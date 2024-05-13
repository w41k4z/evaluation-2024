package proj.eval.app.controller.api;

import jakarta.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.entity.FinitionType;
import proj.eval.app.entity.HouseType;
import proj.eval.app.entity.Quote;
import proj.eval.app.entity.V_QuoteDetail;
import proj.eval.app.request.NewQuoteRequest;
import proj.eval.app.security.UserDetailsImpl;
import proj.w41k4z.orm.database.QueryExecutor;
import proj.w41k4z.orm.database.connectivity.ConnectionManager;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;
import proj.w41k4z.orm.database.request.Condition;
import proj.w41k4z.orm.database.request.Operator;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

  @GetMapping("/house-types")
  public ResponseEntity<?> getHouseTypesAndFinitionTypes() throws SQLException {
    HashMap<String, Object> response = new HashMap<>();
    DatabaseConnection connection = ConnectionManager.getDatabaseConnection();
    try {
      response.put("houseTypes", new HouseType().findAll(connection));
      response.put("finitionTypes", new FinitionType().findAll(connection));
    } catch (Exception e) {
      connection.close();
      throw new RuntimeException(e);
    }
    connection.close();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/client")
  public ResponseEntity<?> getAllQuotes(
    @AuthenticationPrincipal UserDetailsImpl userDetails
  )
    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IllegalArgumentException, SecurityException, SQLException, IOException {
    return new ResponseEntity<>(
      new Quote()
        .findAll(
          Condition.WHERE("_users_id_", Operator.E, userDetails.getId()),
          null,
          null
        ),
      HttpStatus.OK
    );
  }

  @GetMapping("/{quoteId}")
  public ResponseEntity<?> getQuote(@PathVariable String quoteId)
    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IllegalArgumentException, SecurityException, SQLException, IOException {
    return new ResponseEntity<>(
      new V_QuoteDetail()
        .findAll(Condition.WHERE("_id_", Operator.E, quoteId), null, null),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<?> createNewQuote(
    @RequestBody @Valid NewQuoteRequest newQuoteRequest,
    @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws SQLException {
    DatabaseConnection connection = ConnectionManager.getDatabaseConnection();
    Quote newQuote = new Quote();
    try {
      newQuote.create(
        connection,
        newQuoteRequest.getHouseType(),
        newQuoteRequest.getFinitionType(),
        newQuoteRequest.getConstructionStartDate(),
        userDetails.getId()
      );
      QueryExecutor executor = new QueryExecutor();
      executor.executeInstruction(
        "SELECT insert_quote_details(" + newQuote.getId() + ")",
        connection.getConnection()
      );
      connection.commit();
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      connection.rollback();
      throw new SQLException(e);
    } finally {
      connection.close();
    }
  }
}
