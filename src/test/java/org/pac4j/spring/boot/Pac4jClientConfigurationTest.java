package org.pac4j.spring.boot;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.core.client.Client;
import org.pac4j.oauth.client.GitHubClient;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

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
