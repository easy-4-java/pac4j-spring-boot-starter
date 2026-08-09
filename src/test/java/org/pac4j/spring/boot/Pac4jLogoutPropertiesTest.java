package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jLogoutProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jLogoutPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        assertThat(props.getPathPattern()).isEqualTo("/**/logout/pac4j");
        assertThat(props.isLocalLogout()).isTrue();
        assertThat(props.isCentralLogout()).isTrue();
        assertThat(props.isDestroySession()).isTrue();
        assertThat(props.getDefaultUrl()).isNull();
    }

    @Test
    void setAndGetValues() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();

        props.setPathPattern("/logout");
        assertThat(props.getPathPattern()).isEqualTo("/logout");

        props.setLocalLogout(false);
        assertThat(props.isLocalLogout()).isFalse();

        props.setCentralLogout(false);
        assertThat(props.isCentralLogout()).isFalse();

        props.setDestroySession(false);
        assertThat(props.isDestroySession()).isFalse();

        props.setDefaultUrl("https://example.com");
        assertThat(props.getDefaultUrl()).isEqualTo("https://example.com");
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jLogoutProperties.PREFIX).isEqualTo("pac4j.logout");
    }

    @Test
    void toStringContainsFields() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        props.setLocalLogout(false);
        String str = props.toString();
        assertThat(str).contains("localLogout=false");
    }
}
