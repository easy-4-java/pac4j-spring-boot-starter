package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pac4j.core.ext.authentication.AuthenticatingFailureCounter;
import org.pac4j.core.ext.authentication.UsernamePasswordCaptchaAuthenticator;
import org.pac4j.core.ext.authentication.captcha.CaptchaResolver;
import org.pac4j.core.ext.credentials.extractor.UsernamePasswordCaptchaCredentialsExtractor;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.callback.CallbackUrlResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.http.client.direct.CookieClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;

/**
 * Tests for {@link Pac4jJwtConfiguration} bean methods.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jJwtConfigurationTest {

    private Pac4jJwtConfiguration config;
    private Pac4jJwtProperties jwtProps;

    @BeforeEach
    void setUp() {
        config = new Pac4jJwtConfiguration();
        jwtProps = new Pac4jJwtProperties();
        jwtProps.setEncryptSecret("myEncryptSecret12345678901234567890");
        jwtProps.setSignSecret("mySignSecret1234567890123456789012");
        jwtProps.setLoginUrl("https://login.example.com");
        jwtProps.setCallbackUrl("https://callback.example.com");
        jwtProps.setDefaultClientName("jwt-form");
        jwtProps.setUsernameParameterName("username");
        jwtProps.setPasswordParameterName("password");
        jwtProps.setCaptchaParamName("captcha");
        jwtProps.setPostOnly(true);

        try {
            var field = Pac4jJwtConfiguration.class.getDeclaredField("jwtProperties");
            field.setAccessible(true);
            field.set(config, jwtProps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void encryptionConfiguration() {
        EncryptionConfiguration encConfig = config.encryptionConfiguration();
        assertThat(encConfig).isNotNull();
        assertThat(encConfig).isInstanceOf(SecretEncryptionConfiguration.class);
    }

    @Test
    void signatureConfiguration() {
        SignatureConfiguration sigConfig = config.signatureConfiguration();
        assertThat(sigConfig).isNotNull();
        assertThat(sigConfig).isInstanceOf(SecretSignatureConfiguration.class);
    }

    @Test
    void jwtAuthenticator() {
        List<SignatureConfiguration> sigConfigs = new ArrayList<>();
        sigConfigs.add(new SecretSignatureConfiguration("secret1234567890123456789012345"));
        List<EncryptionConfiguration> encConfigs = new ArrayList<>();
        encConfigs.add(new SecretEncryptionConfiguration("encrypt123456789012345678901234"));

        JwtAuthenticator authenticator = config.jwtAuthenticator(sigConfigs, encConfigs);
        assertThat(authenticator).isNotNull();
    }

    @Test
    void jwtUpcAuthenticator() {
        CaptchaResolver captchaResolver = Mockito.mock(CaptchaResolver.class);
        AuthenticatingFailureCounter failureCounter = Mockito.mock(AuthenticatingFailureCounter.class);

        UsernamePasswordCaptchaAuthenticator authenticator = config.jwtUpcAuthenticator(captchaResolver, failureCounter);
        assertThat(authenticator).isNotNull();
    }

    @Test
    void jwtUpcCredentialsExtractor() {
        UsernamePasswordCaptchaCredentialsExtractor extractor = config.jwtUpcCredentialsExtractor();
        assertThat(extractor).isNotNull();
    }

    @Test
    void jwtAuthcClient() {
        AjaxRequestResolver ajaxResolver = new org.pac4j.core.http.ajax.DefaultAjaxRequestResolver();
        CallbackUrlResolver cbResolver = new org.pac4j.core.http.callback.QueryParameterCallbackUrlResolver();
        UrlResolver urlResolver = new org.pac4j.core.http.url.DefaultUrlResolver();
        CaptchaResolver captchaResolver = Mockito.mock(CaptchaResolver.class);
        AuthenticatingFailureCounter failureCounter = Mockito.mock(AuthenticatingFailureCounter.class);
        UsernamePasswordCaptchaAuthenticator authenticator = config.jwtUpcAuthenticator(captchaResolver, failureCounter);
        UsernamePasswordCaptchaCredentialsExtractor extractor = config.jwtUpcCredentialsExtractor();

        var client = config.jwtAuthcClient(ajaxResolver, cbResolver, authenticator, extractor, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void jwtCookieAuthzClient() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.setSignatureConfigurations(Collections.singletonList(new SecretSignatureConfiguration("secret1234567890123456789012345")));

        CookieClient client = config.jwtCookieAuthzClient(jwtAuthenticator);
        assertThat(client).isNotNull();
    }

    @Test
    void jwtHeaderAuthzClient() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.setSignatureConfigurations(Collections.singletonList(new SecretSignatureConfiguration("secret1234567890123456789012345")));

        HeaderClient client = config.jwtHeaderAuthzClient(jwtAuthenticator);
        assertThat(client).isNotNull();
    }

    @Test
    void jwtParamAuthzClient() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.setSignatureConfigurations(Collections.singletonList(new SecretSignatureConfiguration("secret1234567890123456789012345")));

        ParameterClient client = config.jwtParamAuthzClient(jwtAuthenticator);
        assertThat(client).isNotNull();
    }
}
