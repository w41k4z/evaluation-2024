package proj.eval.app.controller.api;

import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.service.AdminService;
import proj.w41k4z.orm.database.connectivity.ConnectionManager;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @GetMapping("/reinitialize-database")
  public ResponseEntity<?> reinitialize() throws SQLException {
    DatabaseConnection connection = ConnectionManager.getDatabaseConnection();
    this.adminService.reinitializeDatabase(connection);
    connection.commit();
    connection.close();
    return ResponseEntity.ok().build();
  }
}
