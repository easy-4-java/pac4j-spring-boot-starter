package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for configuration classes - basic instantiation.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jConfigurationClassesTest {

    @Test
    void autoConfigurationCanInstantiate() {
        Pac4jAutoConfiguration config = new Pac4jAutoConfiguration();
        assertThat(config).isNotNull();
        assertThat(config.logger).isNotNull();
    }

    @Test
    void casConfigurationCanInstantiate() {
        Pac4jCasConfiguration config = new Pac4jCasConfiguration();
        assertThat(config).isNotNull();
    }

    @Test
    void httpConfigurationCanInstantiate() {
        Pac4jHttpConfiguration config = new Pac4jHttpConfiguration();
        assertThat(config).isNotNull();
    }

    @Test
    void jwtConfigurationCanInstantiate() {
        Pac4jJwtConfiguration config = new Pac4jJwtConfiguration();
        assertThat(config).isNotNull();
    }

    @Test
    void oauthConfigurationCanInstantiate() {
        Pac4jOAuthConfiguration config = new Pac4jOAuthConfiguration();
        assertThat(config).isNotNull();
    }
}
