package org.pac4j.spring.boot;

import org.pac4j.core.context.JEEContext;
import org.pac4j.core.ext.http.callback.QueryParameterCallbackUrlExtResolver;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.DefaultUrlResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.pac4j.core.logout.handler.LogoutHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://blog.csdn.net/u010004082/article/details/79744481?utm_source=blogxgwz9
@Configuration
@ConditionalOnClass({ DefaultAjaxRequestResolver.class})
@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ Pac4jProperties.class, Pac4jLogoutProperties.class })
/**
 * Base Spring Boot configuration registering the shared pac4j infrastructure beans:
 * the AJAX-request resolver, callback-URL resolver, URL resolver, HTTP action adapter
 * and the logout handler.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Pac4jBaseConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
    /**
     * <p>Logout handler.</p>
     * @param logoutProperties
     * @return the logout handler
     */
    public LogoutHandler<JEEContext> logoutHandler(Pac4jLogoutProperties logoutProperties){
		DefaultLogoutHandler<JEEContext> logoutHandler = new DefaultLogoutHandler<JEEContext>();
		logoutHandler.setDestroySession(logoutProperties.isDestroySession());
		return logoutHandler;
	}
	
	@Bean
	@ConditionalOnMissingBean
    /**
     * <p>Ajax request resolver.</p>
     * @return the ajax request resolver
     */
	protected AjaxRequestResolver ajaxRequestResolver() {
		return new DefaultAjaxRequestResolver();
	}
	
	@Bean
	@ConditionalOnMissingBean
    /**
     * <p>Callback url resolver.</p>
     * @param pac4jProperties
     * @return the callback url resolver
     */
	protected CallbackUrlResolver callbackUrlResolver(Pac4jProperties pac4jProperties) {
		return new QueryParameterCallbackUrlExtResolver(pac4jProperties.isCallbackUrlFixed(),
				pac4jProperties.getCallbackUrl(),
				pac4jProperties.getCustomParams());
	}
	
	@Bean
	@ConditionalOnMissingBean
    /**
     * <p>Url resolver.</p>
     * @param pac4jProperties
     * @return the url resolver
     */
	protected UrlResolver urlResolver(Pac4jProperties pac4jProperties) {
		return new DefaultUrlResolver(pac4jProperties.isCompleteRelativeUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean
    /**
     * <p>Http action adapter.</p>
     * @return the http action adapter
     */
	protected JEEHttpActionAdapter httpActionAdapter() {
		return JEEHttpActionAdapter.INSTANCE;
	}
}
