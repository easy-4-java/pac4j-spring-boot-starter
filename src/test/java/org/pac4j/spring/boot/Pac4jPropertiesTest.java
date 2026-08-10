package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jProperties props = new Pac4jProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getLoginUrl()).isNull();
        assertThat(props.getCallbackUrl()).isNull();
        assertThat(props.getAllowedIpRegexpPattern()).isNull();
        assertThat(props.getAllowedHttpMethods()).isNull();
        assertThat(props.isCallbackUrlFixed()).isFalse();
        assertThat(props.getClients()).isNull();
        assertThat(props.getClientParameterName()).isEqualTo("client_name");
        assertThat(props.getDefaultClientName()).isNull();
        assertThat(props.isMultiProfile()).isFalse();
        assertThat(props.isCompleteRelativeUrl()).isFalse();
        assertThat(props.getCustomParams()).isEmpty();
        assertThat(props.getMatchers()).isNull();
    }

    @Test
    void setAndGetValues() {
        Pac4jProperties props = new Pac4jProperties();

        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        props.setLoginUrl("https://login.example.com");
        assertThat(props.getLoginUrl()).isEqualTo("https://login.example.com");

        props.setCallbackUrl("https://callback.example.com");
        assertThat(props.getCallbackUrl()).isEqualTo("https://callback.example.com");

        props.setAllowedIpRegexpPattern("192\\.168\\..*");
        assertThat(props.getAllowedIpRegexpPattern()).isEqualTo("192\\.168\\..*");

        props.setAllowedHttpMethods(new String[]{"GET", "POST"});
        assertThat(props.getAllowedHttpMethods()).containsExactly("GET", "POST");

        props.setCallbackUrlFixed(true);
        assertThat(props.isCallbackUrlFixed()).isTrue();

        props.setClients("FormClient");
        assertThat(props.getClients()).isEqualTo("FormClient");

        props.setClientParameterName("client_id");
        assertThat(props.getClientParameterName()).isEqualTo("client_id");

        props.setDefaultClientName("default");
        assertThat(props.getDefaultClientName()).isEqualTo("default");

        props.setMultiProfile(true);
        assertThat(props.isMultiProfile()).isTrue();

        props.setCompleteRelativeUrl(true);
        assertThat(props.isCompleteRelativeUrl()).isTrue();

        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        props.setCustomParams(params);
        assertThat(props.getCustomParams()).containsEntry("key", "value");

        props.setMatchers("myMatcher");
        assertThat(props.getMatchers()).isEqualTo("myMatcher");
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jProperties.PREFIX).isEqualTo("pac4j");
    }

    @Test
    void toStringContainsFields() {
        Pac4jProperties props = new Pac4jProperties();
        props.setEnabled(true);
        props.setCallbackUrl("https://cb.example.com");
        String str = props.toString();
        assertThat(str).contains("enabled=true");
        assertThat(str).contains("callbackUrl=https://cb.example.com");
    }
}
