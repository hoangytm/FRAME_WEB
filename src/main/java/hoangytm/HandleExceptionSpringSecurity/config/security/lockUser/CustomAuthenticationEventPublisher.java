package hoangytm.HandleExceptionSpringSecurity.config.security.lockUser;

import hoangytm.HandleExceptionSpringSecurity.utils.GetIP;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author PhanHoang
 * 3/1/2020
 */
@Component
public class CustomAuthenticationEventPublisher implements AuthenticationEventPublisher {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CustomAuthenticationEventPublisher.class);
    @Autowired
    LoginAttemptService loginAttemptService;
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        try {
            String ip= GetIP.getClientIP().replace(":","").replace(" ","");
            loginAttemptService.loginSucceeded(ip);
        }
        catch (RuntimeException e){

            log.info("Ip null");
        }


    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        try {
            String ip= GetIP.getClientIP().replace(":","").replace(" ","");
            loginAttemptService.loginFailed(ip);
        }
        catch (RuntimeException r){

            log.info("Ip null");
        }

    }
}
