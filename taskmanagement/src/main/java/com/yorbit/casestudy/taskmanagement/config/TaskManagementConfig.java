package com.yorbit.casestudy.taskmanagement.config;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.DEFAULT_LANG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.ERRORMESSAGES;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELDS;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.SCRIPT_FILE_PROPERTY;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * The Class TaskManagementConfig.
 *
 * @author Manoj SP
 */
@Configuration
public class TaskManagementConfig implements ResourceLoaderAware {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagementConfig.class);

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	private Resource resource;

	/**
	 * Execute scripts one time at start of application.
	 */
	@PostConstruct
	public void setup() {
		try {
			if (Objects.nonNull(resource) && resource.exists() && resource.contentLength() != 0l) {
				logger.debug("executing script from resource");
				ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
			}
		} catch (ScriptException | SQLException | IOException e) {
			logger.error("Script execution Failed", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ResourceLoaderAware#setResourceLoader(org.
	 * springframework.core.io.ResourceLoader)
	 */
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		if (Objects.nonNull(env.getProperty(SCRIPT_FILE_PROPERTY))) {
			logger.debug("Loading resource from provided properties");
			resource = resourceLoader.getResource(env.getProperty(SCRIPT_FILE_PROPERTY));
		}
	}

	/**
	 * Locale resolver.
	 *
	 * @return the locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		Locale locale = new Locale(env.getProperty(DEFAULT_LANG));
		LocaleContextHolder.setLocale(locale);
		sessionLocaleResolver.setDefaultLocale(locale);
		return sessionLocaleResolver;
	}

	/**
	 * Message source.
	 *
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames(env.getProperty(ERRORMESSAGES), env.getProperty(FIELDS));
		return source;
	}

}
