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
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .mvcMatchers(HttpMethod.GET, "/api/account/currentUser").authenticated()
                .mvcMatchers(HttpMethod.PUT, "/api/account/**").authenticated()

                .mvcMatchers(HttpMethod.POST, "/api/login").not().authenticated()
                .mvcMatchers(HttpMethod.POST, "/api/social/login").not().authenticated()

                .mvcMatchers("/api/taken").authenticated()

                .mvcMatchers(HttpMethod.POST, "/api/qnaBoards").authenticated()
                .mvcMatchers(HttpMethod.PUT, "/api/qnaBoards").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/api/qnaBoards").authenticated()

                .anyRequest().permitAll()
                ;

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
