package hoangytm.HandleExceptionSpringSecurity.service;

import hoangytm.HandleExceptionSpringSecurity.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author PhanHoang
 * 2/6/2020
 */
public interface CustomUserService extends UserDetailsService {
    User findUserByEmail(String email);
    void deleteUser(String email);
}
