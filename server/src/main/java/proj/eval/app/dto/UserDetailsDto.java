package proj.eval.app.dto;

import lombok.Data;

@Data
public class UserDetailsDto {

  private Long id;
  private String username;
  private String password;
  private String authority;
}
