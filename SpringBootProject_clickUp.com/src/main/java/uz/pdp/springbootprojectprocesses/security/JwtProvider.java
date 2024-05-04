package uz.pdp.springbootprojectprocesses.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class JwtProvider {

   private static final long expireTime = 1000 * 60 * 60 * 24;   // 1 KUN
   private static final String secretWord = "maxfiysozbunihechkimbilmasin";


   public String generateToken(String username){
      Date expireDate = new Date(System.currentTimeMillis() + expireTime);
      String token = Jwts
              .builder()
              .setSubject(username)
              .setIssuedAt(new Date())
              .setExpiration(expireDate)
              .signWith(SignatureAlgorithm.HS512, secretWord)
              .compact();
      return  token;
   }

   public String getEmailFromToken(String token){

        try {
           String email = Jwts
                   .parser()
                   .setSigningKey(secretWord)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
           return email;

        }catch (Exception e){
           return null;
        }
   }




}
