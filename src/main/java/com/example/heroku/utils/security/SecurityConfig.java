package com.example.heroku.utils.security;


import com.example.heroku.repo.TokenRepository;
import com.example.heroku.repo.UserRepository;
import com.example.heroku.utils.CustomAuthenticationProvider;
import com.example.heroku.utils.security.token.TokenService;
import com.example.heroku.utils.security.user.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;
    private UserDetailsService userDetailsService;
    private TokenService repository;

    @Autowired
    public SecurityConfig(UserRepository userRepository, TokenRepository repository) {
        this.userRepository = userRepository;
        userDetailsService = new UserDetailsService(userRepository);
        this.repository = new TokenService(repository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new CustomAuthenticationProvider(userRepository));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/resources/**", "/font/**", "/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/", "/tube/*",
                        "/tube", "/point", "/point/*", "/building/*", "/building").permitAll()
                .antMatchers(HttpMethod.POST, "/point/*", "/point", "/tube", "/tube/*", "/building/*", "/building").permitAll()
                .antMatchers("/users/registration").anonymous()
                .antMatchers("/users/account", "/users/edit_data", "/users/edit_address").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**").hasRole("ADMIN")
//                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/users/account", false)
                .loginPage("/users/login").failureUrl("/users/login?error=true").permitAll()
                .and()
                .logout().logoutUrl("/users/logout").logoutSuccessUrl("/users/login").permitAll()
                .and()
                .rememberMe().tokenValiditySeconds(1209600).rememberMeParameter("remember-me").rememberMeCookieName("remember_me").userDetailsService(userDetailsService)
                .tokenRepository(repository)
                .and()
                .exceptionHandling().accessDeniedPage("/error/403")
                .and()
                .csrf().disable();
        http.httpBasic();
    }

}
