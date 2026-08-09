package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.DefaultUrlResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.pac4j.core.logout.handler.LogoutHandler;

import java.util.HashMap;

/**
 * Tests for {@link Pac4jBaseConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jBaseConfigurationTest {

    private Pac4jBaseConfiguration config;

    @BeforeEach
    void setUp() {
        config = new Pac4jBaseConfiguration();
    }

    @Test
    void canInstantiate() {
        assertThat(config).isNotNull();
    }

    @Test
    void logoutHandler() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        props.setDestroySession(true);
        LogoutHandler<JEEContext> handler = config.logoutHandler(props);
        assertThat(handler).isNotNull();
        assertThat(handler).isInstanceOf(DefaultLogoutHandler.class);
    }

    @Test
    void logoutHandlerDefaultDestroySession() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        LogoutHandler<JEEContext> handler = config.logoutHandler(props);
        assertThat(handler).isNotNull();
    }

    @Test
    void ajaxRequestResolver() {
        assertThat(config.ajaxRequestResolver()).isInstanceOf(DefaultAjaxRequestResolver.class);
    }

    @Test
    void callbackUrlResolver() {
        Pac4jProperties props = new Pac4jProperties();
        props.setCallbackUrl("https://example.com/callback");
        props.setCallbackUrlFixed(true);
        CallbackUrlResolver resolver = config.callbackUrlResolver(props);
        assertThat(resolver).isNotNull();
    }

    @Test
    void callbackUrlResolverWithCustomParams() {
        Pac4jProperties props = new Pac4jProperties();
        props.setCallbackUrl("https://example.com/callback");
        props.setCustomParams(new HashMap<>());
        props.getCustomParams().put("key", "value");
        CallbackUrlResolver resolver = config.callbackUrlResolver(props);
        assertThat(resolver).isNotNull();
    }

    @Test
    void urlResolver() {
        Pac4jProperties props = new Pac4jProperties();
        props.setCompleteRelativeUrl(true);
        UrlResolver resolver = config.urlResolver(props);
        assertThat(resolver).isNotNull();
        assertThat(resolver).isInstanceOf(DefaultUrlResolver.class);
    }

    @Test
    void urlResolverDefault() {
        Pac4jProperties props = new Pac4jProperties();
        UrlResolver resolver = config.urlResolver(props);
        assertThat(resolver).isNotNull();
    }

    @Test
    void httpActionAdapter() {
        assertThat(config.httpActionAdapter()).isEqualTo(JEEHttpActionAdapter.INSTANCE);
    }
}
