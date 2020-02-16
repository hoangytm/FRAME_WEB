package hoangytm.HandleExceptionSpringSecurity.service;

public interface FacebookService {
    String facebookGenerateUrl();

    void generateFacebookAccessToken(String code);

    String getUserData(String accessToken);
}
