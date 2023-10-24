package net.guzari.auth.security;

import lombok.AllArgsConstructor;
import net.guzari.auth.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();
        Boolean existUserByEmail = userService.isExistUserByEmail(email);
        if (existUserByEmail) {
            String jwtToken = jwtService.generateToken(email);
            response.setContentType("application/json");
            response.getWriter().write("{\"token\": \"" + jwtToken + "\"}");
        } else {
            response.sendRedirect("/login?error=true");
        }
    }
}
