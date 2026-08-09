package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthClientProperties}, {@link Pac4jOAuthFacebookClientProperties},
 * {@link Pac4jOAuthCasClientProperties}, {@link Pac4jOAuthOkClientProperties},
 * {@link Pac4jOAuthStravaClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthClientPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();
        assertThat(props.getCallbackUrl()).isNull();
        assertThat(props.getName()).isNull();
        assertThat(props.getDesc()).isNull();
        assertThat(props.getLogoUrl()).isNull();
        assertThat(props.getKey()).isNull();
        assertThat(props.getSecret()).isNull();
        assertThat(props.isTokenAsHeader()).isFalse();
        assertThat(props.getResponseType()).isEqualTo("code");
        assertThat(props.getScope()).isNull();
        assertThat(props.isHasGrantType()).isFalse();
        assertThat(props.getConnectTimeout()).isGreaterThan(0);
        assertThat(props.getReadTimeout()).isGreaterThan(0);
        assertThat(props.getCustomParams()).isEmpty();
        assertThat(props.getProfileAttrs()).isEmpty();
        assertThat(props.isWithState()).isFalse();
        assertThat(props.getStateData()).isNull();
    }

    @Test
    void setAndGetValues() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();

        props.setCallbackUrl("https://cb.example.com");
        assertThat(props.getCallbackUrl()).isEqualTo("https://cb.example.com");

        props.setName("testClient");
        assertThat(props.getName()).isEqualTo("testClient");

        props.setKey("myKey");
        assertThat(props.getKey()).isEqualTo("myKey");

        props.setSecret("mySecret");
        assertThat(props.getSecret()).isEqualTo("mySecret");

        props.setTokenAsHeader(true);
        assertThat(props.isTokenAsHeader()).isTrue();

        props.setResponseType("token");
        assertThat(props.getResponseType()).isEqualTo("token");

        props.setScope("read write");
        assertThat(props.getScope()).isEqualTo("read write");

        props.setWithState(true);
        assertThat(props.isWithState()).isTrue();

        Map<String, String> params = new HashMap<>();
        params.put("k", "v");
        props.setCustomParams(params);
        assertThat(props.getCustomParams()).containsEntry("k", "v");
    }

    @Test
    void responseTypeCodeConstant() {
        assertThat(Pac4jOAuthClientProperties.RESPONSE_TYPE_CODE).isEqualTo("code");
    }

    @Test
    void facebookClientProperties() {
        Pac4jOAuthFacebookClientProperties fbProps = new Pac4jOAuthFacebookClientProperties();
        assertThat(fbProps.getFields()).isNotEmpty();
        assertThat(fbProps.getLimit()).isGreaterThanOrEqualTo(0);
        assertThat(fbProps.isRequiresExtendedToken()).isFalse();
        assertThat(fbProps.isUseAppsecretProof()).isFalse();

        fbProps.setFields("id,name");
        assertThat(fbProps.getFields()).isEqualTo("id,name");

        fbProps.setLimit(100);
        assertThat(fbProps.getLimit()).isEqualTo(100);

        fbProps.setRequiresExtendedToken(true);
        assertThat(fbProps.isRequiresExtendedToken()).isTrue();

        fbProps.setUseAppsecretProof(true);
        assertThat(fbProps.isUseAppsecretProof()).isTrue();
    }

    @Test
    void casClientProperties() {
        Pac4jOAuthCasClientProperties casProps = new Pac4jOAuthCasClientProperties();
        assertThat(casProps.getCasOAuthUrl()).isNull();
        assertThat(casProps.getCasLogoutUrl()).isNull();
        assertThat(casProps.isSpringSecurityCompliant()).isFalse();
        assertThat(casProps.isImplicitFlow()).isFalse();

        casProps.setCasOAuthUrl("https://cas.example.com/oauth2.0");
        assertThat(casProps.getCasOAuthUrl()).isEqualTo("https://cas.example.com/oauth2.0");

        casProps.setCasLogoutUrl("https://cas.example.com/logout");
        assertThat(casProps.getCasLogoutUrl()).isEqualTo("https://cas.example.com/logout");

        casProps.setSpringSecurityCompliant(true);
        assertThat(casProps.isSpringSecurityCompliant()).isTrue();

        casProps.setImplicitFlow(true);
        assertThat(casProps.isImplicitFlow()).isTrue();
    }

    @Test
    void okClientProperties() {
        Pac4jOAuthOkClientProperties okProps = new Pac4jOAuthOkClientProperties();
        assertThat(okProps.getPublicKey()).isNull();

        okProps.setPublicKey("myPublicKey");
        assertThat(okProps.getPublicKey()).isEqualTo("myPublicKey");
    }

    @Test
    void stravaClientProperties() {
        Pac4jOAuthStravaClientProperties stravaProps = new Pac4jOAuthStravaClientProperties();
        assertThat(stravaProps.getApprovalPrompt()).isEqualTo("auto");

        stravaProps.setApprovalPrompt("force");
        assertThat(stravaProps.getApprovalPrompt()).isEqualTo("force");
    }
}
