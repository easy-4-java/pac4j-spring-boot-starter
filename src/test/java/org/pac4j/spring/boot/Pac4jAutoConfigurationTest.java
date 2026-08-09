package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pac4j.core.authorization.authorizer.CheckHttpMethodAuthorizer;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.ext.http.callback.QueryParameterCallbackUrlExtResolver;
import org.pac4j.core.http.url.DefaultUrlResolver;
import org.pac4j.core.http.url.UrlResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tests for {@link Pac4jAutoConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jAutoConfigurationTest {

    private Pac4jAutoConfiguration config;

    @BeforeEach
    void setUp() {
        config = new Pac4jAutoConfiguration();
    }

    @Test
    void canInstantiate() {
        assertThat(config).isNotNull();
    }

    @Test
    void clientsWithDefaultClients() {
        Pac4jProperties props = new Pac4jProperties();
        List<Client> clientList = new ArrayList<>();
        List<org.pac4j.core.authorization.generator.AuthorizationGenerator> authGens = new ArrayList<>();
        DefaultAjaxRequestResolver ajaxResolver = new DefaultAjaxRequestResolver();
        QueryParameterCallbackUrlExtResolver callbackResolver = new QueryParameterCallbackUrlExtResolver(false, "https://cb.example.com", Collections.emptyMap());
        DefaultUrlResolver urlResolver = new DefaultUrlResolver(false);

        Clients clients = config.clients(props, clientList, authGens, ajaxResolver, callbackResolver, urlResolver);
        assertThat(clients).isNotNull();
    }

    @Test
    void clientsWithSpecifiedClients() {
        Pac4jProperties props = new Pac4jProperties();
        props.setClients("myClient");
        List<Client> clientList = new ArrayList<>();
        List<org.pac4j.core.authorization.generator.AuthorizationGenerator> authGens = new ArrayList<>();
        DefaultAjaxRequestResolver ajaxResolver = new DefaultAjaxRequestResolver();
        QueryParameterCallbackUrlExtResolver callbackResolver = new QueryParameterCallbackUrlExtResolver(false, "https://cb.example.com", Collections.emptyMap());
        DefaultUrlResolver urlResolver = new DefaultUrlResolver(false);

        Clients clients = config.clients(props, clientList, authGens, ajaxResolver, callbackResolver, urlResolver);
        assertThat(clients).isNotNull();
    }

    @Test
    void configBean() {
        Pac4jProperties props = new Pac4jProperties();
        props.setAllowedIpRegexpPattern("192\\.168\\..*");
        props.setAllowedHttpMethods(new String[]{"GET", "POST"});

        Clients clients = new Clients();
        HttpActionAdapter adapter = JEEHttpActionAdapter.INSTANCE;

        Config cfg = config.config(props, clients, adapter);
        assertThat(cfg).isNotNull();
    }

    @Test
    void configBeanWithoutIpAndMethods() {
        Pac4jProperties props = new Pac4jProperties();
        Clients clients = new Clients();
        HttpActionAdapter adapter = JEEHttpActionAdapter.INSTANCE;

        Config cfg = config.config(props, clients, adapter);
        assertThat(cfg).isNotNull();
    }

    @Test
    void configBeanWithIpOnly() {
        Pac4jProperties props = new Pac4jProperties();
        props.setAllowedIpRegexpPattern("10\\.0\\..*");
        Clients clients = new Clients();
        HttpActionAdapter adapter = JEEHttpActionAdapter.INSTANCE;

        Config cfg = config.config(props, clients, adapter);
        assertThat(cfg).isNotNull();
    }

    @Test
    void configBeanWithMethodsOnly() {
        Pac4jProperties props = new Pac4jProperties();
        props.setAllowedHttpMethods(new String[]{"GET"});
        Clients clients = new Clients();
        HttpActionAdapter adapter = JEEHttpActionAdapter.INSTANCE;

        Config cfg = config.config(props, clients, adapter);
        assertThat(cfg).isNotNull();
    }
}
