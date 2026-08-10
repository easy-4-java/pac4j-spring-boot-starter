package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pac4j.cas.client.CasProxyReceptor;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.pac4j.core.logout.handler.LogoutHandler;

/**
 * Tests for {@link Pac4jCasConfiguration} bean methods.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jCasConfigurationTest {

    private Pac4jCasConfiguration config;

    @BeforeEach
    void setUp() {
        config = new Pac4jCasConfiguration();
        // Set required fields via reflection since @Autowired is used
        Pac4jProperties pac4jProps = new Pac4jProperties();
        pac4jProps.setCallbackUrl("https://app.example.com/callback");
        pac4jProps.setClientParameterName("client_name");

        Pac4jCasProperties casProps = new Pac4jCasProperties();
        casProps.setLoginUrl("https://cas.example.com/login");
        casProps.setPrefixUrl("https://cas.example.com");
        casProps.setProtocol(CasProtocol.CAS30);
        casProps.setEncoding("UTF-8");
        casProps.setCustomParams(new HashMap<>());
        casProps.setPostLogoutUrlParameter("service");
        casProps.setServiceUrl("https://app.example.com");
        casProps.setServiceParameterName("service");
        casProps.setCasClientName("cas");
        casProps.setDirectCasClientName("direct-cas");
        casProps.setDirectCasProxyClientName("direct-cas-proxy");
        casProps.setCasRestBasicAuthClientName("cas-rest-basic");
        casProps.setCasRestFormClientName("cas-rest-form");
        casProps.setUsernameParameterName("username");
        casProps.setPasswordParameterName("password");
        casProps.setHeaderName("Authorization");
        casProps.setPrefixHeader("Basic ");

        try {
            var pac4jField = Pac4jCasConfiguration.class.getDeclaredField("pac4jProperties");
            pac4jField.setAccessible(true);
            pac4jField.set(config, pac4jProps);

            var casField = Pac4jCasConfiguration.class.getDeclaredField("pac4jCasProperties");
            casField.setAccessible(true);
            casField.set(config, casProps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void proxyReceptor() {
        CasProxyReceptor receptor = config.proxyReceptor();
        assertThat(receptor).isNotNull();
    }

    @Test
    void casConfiguration() {
        LogoutHandler<JEEContext> logoutHandler = new DefaultLogoutHandler<>();
        UrlResolver urlResolver = new org.pac4j.core.http.url.DefaultUrlResolver();
        CasProxyReceptor receptor = new CasProxyReceptor();

        CasConfiguration casConfig = config.casConfiguration(logoutHandler, urlResolver, receptor);
        assertThat(casConfig).isNotNull();
    }

    @Test
    void casClient() {
        CasConfiguration casConfig = new CasConfiguration("https://cas.example.com/login", CasProtocol.CAS30);
        CallbackUrlResolver cbResolver = new org.pac4j.core.http.callback.QueryParameterCallbackUrlResolver();

        var client = config.casClient(casConfig, cbResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void directCasClient() {
        CasConfiguration casConfig = new CasConfiguration("https://cas.example.com/login", CasProtocol.CAS30);
        CallbackUrlResolver cbResolver = new org.pac4j.core.http.callback.QueryParameterCallbackUrlResolver();

        var client = config.directCasClient(casConfig, cbResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void directCasProxyClient() {
        CasConfiguration casConfig = new CasConfiguration("https://cas.example.com/login", CasProtocol.CAS30);
        CallbackUrlResolver cbResolver = new org.pac4j.core.http.callback.QueryParameterCallbackUrlResolver();

        var client = config.directCasProxyClient(casConfig, cbResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void casRestBasicAuthClient() {
        CasConfiguration casConfig = new CasConfiguration("https://cas.example.com/login", CasProtocol.CAS30);

        var client = config.casRestBasicAuthClient(casConfig);
        assertThat(client).isNotNull();
    }

    @Test
    void casRestFormClient() {
        CasConfiguration casConfig = new CasConfiguration("https://cas.example.com/login", CasProtocol.CAS30);

        var client = config.casRestFormClient(casConfig);
        assertThat(client).isNotNull();
    }
}
