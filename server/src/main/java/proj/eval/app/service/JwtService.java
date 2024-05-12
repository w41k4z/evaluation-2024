package proj.eval.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import proj.eval.app.dto.UserDetailsDto;
import proj.eval.app.security.UserDetailsImpl;

@Service
public class JwtService {

  @Value("${jwt.private.key}")
  private String SECRET_KEY;

  @Value("${jwt.expiration.time}")
  private Long EXPIRATION_TIME;

  private Key getSignInKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
  }

  public String generateToken(UserDetailsImpl userDetails)
    throws JsonProcessingException {
    // Serialize UserDetails object to JSON string
    UserDetailsDto userDetailsDto = new UserDetailsDto();
    userDetailsDto.setId(userDetails.getId());
    userDetailsDto.setUsername(userDetails.getUsername());
    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    userDetailsDto.setAuthority(authorities.iterator().next().getAuthority());

    String userDetailsJson = new ObjectMapper()
      .writeValueAsString(userDetailsDto);

    return Jwts
      .builder()
      .setClaims(Map.of("userDetails", userDetailsJson))
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(this.getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public UserDetails getUserDetailsFromToken(String token) {
    // Parse the token and extract the "userDetails" claim
    Claims claims = this.extractAllClaims(token);
    String userDetailsJson = (String) claims.get("userDetails");

    // Deserialize the JSON string back into UserDetails object
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      UserDetailsDto userDetailsDto = objectMapper.readValue(
        userDetailsJson,
        UserDetailsDto.class
      );
      UserDetails userDetails = new UserDetailsImpl(userDetailsDto);
      return userDetails;
    } catch (Exception e) {
      throw new RuntimeException(
        "Failed to deserialize UserDetails from token",
        e
      );
    }
  }

  public boolean isValidToken(String token) {
    try {
      Jwts
        .parserBuilder()
        .setSigningKey(this.getSignInKey())
        .build()
        .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String getSubject(String token) {
    return extractClaim(token, Claims::getSubject);
  }
}
