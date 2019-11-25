package me.thursdayParty.safeFoodApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Bactoria
 * @since 2019-11-24 [2019.11월.24]
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .mvcMatchers(HttpMethod.POST, "/oauth/token").not().authenticated()
                .mvcMatchers(HttpMethod.GET, "/login/google").not().authenticated()
                .mvcMatchers(HttpMethod.GET, "/api/foods/**").authenticated()
                .mvcMatchers(HttpMethod.GET, "/user").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/login").authenticated()
                .anyRequest().permitAll()
                ;

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
