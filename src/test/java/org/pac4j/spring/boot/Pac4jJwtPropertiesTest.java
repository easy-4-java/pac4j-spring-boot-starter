package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jJwtProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class Pac4jJwtPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jJwtProperties props = new Pac4jJwtProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getEncryptSecret()).isNull();
        assertThat(props.getSignSecret()).isNull();
        assertThat(props.getJweAlgorithm()).isEqualTo(Pac4jJwtProperties.JWEAlgorithm.DIR);
        assertThat(props.getJwsAlgorithm()).isEqualTo(Pac4jJwtProperties.JWSAlgorithm.HS256);
        assertThat(props.getEncryption()).isEqualTo(Pac4jJwtProperties.EncryptionMethod.A256GCM);
        assertThat(props.getCustomProperties()).isEmpty();
        assertThat(props.getLoginUrl()).isNull();
        assertThat(props.getLogoutUrl()).isNull();
        assertThat(props.getCallbackUrl()).isNull();
        assertThat(props.getClientParameterName()).isEqualTo("client_name");
        assertThat(props.getDefaultClientName()).isNull();
        assertThat(props.getCaptchaParamName()).isEqualTo("captcha");
        assertThat(props.isCaptchaRequired()).isFalse();
        assertThat(props.getUsernameParameterName()).isEqualTo("username");
        assertThat(props.getPasswordParameterName()).isEqualTo("password");
        assertThat(props.isPostOnly()).isTrue();
        assertThat(props.getRetryTimesWhenAccessDenied()).isEqualTo(3);
        assertThat(props.getAuthorizationHeaderName()).isEqualTo("X-Authorization");
        assertThat(props.getAuthorizationHeaderPrefix()).isEmpty();
        assertThat(props.getHeaderClientName()).isEqualTo("jwt-header");
        assertThat(props.getAuthorizationParamName()).isEqualTo("token");
        assertThat(props.isSupportGetRequest()).isTrue();
        assertThat(props.isSupportPostRequest()).isTrue();
        assertThat(props.getParamClientName()).isEqualTo("jwt-param");
        assertThat(props.getAuthorizationCookieName()).isEqualTo("token");
        assertThat(props.getCookieClientName()).isEqualTo("jwt-cookie");
    }

    @Test
    void setAndGetValues() {
        Pac4jJwtProperties props = new Pac4jJwtProperties();

        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        props.setEncryptSecret("encryptSecret123");
        assertThat(props.getEncryptSecret()).isEqualTo("encryptSecret123");

        props.setSignSecret("signSecret123");
        assertThat(props.getSignSecret()).isEqualTo("signSecret123");

        props.setJweAlgorithm(Pac4jJwtProperties.JWEAlgorithm.A128KW);
        assertThat(props.getJweAlgorithm()).isEqualTo(Pac4jJwtProperties.JWEAlgorithm.A128KW);

        props.setJwsAlgorithm(Pac4jJwtProperties.JWSAlgorithm.RS256);
        assertThat(props.getJwsAlgorithm()).isEqualTo(Pac4jJwtProperties.JWSAlgorithm.RS256);

        props.setEncryption(Pac4jJwtProperties.EncryptionMethod.A128GCM);
        assertThat(props.getEncryption()).isEqualTo(Pac4jJwtProperties.EncryptionMethod.A128GCM);

        Map<String, Object> customProps = new HashMap<>();
        customProps.put("key", "value");
        props.setCustomProperties(customProps);
        assertThat(props.getCustomProperties()).containsEntry("key", "value");

        props.setLoginUrl("https://login.example.com");
        assertThat(props.getLoginUrl()).isEqualTo("https://login.example.com");

        props.setCallbackUrl("https://callback.example.com");
        assertThat(props.getCallbackUrl()).isEqualTo("https://callback.example.com");

        props.setCaptchaRequired(true);
        assertThat(props.isCaptchaRequired()).isTrue();

        props.setPostOnly(false);
        assertThat(props.isPostOnly()).isFalse();

        props.setRetryTimesWhenAccessDenied(5);
        assertThat(props.getRetryTimesWhenAccessDenied()).isEqualTo(5);

        props.setAuthorizationHeaderName("Auth-Header");
        assertThat(props.getAuthorizationHeaderName()).isEqualTo("Auth-Header");

        props.setSupportGetRequest(false);
        assertThat(props.isSupportGetRequest()).isFalse();

        props.setSupportPostRequest(false);
        assertThat(props.isSupportPostRequest()).isFalse();
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jJwtProperties.PREFIX).isEqualTo("pac4j.jwt");
    }

    @Test
    void authHeaderConstant() {
        assertThat(Pac4jJwtProperties.AUTHORIZATION_HEADER).isEqualTo("X-Authorization");
        assertThat(Pac4jJwtProperties.AUTHORIZATION_PARAM).isEqualTo("token");
    }

    @Test
    void captchaSessionKeyConstant() {
        assertThat(Pac4jJwtProperties.DEFAULT_SESSION_CAPTCHA_KEY).isEqualTo("KAPTCHA_SESSION_KEY");
    }

    @Test
    void jweAlgorithmEnumValues() {
        Pac4jJwtProperties.JWEAlgorithm[] values = Pac4jJwtProperties.JWEAlgorithm.values();
        assertThat(values).hasSize(15);
        assertThat(Pac4jJwtProperties.JWEAlgorithm.valueOf("RSA_OAEP_256")).isNotNull();
        assertThat(Pac4jJwtProperties.JWEAlgorithm.DIR.value()).isEqualTo("dir");
    }

    @Test
    void jwsAlgorithmEnumValues() {
        Pac4jJwtProperties.JWSAlgorithm[] values = Pac4jJwtProperties.JWSAlgorithm.values();
        assertThat(values).hasSize(14);
        assertThat(Pac4jJwtProperties.JWSAlgorithm.valueOf("HS256")).isNotNull();
        assertThat(Pac4jJwtProperties.JWSAlgorithm.RS256.value()).isEqualTo("RS256");
    }

    @Test
    void encryptionMethodEnumValues() {
        Pac4jJwtProperties.EncryptionMethod[] values = Pac4jJwtProperties.EncryptionMethod.values();
        assertThat(values).hasSize(8);
        assertThat(Pac4jJwtProperties.EncryptionMethod.valueOf("A128GCM")).isNotNull();
        assertThat(Pac4jJwtProperties.EncryptionMethod.A256GCM.value()).isEqualTo("A256GCM");
    }
}
