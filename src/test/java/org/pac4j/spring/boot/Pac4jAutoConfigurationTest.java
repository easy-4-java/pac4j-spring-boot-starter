package org.pac4j.spring.boot;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.UrlResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Pac4jAutoConfigurationTest {

    private final Pac4jAutoConfiguration configuration = new Pac4jAutoConfiguration();

    @Test
    void derivesDefaultSecurityClientsFromRegisteredOrder() {
        Client<?> first = mock(Client.class);
        Client<?> second = mock(Client.class);
        when(first.getName()).thenReturn("form");
        when(second.getName()).thenReturn("basic");
        AjaxRequestResolver ajaxResolver = mock(AjaxRequestResolver.class);
        CallbackUrlResolver callbackResolver = mock(CallbackUrlResolver.class);
        UrlResolver urlResolver = mock(UrlResolver.class);

        Clients clients = configuration.clients(new Pac4jProperties(), Arrays.<Client>asList(first, second),
                Collections.<AuthorizationGenerator>emptyList(), ajaxResolver, callbackResolver, urlResolver);

        assertThat(clients.getClients()).containsExactly(first, second);
        assertThat(clients.getDefaultSecurityClients()).isEqualTo("form,basic");
        assertThat(clients.getCallbackUrlResolver()).isSameAs(callbackResolver);
        assertThat(clients.getUrlResolver()).isSameAs(urlResolver);
    }

    @Test
    void honorsExplicitClientsAndRegistersConfiguredAuthorizationRules() {
        Client<?> client = mock(Client.class);
        when(client.getName()).thenReturn("form");
        Pac4jProperties properties = new Pac4jProperties();
        properties.setClients("form");
        properties.setAllowedIpRegexpPattern("127\\.0\\.0\\.1");
        properties.setAllowedHttpMethods(new String[] { "GET", "POST" });
        Clients clients = configuration.clients(properties, Collections.<Client>singletonList(client),
                Collections.<AuthorizationGenerator>emptyList(), mock(AjaxRequestResolver.class),
                mock(CallbackUrlResolver.class), mock(UrlResolver.class));
        HttpActionAdapter adapter = mock(HttpActionAdapter.class);

        Config config = configuration.config(properties, clients, adapter);

        assertThat(clients.getDefaultSecurityClients()).isEqualTo("form");
        assertThat(config.getClients()).isSameAs(clients);
        assertThat(config.getHttpActionAdapter()).isSameAs(adapter);
        assertThat(config.getAuthorizers()).containsKeys("isIPAuthenticated", "isMethodAuthenticated");
    }
}
