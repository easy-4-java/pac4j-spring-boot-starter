package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jHttpProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jHttpPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jHttpProperties props = new Pac4jHttpProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getLoginUrl()).isNull();
        assertThat(props.getUsernameParameter()).isEqualTo("username");
        assertThat(props.getPasswordParameter()).isEqualTo("password");
        assertThat(props.isFormClient()).isFalse();
        assertThat(props.getFormClientName()).isEqualTo("form");
        assertThat(props.isIndirectBasicAuthClient()).isFalse();
        assertThat(props.getIndirectBasicAuthClientName()).isEqualTo("indirect-basic-auth");
        assertThat(props.getRealmName()).isNull();
        assertThat(props.isDirectBasicAuthClient()).isFalse();
        assertThat(props.getDirectBasicAuthClientName()).isEqualTo("direct-basic-auth");
    }

    @Test
    void setAndGetValues() {
        Pac4jHttpProperties props = new Pac4jHttpProperties();

        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        props.setLoginUrl("https://login.example.com");
        assertThat(props.getLoginUrl()).isEqualTo("https://login.example.com");

        props.setUsernameParameter("user");
        assertThat(props.getUsernameParameter()).isEqualTo("user");

        props.setPasswordParameter("pass");
        assertThat(props.getPasswordParameter()).isEqualTo("pass");

        props.setFormClient(true);
        assertThat(props.isFormClient()).isTrue();

        props.setFormClientName("myForm");
        assertThat(props.getFormClientName()).isEqualTo("myForm");

        props.setIndirectBasicAuthClient(true);
        assertThat(props.isIndirectBasicAuthClient()).isTrue();

        props.setIndirectBasicAuthClientName("myBasicAuth");
        assertThat(props.getIndirectBasicAuthClientName()).isEqualTo("myBasicAuth");

        props.setRealmName("MyRealm");
        assertThat(props.getRealmName()).isEqualTo("MyRealm");

        props.setDirectBasicAuthClient(true);
        assertThat(props.isDirectBasicAuthClient()).isTrue();

        props.setDirectBasicAuthClientName("myDirectAuth");
        assertThat(props.getDirectBasicAuthClientName()).isEqualTo("myDirectAuth");
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jHttpProperties.PREFIX).isEqualTo("pac4j.http");
    }
}
