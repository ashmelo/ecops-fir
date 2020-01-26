package com.ecops.wrapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ecops.beans.FirRequest;
import com.ecops.beans.UserResponse;
import com.ecops.exception.BadRequestException;
import com.ecops.parameter.ApplicationParameter;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class TokenVerify {

    public static final String TOKEN_HEADER = "Authorization";

    @Autowired
    ApplicationParameter applicationParameter;

    public boolean verifyToken(HttpServletRequest request, String userId) {

        try {
            UserResponse userResponse;
            String url = "http://localhost:9090/profile/api/v1/get_user?userId=" + userId;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity requestEntity = new HttpEntity(null, headers);
            ResponseEntity<UserResponse> re = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UserResponse.class);
            userResponse = re.getBody();
            String token = request.getHeader(TOKEN_HEADER);
            token = token.replace("Bearer ","");
            Algorithm algorithm = Algorithm.HMAC256("tokenSecret");
            if (token != null) {
                JWTVerifier verifier = JWT.require(algorithm)
                        .withClaim("identityProof", userResponse.getUserId())
                        .withClaim("password", userResponse.getPassword())
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                return true;
            } else {
                throw new BadRequestException("Token not provided");
            }
        } catch (JWTVerificationException e) {
            // in case of verification error a further filter will take care about authentication error
            throw new BadRequestException("Authentication error. Token is not valid");
        } catch (NullPointerException e) {
            throw new BadRequestException("Token not provided");
        } catch (Exception e) {
            throw new BadRequestException("User not found");
        }
    }
}
