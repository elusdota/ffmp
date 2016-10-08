package org.craftsmen.ffmp.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.craftsmen.ffmp.webclient.controllers"})
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Supports FileUploads.
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(50000000);
        return multipartResolver;
    }
//	@Bean
//	public MessageSource messageSource()
//	{
//		ReloadableResourceBundleMessageSource messageSource =
//				new ReloadableResourceBundleMessageSource();
//		messageSource.setCacheSeconds(-1);
//		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
//		messageSource.setBasenames(
//				"classpath:/i18n/faces", "classpath:/i18n/messages", "classpath:/i18n/errors"
//		);
//		return messageSource;
//	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(new MappingJackson2HttpMessageConverter());
//	}
}
