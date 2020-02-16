package hoangytm.HandleExceptionSpringSecurity.service;

import hoangytm.HandleExceptionSpringSecurity.entity.Role;
import hoangytm.HandleExceptionSpringSecurity.entity.User;
import hoangytm.HandleExceptionSpringSecurity.repo.RoleRepo;
import hoangytm.HandleExceptionSpringSecurity.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


/**
 * @author PhanHoang
 * 2/6/2020
 */

@Service("userDetailsService")
@Primary
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepo.findUserByEmail(email);
        if (user == null) {
            log.error("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = getAuthorities(user);


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<Role> roles = roleRepo.findRoleByUserID(user.getId());
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase()));

        }
        return authorities;
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public void deleteUser() {
        userRepo.deleteUserByEmail("hoang2");
        System.out.println("SUCCESS");
//        throw new RuntimeException();
    }
}
