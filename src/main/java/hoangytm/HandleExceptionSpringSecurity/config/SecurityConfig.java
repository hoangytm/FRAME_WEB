package hoangytm.HandleExceptionSpringSecurity.config;

import hoangytm.HandleExceptionSpringSecurity.exception.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * @author PhanHoang
 * 2/6/2020
 */
@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Resource(name = "userService")

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    @Bean
    @Primary
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatcher("/private/**").authorizeRequests();
        http.authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint).and()
        ;

    }


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
