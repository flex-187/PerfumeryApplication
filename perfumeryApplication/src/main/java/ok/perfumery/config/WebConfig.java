package ok.perfumery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ok.perfumery.converter.ProductTypeToStringConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("overview");
		registry.addViewController("/login").setViewName("loginForm");
	}
	
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new ProductTypeToStringConverter());
	}

}
