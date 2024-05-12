package proj.eval.app.service;

import java.sql.SQLException;
import org.springframework.stereotype.Service;
import proj.w41k4z.orm.database.QueryExecutor;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;

@Service
public class AdminService {

  private final Long ADMIN_ID = 1L;

  // Nothing to reinitialize for now
  public void reinitializeDatabase(DatabaseConnection databaseConnection)
    throws SQLException {
    QueryExecutor queryExecutor = new QueryExecutor();
    queryExecutor.executeInstruction(
      "SELECT reinitialize(" + ADMIN_ID + ")",
      databaseConnection.getConnection()
    );
  }
}
