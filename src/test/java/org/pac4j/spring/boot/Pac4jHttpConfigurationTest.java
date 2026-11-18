package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class Pac4jHttpConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(Pac4jHttpConfiguration.class);

    @Test
    void leavesHttpClientsDisabledByDefault() {
        runner.run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean(FormClient.class);
            assertThat(context).doesNotHaveBean(IndirectBasicAuthClient.class);
            assertThat(context).doesNotHaveBean(DirectBasicAuthClient.class);
        });
    }

    @Test
    void registersOnlyTheEnabledHttpClientsWithBoundOptions() {
        runner.withPropertyValues(
                        "pac4j.http.enabled=true",
                        "pac4j.http.form-client=true",
                        "pac4j.http.indirect-basic-auth-client=true",
                        "pac4j.http.direct-basic-auth-client=true",
                        "pac4j.http.login-url=/login",
                        "pac4j.http.realm-name=example")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(FormClient.class);
                    assertThat(context).hasSingleBean(IndirectBasicAuthClient.class);
                    assertThat(context).hasSingleBean(DirectBasicAuthClient.class);
                    Pac4jHttpProperties properties = context.getBean(Pac4jHttpProperties.class);
                    assertThat(properties.getLoginUrl()).isEqualTo("/login");
                    assertThat(properties.getRealmName()).isEqualTo("example");
                });
    }
}
