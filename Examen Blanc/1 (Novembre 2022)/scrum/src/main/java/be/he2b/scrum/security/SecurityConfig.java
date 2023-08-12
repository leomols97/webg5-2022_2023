/* package be.he2b.scrum.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String secret1 = encoder.encode("bob");
        UserDetails user1 = User.withUsername("bob")
                .password(secret1)
                .roles("ADMIN")
                .build();

        String secret2 = encoder.encode("alice");
        UserDetails user2 = User.withUsername("alice")
                .password(secret2)
                .roles("USER")
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        users.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");

        users.createUser(user1);
        users.createUser(user2);

        return users;
    }

}
 */