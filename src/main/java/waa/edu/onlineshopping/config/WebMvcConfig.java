package waa.edu.onlineshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import waa.edu.onlineshopping.formatter.RoleFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	RoleFormatter roleFormatter;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(roleFormatter);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}

/* https://stackoverflow.com/questions/47552835/the-type-webmvcconfigureradapter-is-deprecated */
/*
	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {

		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}

	}

*/
