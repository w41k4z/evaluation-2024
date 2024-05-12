package proj.eval.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proj.eval.app.entity.auth.Groups;

@Data
public class SignUpRequest {

  @NotBlank(message = "Username is required")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;

  @NotNull(message = "New user must be assigned to a group")
  private Groups group;
}
