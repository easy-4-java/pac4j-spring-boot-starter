package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jClientNames}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jClientNamesTest {

    @Test
    void casClientNames() {
        assertThat(Pac4jClientNames.CAS_CLIENT).isEqualTo("cas-client");
        assertThat(Pac4jClientNames.DIRECT_CAS_CLIENT).isEqualTo("direct-cas-client");
        assertThat(Pac4jClientNames.DIRECT_CAS_PROXY_CLIENT).isEqualTo("direct-cas-proxy-client");
        assertThat(Pac4jClientNames.CAS_REST_BASIC_AUTH_CLIENT).isEqualTo("cas-rest-basic-auth-client");
        assertThat(Pac4jClientNames.CAS_REST_FORM_CLIENT).isEqualTo("cas-rest-form-client");
    }

    @Test
    void httpClientNames() {
        assertThat(Pac4jClientNames.FORM_CLIENT).isEqualTo("form-client");
        assertThat(Pac4jClientNames.INDIRECT_BASIC_AUTH_CLIENT).isEqualTo("indirect-basic-auth-client");
        assertThat(Pac4jClientNames.DIRECT_BASIC_AUTH_CLIENT).isEqualTo("direct-basic-auth-client");
    }
}
