package com.stackroute;

import com.stackroute.config.JwtFilter;
import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableDiscoveryClient
public class UserAuthenticationApplication {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/secure/*");

        return registrationBean;
    }

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationApplication.class, args);
	}
//    @Autowired
//    private UserRepository userRepository;
    /*@Override
    public void run(String... args) throws Exception {
          userRepository.save(new User("rajvineeth@gmail.com","12345678","SME"));
    }*/
}
