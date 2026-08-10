package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jHttpConfiguration} bean methods.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jHttpConfigurationTest {

    private final Pac4jHttpConfiguration config = new Pac4jHttpConfiguration();

    @Test
    void formClient() {
        Pac4jHttpProperties props = new Pac4jHttpProperties();
        props.setLoginUrl("https://login.example.com");
        props.setUsernameParameter("user");
        props.setPasswordParameter("pass");

        var client = config.formClient(props);
        assertThat(client).isNotNull();
    }

    @Test
    void indirectBasicAuthClient() {
        Pac4jHttpProperties props = new Pac4jHttpProperties();
        props.setRealmName("MyRealm");

        var client = config.indirectBasicAuthClient(props);
        assertThat(client).isNotNull();
    }

    @Test
    void directBasicAuthClient() {
        Pac4jHttpProperties props = new Pac4jHttpProperties();

        var client = config.directBasicAuthClient(props);
        assertThat(client).isNotNull();
    }
}
