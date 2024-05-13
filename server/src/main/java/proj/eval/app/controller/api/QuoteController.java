package proj.eval.app.controller.api;

import jakarta.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.entity.FinitionType;
import proj.eval.app.entity.HouseConstructionDetail;
import proj.eval.app.entity.HouseType;
import proj.eval.app.entity.Quote;
import proj.eval.app.entity.QuoteDetail;
import proj.eval.app.request.NewQuoteRequest;
import proj.eval.app.security.UserDetailsImpl;
import proj.w41k4z.orm.database.connectivity.ConnectionManager;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;

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
        userDetails.getId()
      );
      HouseType houseType = new HouseType()
        .findById(connection, newQuoteRequest.getHouseType().getId());
      for (HouseConstructionDetail detail : houseType.getHouseConstructionDetails()) {
        QuoteDetail quoteDetail = new QuoteDetail();
        quoteDetail.setQuoteId(newQuote.getId());
        quoteDetail.setUnitPrice(detail.getUnitPrice());
        quoteDetail.setQuantity(detail.getDefaultQuantity());
        quoteDetail.setWorkDetailsId(detail.getWorkDetailsId());

        quoteDetail.create(connection);
      }
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
