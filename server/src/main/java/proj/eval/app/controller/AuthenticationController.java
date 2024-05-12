package proj.eval.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.entity.auth.Users;
import proj.eval.app.request.AuthRequest;
import proj.eval.app.request.SignUpRequest;
import proj.eval.app.security.UserDetailsImpl;
import proj.eval.app.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private AuthenticationManager authenticationManager;
  private PasswordEncoder passwordEncoder;
  private JwtService jwtService;

  public AuthenticationController(
    AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder,
    JwtService jwtService
  ) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @PostMapping("/sign-in")
  private ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest)
    throws JsonProcessingException {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authRequest.getUsername(),
        authRequest.getPassword()
      )
    );

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String token = jwtService.generateToken(userDetails);
    HashMap<String, Object> response = new HashMap<>();
    response.put("token", token);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/sign-out")
  private ResponseEntity<?> signOut() {
    return new ResponseEntity<>("Signed out", HttpStatus.OK);
  }

  @PostMapping("/sign-up")
  private ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest)
    throws Exception {
    Users user = new Users();
    user.setUsername(signUpRequest.getUsername());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setGroup(signUpRequest.getGroup());
    user.create();
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
