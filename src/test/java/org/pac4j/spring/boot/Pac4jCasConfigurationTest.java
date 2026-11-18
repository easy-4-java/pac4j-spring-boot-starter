package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.direct.DirectCasClient;
import org.pac4j.cas.client.direct.DirectCasProxyClient;
import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class Pac4jCasConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(Pac4jBaseConfiguration.class, Pac4jCasConfiguration.class);

    @Test
    void leavesCasClientDisabledByDefault() {
        runner.withPropertyValues("pac4j.enabled=true").run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean(CasClient.class);
        });
    }

    @Test
    void createsCasClientWithConfiguredServerAndServiceUrls() {
        runner.withPropertyValues(
                        "pac4j.enabled=true", "pac4j.cas.enabled=true", "pac4j.cas.cas-client=true",
                        "pac4j.cas.login-url=https://cas.example.test/login",
                        "pac4j.cas.service-url=https://app.example.test/callback")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(CasConfiguration.class);
                    assertThat(context).hasSingleBean(CasClient.class);
                    CasClient client = context.getBean(CasClient.class);
                    assertThat(client.getName()).isEqualTo("cas");
                    assertThat(client.getCallbackUrl()).contains("https://cas.example.test/login");
                });
    }

    @Test
    void registersDirectAndRestCasClientsWithConfiguredNames() {
        runner.withPropertyValues(
                        "pac4j.enabled=true", "pac4j.cas.enabled=true",
                        "pac4j.cas.login-url=https://cas.example.test/login",
                        "pac4j.cas.prefix-url=https://cas.example.test",
                        "pac4j.cas.direct-cas-client=true",
                        "pac4j.cas.direct-cas-proxy-client=true",
                        "pac4j.cas.cas-rest-basic-auth-client=true",
                        "pac4j.cas.cas-rest-form-client=true",
                        "pac4j.cas.direct-cas-client-name=my-direct",
                        "pac4j.cas.direct-cas-proxy-client-name=my-proxy",
                        "pac4j.cas.cas-rest-basic-auth-client-name=my-basic",
                        "pac4j.cas.cas-rest-form-client-name=my-form",
                        "pac4j.cas.header-name=X-CAS-Auth",
                        "pac4j.cas.prefix-header=Token ",
                        "pac4j.cas.username-parameter-name=user",
                        "pac4j.cas.password-parameter-name=pass")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context.getBean(DirectCasClient.class).getName()).isEqualTo("my-direct");
                    assertThat(context.getBean(DirectCasProxyClient.class).getName()).isEqualTo("my-proxy");
                    assertThat(context.getBean(CasRestBasicAuthClient.class).getName()).isEqualTo("my-basic");
                    assertThat(context.getBean(CasRestFormClient.class).getName()).isEqualTo("my-form");
                });
    }
}
