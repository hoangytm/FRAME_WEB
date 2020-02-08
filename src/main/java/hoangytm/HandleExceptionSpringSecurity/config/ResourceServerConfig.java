package hoangytm.HandleExceptionSpringSecurity.config;

import hoangytm.HandleExceptionSpringSecurity.exception.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author PhanHoang
 * 2/6/2020
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/private/**").access("hasRole('ADMIN')")
                .antMatchers("/oauth/token").permitAll();
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint).and()
        ;

    }


}
