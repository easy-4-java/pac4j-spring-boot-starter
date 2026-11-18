package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.oauth.client.YibanClient;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class Pac4jOAuthConfigurationTest {

    @Test
    void yibanClientUsesItsOwnCredentials() {
        Pac4jOAuthProperties properties = new Pac4jOAuthProperties();
        properties.getGithub().setKey("github-key");
        properties.getGithub().setSecret("github-secret");
        properties.getYiban().setKey("yiban-key");
        properties.getYiban().setSecret("yiban-secret");
        properties.getYiban().setName("yiban");
        Pac4jOAuthConfiguration configuration = new Pac4jOAuthConfiguration();
        ReflectionTestUtils.setField(configuration, "oauthProperties", properties);

        YibanClient client = configuration.yibanClient(mock(AjaxRequestResolver.class), mock(UrlResolver.class));

        assertThat(client.getKey()).isEqualTo("yiban-key");
        assertThat(client.getName()).isEqualTo("yiban");
    }

    @Test
    void providerFactoriesKeepEachProvidersCredentials() {
        Pac4jOAuthProperties properties = new Pac4jOAuthProperties();
        seed(properties.getBaidu(), "baidu");
        seed(properties.getBitbucket(), "bitbucket");
        seed(properties.getCas(), "cas");
        properties.getCas().setCasOAuthUrl("https://cas.example.test/oauth");
        seed(properties.getDropbox(), "dropbox");
        seed(properties.getFacebook(), "facebook");
        seed(properties.getFoursquare(), "foursquare");
        seed(properties.getGithub(), "github");
        seed(properties.getGoogle2(), "google2");
        seed(properties.getLinkedin2(), "linkedin2");
        seed(properties.getOk(), "ok");
        properties.getOk().setPublicKey("public-key");
        seed(properties.getOschina(), "oschina");
        seed(properties.getPaypal(), "paypal");
        seed(properties.getQq(), "qq");
        seed(properties.getStrava(), "strava");
        seed(properties.getTwitter(), "twitter");
        seed(properties.getVk(), "vk");
        seed(properties.getWeibo(), "weibo");
        seed(properties.getWeixin(), "weixin");
        seed(properties.getWindowslive(), "windowslive");
        seed(properties.getWordpress(), "wordpress");
        seed(properties.getYahoo(), "yahoo");
        seed(properties.getYiban(), "yiban");

        Pac4jOAuthConfiguration configuration = new Pac4jOAuthConfiguration();
        ReflectionTestUtils.setField(configuration, "oauthProperties", properties);
        AjaxRequestResolver ajax = mock(AjaxRequestResolver.class);
        UrlResolver url = mock(UrlResolver.class);

        assertThat(configuration.baiduClient(ajax, url).getKey()).isEqualTo("baidu-key");
        assertThat(configuration.bitbucketClient(ajax, url).getKey()).isEqualTo("bitbucket-key");
        assertThat(configuration.casOAuthWrapperClient(ajax, url).getKey()).isEqualTo("cas-key");
        assertThat(configuration.dropboxClient(ajax, url).getKey()).isEqualTo("dropbox-key");
        assertThat(configuration.facebookClient(ajax, url).getKey()).isEqualTo("facebook-key");
        assertThat(configuration.foursquareClient(ajax, url).getKey()).isEqualTo("foursquare-key");
        assertThat(configuration.githubClient(ajax, url).getKey()).isEqualTo("github-key");
        assertThat(configuration.google2Client(ajax, url).getKey()).isEqualTo("google2-key");
        assertThat(configuration.linkedin2Client(ajax, url).getKey()).isEqualTo("linkedin2-key");
        assertThat(configuration.okClient(ajax, url).getKey()).isEqualTo("ok-key");
        assertThat(configuration.oschinaClient(ajax, url).getKey()).isEqualTo("oschina-key");
        assertThat(configuration.paypalClient(ajax, url).getKey()).isEqualTo("paypal-key");
        assertThat(configuration.qqClient(ajax, url).getKey()).isEqualTo("qq-key");
        assertThat(configuration.stravaClient(ajax, url).getKey()).isEqualTo("strava-key");
        assertThat(configuration.twitterClient(ajax, url).getKey()).isEqualTo("twitter-key");
        assertThat(configuration.vkClient(ajax, url).getKey()).isEqualTo("vk-key");
        assertThat(configuration.weiboClient(ajax, url).getKey()).isEqualTo("weibo-key");
        assertThat(configuration.wechatClient(ajax, url).getKey()).isEqualTo("weixin-key");
        assertThat(configuration.windowsliveClient(ajax, url).getKey()).isEqualTo("windowslive-key");
        assertThat(configuration.wordpressClient(ajax, url).getKey()).isEqualTo("wordpress-key");
        assertThat(configuration.yahooClient(ajax, url).getKey()).isEqualTo("yahoo-key");
        assertThat(configuration.yibanClient(ajax, url).getKey()).isEqualTo("yiban-key");
    }

    private void seed(Pac4jOAuthClientProperties properties, String provider) {
        properties.setKey(provider + "-key");
        properties.setSecret(provider + "-secret");
        properties.setName(provider);
    }
}
