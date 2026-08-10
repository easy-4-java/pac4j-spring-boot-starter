package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jCasProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jCasPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jCasProperties props = new Pac4jCasProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getLoginUrl()).isNull();
        assertThat(props.getLogoutUrl()).isNull();
        assertThat(props.getRestUrl()).isNull();
        assertThat(props.getPrefixUrl()).isNull();
        assertThat(props.isAcceptAnyProxy()).isFalse();
        assertThat(props.getAllowedProxyChains()).isNull();
        assertThat(props.getEncoding()).isEqualTo("UTF-8");
        assertThat(props.isGateway()).isFalse();
        assertThat(props.isRenew()).isFalse();
        assertThat(props.getServiceUrl()).isNull();
        assertThat(props.getTolerance()).isEqualTo(1000L);
        assertThat(props.getMethod()).isNull();
        assertThat(props.isCasClient()).isFalse();
        assertThat(props.getCasClientName()).isEqualTo("cas");
        assertThat(props.isDirectCasClient()).isFalse();
        assertThat(props.getDirectCasClientName()).isEqualTo("direct-cas");
        assertThat(props.isDirectCasProxyClient()).isFalse();
        assertThat(props.getDirectCasProxyClientName()).isEqualTo("direct-cas-proxy");
        assertThat(props.isCasRestBasicAuthClient()).isFalse();
        assertThat(props.getCasRestBasicAuthClientName()).isEqualTo("cas-rest-basic");
        assertThat(props.isCasRestFormClient()).isFalse();
        assertThat(props.getCasRestFormClientName()).isEqualTo("cas-rest-form");
    }

    @Test
    void setAndGetValues() {
        Pac4jCasProperties props = new Pac4jCasProperties();

        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        props.setLoginUrl("https://cas.example.com/login");
        assertThat(props.getLoginUrl()).isEqualTo("https://cas.example.com/login");

        props.setLogoutUrl("https://cas.example.com/logout");
        assertThat(props.getLogoutUrl()).isEqualTo("https://cas.example.com/logout");

        props.setRestUrl("https://cas.example.com/v1/tickets");
        assertThat(props.getRestUrl()).isEqualTo("https://cas.example.com/v1/tickets");

        props.setPrefixUrl("https://cas.example.com");
        assertThat(props.getPrefixUrl()).isEqualTo("https://cas.example.com");

        props.setAcceptAnyProxy(true);
        assertThat(props.isAcceptAnyProxy()).isTrue();

        props.setAllowedProxyChains("https://proxy.example.com");
        assertThat(props.getAllowedProxyChains()).isEqualTo("https://proxy.example.com");

        props.setEncoding("ISO-8859-1");
        assertThat(props.getEncoding()).isEqualTo("ISO-8859-1");

        props.setGateway(true);
        assertThat(props.isGateway()).isTrue();

        props.setRenew(true);
        assertThat(props.isRenew()).isTrue();

        props.setServiceUrl("https://app.example.com");
        assertThat(props.getServiceUrl()).isEqualTo("https://app.example.com");

        props.setTolerance(5000L);
        assertThat(props.getTolerance()).isEqualTo(5000L);

        props.setCasClient(true);
        assertThat(props.isCasClient()).isTrue();

        props.setDirectCasClient(true);
        assertThat(props.isDirectCasClient()).isTrue();

        props.setDirectCasProxyClient(true);
        assertThat(props.isDirectCasProxyClient()).isTrue();

        props.setCasRestBasicAuthClient(true);
        assertThat(props.isCasRestBasicAuthClient()).isTrue();

        props.setCasRestFormClient(true);
        assertThat(props.isCasRestFormClient()).isTrue();
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jCasProperties.PREFIX).isEqualTo("pac4j.cas");
    }

    @Test
    void rememberMeAttributeNameConstant() {
        assertThat(Pac4jCasProperties.DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME)
                .isEqualTo("longTermAuthenticationRequestTokenUsed");
    }
}
