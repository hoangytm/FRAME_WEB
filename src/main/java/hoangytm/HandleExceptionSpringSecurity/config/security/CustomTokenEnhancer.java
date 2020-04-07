package hoangytm.HandleExceptionSpringSecurity.config.security;

import hoangytm.HandleExceptionSpringSecurity.entity.User;
import hoangytm.HandleExceptionSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PhanHoang
 * 2/8/2020
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    UserService userService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
//        User user = (User) oAuth2Authentication.getUserAuthentication();
        User user = userService.findUserByEmail(oAuth2Authentication.getName());
        additionalInfo.put("userInfo", user);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;

    }
}
