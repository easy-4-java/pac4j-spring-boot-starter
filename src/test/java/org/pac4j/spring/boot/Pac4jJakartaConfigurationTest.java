package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.cas.client.CasClient;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.pac4j.jee.http.adapter.JEEHttpActionAdapter;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class Pac4jJakartaConfigurationTest {

    @Test
    void configuresTheNativeJakartaAdapterAndCasClient() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(
                        Pac4jBaseConfiguration.class, Pac4jAutoConfiguration.class, Pac4jCasConfiguration.class))
                .withPropertyValues(
                        "pac4j.enabled=true",
                        "pac4j.clients=cas-fixture",
                        "pac4j.callback-url=https://app.example.invalid/callback",
                        "pac4j.logout.destroy-session=true",
                        "pac4j.cas.enabled=true",
                        "pac4j.cas.cas-client=true",
                        "pac4j.cas.cas-client-name=cas-fixture",
                        "pac4j.cas.login-url=https://cas.example.invalid/login",
                        "pac4j.cas.prefix-url=https://cas.example.invalid/",
                        "pac4j.cas.service-url=https://app.example.invalid/callback")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context.getBean(HttpActionAdapter.class)).isSameAs(JEEHttpActionAdapter.INSTANCE);
                    assertThat(context.getBean(DefaultLogoutHandler.class).isDestroySession()).isTrue();
                    var client = context.getBean(CasClient.class);
                    assertThat(client.getName()).isEqualTo("cas-fixture");
                    assertThat(client.getConfiguration().getLoginUrl()).isEqualTo("https://cas.example.invalid/login");
                    assertThat(context.getBean(Config.class).getClients().getDefaultSecurityClients())
                            .isEqualTo("cas-fixture");
                });
    }
}
