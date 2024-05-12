package proj.eval.app.security;

import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import proj.eval.app.dto.UserDetailsDto;
import proj.eval.app.entity.auth.Users;

@Data
public class UserDetailsImpl implements UserDetails {

  private Long id;
  private String username;
  private String password;
  private GrantedAuthority authority;

  public UserDetailsImpl(UserDetailsDto userDetailsDto) {
    this.id = userDetailsDto.getId();
    this.username = userDetailsDto.getUsername();
    this.password = userDetailsDto.getPassword();
    this.authority = new SimpleGrantedAuthority(userDetailsDto.getAuthority());
  }

  public UserDetailsImpl(Users user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authority =
      new SimpleGrantedAuthority(user.getGroup().getGroupName().toString());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(this.authority);
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
