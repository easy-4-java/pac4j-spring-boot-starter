package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.core.ext.authentication.AuthenticatingFailureCounter;
import org.pac4j.core.ext.authentication.captcha.CaptchaResolver;
import org.pac4j.http.client.direct.CookieClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class Pac4jJwtConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(Pac4jBaseConfiguration.class, Pac4jJwtConfiguration.class)
            .withBean(CaptchaResolver.class, () -> mock(CaptchaResolver.class))
            .withBean(AuthenticatingFailureCounter.class, () -> mock(AuthenticatingFailureCounter.class));

    @Test
    void leavesJwtClientsDisabledByDefault() {
        runner.withPropertyValues("pac4j.enabled=true").run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean(JwtAuthenticator.class);
        });
    }

    @Test
    void registersJwtAuthenticationAndTokenClientsWhenEnabled() {
        runner.withPropertyValues(
                        "pac4j.enabled=true", "pac4j.jwt.enabled=true",
                        "pac4j.jwt.sign-secret=01234567890123456789012345678901",
                        "pac4j.jwt.encrypt-secret=01234567890123456789012345678901",
                        "pac4j.jwt.login-url=/login", "pac4j.jwt.callback-url=/callback")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(JwtAuthenticator.class);
                    assertThat(context).hasSingleBean(CookieClient.class);
                    assertThat(context).hasSingleBean(HeaderClient.class);
                    assertThat(context).hasSingleBean(ParameterClient.class);
                    assertThat(context.getBean(HeaderClient.class).getName()).isEqualTo("jwt-header");
                });
    }
}
