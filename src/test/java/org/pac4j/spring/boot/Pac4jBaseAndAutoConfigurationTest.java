package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pac4j.core.authorization.authorizer.CheckHttpMethodAuthorizer;
import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.core.logout.handler.LogoutHandler;

/**
 * Tests for {@link Pac4jBaseConfiguration} and {@link Pac4jAutoConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jBaseAndAutoConfigurationTest {

    private Pac4jBaseConfiguration baseConfig;
    private Pac4jAutoConfiguration autoConfig;

    @BeforeEach
    void setUp() {
        baseConfig = new Pac4jBaseConfiguration();
        autoConfig = new Pac4jAutoConfiguration();
    }

    @Test
    void logoutHandler() {
        Pac4jLogoutProperties logoutProps = new Pac4jLogoutProperties();
        logoutProps.setDestroySession(false);
        LogoutHandler<JEEContext> handler = baseConfig.logoutHandler(logoutProps);
        assertThat(handler).isNotNull();
    }

    @Test
    void ajaxRequestResolver() {
        AjaxRequestResolver resolver = baseConfig.ajaxRequestResolver();
        assertThat(resolver).isNotNull();
    }

    @Test
    void callbackUrlResolver() {
        Pac4jProperties props = new Pac4jProperties();
        props.setCallbackUrlFixed(true);
        props.setCallbackUrl("https://cb.example.com");
        CallbackUrlResolver resolver = baseConfig.callbackUrlResolver(props);
        assertThat(resolver).isNotNull();
    }

    @Test
    void urlResolver() {
        Pac4jProperties props = new Pac4jProperties();
        props.setCompleteRelativeUrl(true);
        UrlResolver resolver = baseConfig.urlResolver(props);
        assertThat(resolver).isNotNull();
    }

    @Test
    void httpActionAdapter() {
        assertThat(baseConfig.httpActionAdapter()).isNotNull();
    }

    @Test
    void clientsBean() {
        Pac4jProperties props = new Pac4jProperties();
        props.setClients("FormClient");

        Client client = mock(Client.class);
        when(client.getName()).thenReturn("FormClient");
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        List<AuthorizationGenerator> authGens = new ArrayList<>();
        AjaxRequestResolver ajaxResolver = mock(AjaxRequestResolver.class);
        CallbackUrlResolver cbResolver = mock(CallbackUrlResolver.class);
        UrlResolver urlResolver = mock(UrlResolver.class);

        Clients clients = autoConfig.clients(props, clientList, authGens, ajaxResolver, cbResolver, urlResolver);
        assertThat(clients).isNotNull();
        assertThat(clients.getClients()).hasSize(1);
    }

    @Test
    void clientsBeanWithoutExplicitClients() {
        Pac4jProperties props = new Pac4jProperties();

        Client client = mock(Client.class);
        when(client.getName()).thenReturn("DefaultClient");
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        List<AuthorizationGenerator> authGens = new ArrayList<>();
        AjaxRequestResolver ajaxResolver = mock(AjaxRequestResolver.class);
        CallbackUrlResolver cbResolver = mock(CallbackUrlResolver.class);
        UrlResolver urlResolver = mock(UrlResolver.class);

        Clients clients = autoConfig.clients(props, clientList, authGens, ajaxResolver, cbResolver, urlResolver);
        assertThat(clients).isNotNull();
    }

    @Test
    void configBean() {
        Pac4jProperties props = new Pac4jProperties();
        props.setAllowedIpRegexpPattern("192\\.168\\..*");
        props.setAllowedHttpMethods(new String[]{"GET", "POST"});

        Clients clients = new Clients();
        Pac4jBaseConfiguration baseCfg = new Pac4jBaseConfiguration();
        var httpAdapter = baseCfg.httpActionAdapter();

        Config config = autoConfig.config(props, clients, httpAdapter);
        assertThat(config).isNotNull();
    }
}
