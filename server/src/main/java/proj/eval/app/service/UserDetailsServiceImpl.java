package proj.eval.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proj.eval.app.entity.auth.Users;
import proj.eval.app.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    try {
      Users user = new Users().findByUsername(null, username);
      return new UserDetailsImpl(user);
    } catch (Exception e) {
      throw new UsernameNotFoundException("User not found");
    }
  }
}
