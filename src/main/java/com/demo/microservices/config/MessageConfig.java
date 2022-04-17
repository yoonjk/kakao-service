package com.demo.microservices.config;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.rakugakibox.util.YamlResourceBundle;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	/**
	 * 셔션에 지역설정 Default : ko
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver()  {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
		return sessionLocaleResolver;
	}
	
	/**
	 * 지역설정을 변경하는 인터셋터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		return localeChangeInterceptor;
	}
	
    @Override // 인터셉터를 시스템 레지스트리에 등록
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
	
	/**
	 * yaml 을 참조하는 MessageSource 선언
	 * @param basename
	 * @param encoding
	 * @return
	 */
	@Bean
	public  MessageSource messageSource(
			@Value("${spring.messages.basename}") String basename,
			@Value("${spring.messages.encoding}") String encoding,
			@Value("${spring.messages.always-use-message-format}") Boolean isAlwaysUseMessageFormat,
			@Value("${spring.messages.fallback-to-system-locale}") Boolean fallbackToSystemLocale,
			@Value("${spring.messages.use-code-as-default-message}") Boolean useCodeAsDefaultMessage
	) {
		
		YamlMessageSource yamlMessageSource = new YamlMessageSource();
		yamlMessageSource.setBasename(basename);
		yamlMessageSource.setDefaultEncoding(encoding);
		yamlMessageSource.setAlwaysUseMessageFormat(isAlwaysUseMessageFormat);
		yamlMessageSource.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
		yamlMessageSource.setFallbackToSystemLocale(fallbackToSystemLocale);
		
		return yamlMessageSource;
	}
	
	/**
	 * Locale 정보에 따라 다른 yaml 파일을 읽도록 처리
	 * @author nexwe
	 *
	 */
	private static class YamlMessageSource extends ResourceBundleMessageSource {
		@Override
		protected ResourceBundle doGetBundle(String basename, Locale locale) {
			return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
		}
	}
}
