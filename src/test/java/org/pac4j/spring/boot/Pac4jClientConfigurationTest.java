package org.pac4j.spring.boot;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.pac4j.core.client.Client;
import org.pac4j.core.context.CallContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.ext.authentication.AuthenticatingFailureCounter;
import org.pac4j.core.ext.authentication.UsernamePasswordCaptchaAuthenticator;
import org.pac4j.core.ext.authentication.captcha.CaptchaResolver;
import org.pac4j.core.ext.credentials.UsernamePasswordCaptchaCredentials;
import org.pac4j.core.ext.exception.CaptchaNotFoundException;
import org.pac4j.core.ext.exception.OverRetryRemindException;
import org.pac4j.http.client.direct.CookieClient;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.oauth.client.GitHubClient;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Pac4jClientConfigurationTest {

    @Test
    void bindsHttpClientParametersAndRegistersOnlyEnabledClients() {
        var runner = new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(Pac4jHttpConfiguration.class));
        runner.run(context -> assertThat(context).doesNotHaveBean(FormClient.class));
        runner.withPropertyValues(
                        "pac4j.http.enabled=true",
                        "pac4j.http.form-client=true",
                        "pac4j.http.direct-basic-auth-client=true",
                        "pac4j.http.indirect-basic-auth-client=true",
                        "pac4j.http.login-url=/sign-in",
                        "pac4j.http.username-parameter=account",
                        "pac4j.http.password-parameter=passcode",
                        "pac4j.http.realm-name=fixture")
                .run(context -> {
                    assertThat(context).hasNotFailed()
                            .hasSingleBean(FormClient.class)
                            .hasSingleBean(DirectBasicAuthClient.class)
                            .hasSingleBean(IndirectBasicAuthClient.class);
                    var form = context.getBean(FormClient.class);
                    assertThat(form.getLoginUrl()).isEqualTo("/sign-in");
                    assertThat(form.getUsernameParameter()).isEqualTo("account");
                    assertThat(form.getPasswordParameter()).isEqualTo("passcode");
                });
    }

    @Test
    void wiresJwtClientsToTheConfiguredAuthenticator() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(Pac4jBaseConfiguration.class, Pac4jJwtConfiguration.class))
                .withBean(CaptchaResolver.class, () -> mock(CaptchaResolver.class))
                .withBean(AuthenticatingFailureCounter.class, () -> mock(AuthenticatingFailureCounter.class))
                .withPropertyValues(
                        "pac4j.enabled=true",
                        "pac4j.jwt.enabled=true",
                        "pac4j.jwt.sign-secret=00000000000000000000000000000000",
                        "pac4j.jwt.encrypt-secret=11111111111111111111111111111111",
                        "pac4j.jwt.authorization-header-name=X-Fixture-Token",
                        "pac4j.jwt.authorization-header-prefix=Bearer ",
                        "pac4j.jwt.authorization-cookie-name=fixture-cookie",
                        "pac4j.jwt.authorization-param-name=fixture-query",
                        "pac4j.jwt.login-url=/sign-in",
                        "pac4j.jwt.callback-url=https://app.example.invalid/callback",
                        "pac4j.jwt.default-client-name=fixture-form",
                        "pac4j.jwt.captcha-required=true",
                        "pac4j.jwt.retry-times-key-attribute=fixture-retries",
                        "pac4j.jwt.retry-times-when-access-denied=2")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    var authenticator = context.getBean(JwtAuthenticator.class);
                    var header = context.getBean(HeaderClient.class);
                    assertThat(header.getHeaderName()).isEqualTo("X-Fixture-Token");
                    assertThat(header.getAuthenticator()).isSameAs(authenticator);
                    assertThat(context.getBean(CookieClient.class).getCookieName()).isEqualTo("fixture-cookie");
                    assertThat(context.getBean(ParameterClient.class).getParameterName()).isEqualTo("fixture-query");
                    assertThat(context).hasBean("jwtAuthcClient");

                    var profile = new CommonProfile();
                    profile.setId("fixture-user");
                    var generator = new JwtGenerator(context.getBean(SignatureConfiguration.class),
                            context.getBean(EncryptionConfiguration.class));
                    assertThat(authenticator.validateToken(generator.generate(profile)).getId())
                            .isEqualTo("fixture-user");

                    var formAuthenticator = context.getBean(UsernamePasswordCaptchaAuthenticator.class);
                    var call = new CallContext(mock(WebContext.class), mock(SessionStore.class));
                    var missingCaptcha = new UsernamePasswordCaptchaCredentials("fixture", "fixture");
                    assertThatThrownBy(() -> formAuthenticator.validate(call, missingCaptcha))
                            .isInstanceOf(CaptchaNotFoundException.class);
                    when(context.getBean(AuthenticatingFailureCounter.class)
                            .get(call.webContext(), call.sessionStore(), "fixture-retries")).thenReturn(2);
                    assertThatThrownBy(() -> formAuthenticator.validate(call, missingCaptcha))
                            .isInstanceOf(OverRetryRemindException.class);
                });
    }

    @Test
    void registersConfiguredOAuthProvidersWithTheirOwnCredentials() {
        Map<String, String> providers = Map.ofEntries(
                Map.entry("baidu", "baiduClient"), Map.entry("bitbucket", "bitbucketClient"),
                Map.entry("cas", "casOAuthWrapperClient"), Map.entry("dropbox", "dropboxClient"),
                Map.entry("facebook", "facebookClient"), Map.entry("foursquare", "foursquareClient"),
                Map.entry("github", "githubClient"), Map.entry("google2", "google2Client"),
                Map.entry("linkedin2", "linkedin2Client"), Map.entry("ok", "okClient"),
                Map.entry("oschina", "oschinaClient"), Map.entry("paypal", "paypalClient"),
                Map.entry("qq", "qqClient"), Map.entry("strava", "stravaClient"),
                Map.entry("twitter", "twitterClient"), Map.entry("vk", "vkClient"),
                Map.entry("weibo", "weiboClient"), Map.entry("windowslive", "windowsliveClient"),
                Map.entry("wordpress", "wordpressClient"), Map.entry("yahoo", "yahooClient"),
                Map.entry("yiban", "yibanClient"));
        var values = new ArrayList<String>();
        values.add("pac4j.enabled=true");
        values.add("pac4j.oauth.enabled=true");
        providers.forEach((provider, bean) -> {
            String prefix = "pac4j.oauth." + provider;
            values.add(prefix + "=true");
            values.add(prefix + ".name=" + provider + "-fixture");
            values.add(prefix + ".key=" + provider + "-key");
            values.add(prefix + ".secret=" + provider + "-secret");
            values.add(prefix + ".callback-url=https://app.example.invalid/callback");
        });
        values.add("pac4j.oauth.cas.cas-oauth-url=https://cas.example.invalid/oauth");

        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(Pac4jBaseConfiguration.class, Pac4jOAuthConfiguration.class))
                .withPropertyValues(values.toArray(String[]::new))
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    providers.values().forEach(name -> assertThat(context).hasBean(name));
                    assertThat(context.getBean(GitHubClient.class).getName()).isEqualTo("github-fixture");
                    var yiban = context.getBean("yibanClient", Client.class);
                    assertThat(yiban.getName()).isEqualTo("yiban-fixture");
                });
    }
}
