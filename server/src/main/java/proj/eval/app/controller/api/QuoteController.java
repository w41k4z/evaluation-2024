package proj.eval.app.controller.api;

import jakarta.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.entity.FinitionType;
import proj.eval.app.entity.HouseType;
import proj.eval.app.request.NewQuoteRequest;
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
      throw new RuntimeException(e);
    }
    connection.close();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public void createNewQuote(
    @RequestBody @Valid NewQuoteRequest newQuoteRequest
  ) {}
}
