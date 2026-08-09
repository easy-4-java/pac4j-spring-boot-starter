package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.url.UrlResolver;

/**
 * Tests for {@link Pac4jOAuthConfiguration} bean methods.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthConfigurationTest {

    private Pac4jOAuthConfiguration config;
    private Pac4jOAuthProperties oauthProps;
    private AjaxRequestResolver ajaxResolver;
    private UrlResolver urlResolver;

    @BeforeEach
    void setUp() {
        config = new Pac4jOAuthConfiguration();
        oauthProps = new Pac4jOAuthProperties();

        // Set up OAuth client properties with keys and secrets
        Pac4jOAuthClientProperties baiduProps = new Pac4jOAuthClientProperties();
        baiduProps.setKey("baiduKey");
        baiduProps.setSecret("baiduSecret");
        baiduProps.setName("BaiduClient");
        oauthProps.setBaidu(baiduProps);

        Pac4jOAuthClientProperties bitbucketProps = new Pac4jOAuthClientProperties();
        bitbucketProps.setKey("bitbucketKey");
        bitbucketProps.setSecret("bitbucketSecret");
        bitbucketProps.setName("BitbucketClient");
        oauthProps.setBitbucket(bitbucketProps);

        Pac4jOAuthCasClientProperties casProps = new Pac4jOAuthCasClientProperties();
        casProps.setKey("casKey");
        casProps.setSecret("casSecret");
        casProps.setCasOAuthUrl("https://cas.example.com/oauth2.0");
        casProps.setName("CasOAuthClient");
        oauthProps.setCas(casProps);

        Pac4jOAuthClientProperties dropboxProps = new Pac4jOAuthClientProperties();
        dropboxProps.setKey("dropboxKey");
        dropboxProps.setSecret("dropboxSecret");
        dropboxProps.setName("DropboxClient");
        oauthProps.setDropbox(dropboxProps);

        Pac4jOAuthFacebookClientProperties fbProps = new Pac4jOAuthFacebookClientProperties();
        fbProps.setKey("fbKey");
        fbProps.setSecret("fbSecret");
        fbProps.setName("FacebookClient");
        fbProps.setFields("id,name");
        fbProps.setLimit(100);
        oauthProps.setFacebook(fbProps);

        Pac4jOAuthClientProperties foursquareProps = new Pac4jOAuthClientProperties();
        foursquareProps.setKey("4sqKey");
        foursquareProps.setSecret("4sqSecret");
        foursquareProps.setName("FoursquareClient");
        oauthProps.setFoursquare(foursquareProps);

        Pac4jOAuthClientProperties githubProps = new Pac4jOAuthClientProperties();
        githubProps.setKey("ghKey");
        githubProps.setSecret("ghSecret");
        githubProps.setName("GitHubClient");
        oauthProps.setGithub(githubProps);

        Pac4jOAuthClientProperties google2Props = new Pac4jOAuthClientProperties();
        google2Props.setKey("googleKey");
        google2Props.setSecret("googleSecret");
        google2Props.setName("Google2Client");
        oauthProps.setGoogle2(google2Props);

        Pac4jOAuthClientProperties linkedin2Props = new Pac4jOAuthClientProperties();
        linkedin2Props.setKey("linkedinKey");
        linkedin2Props.setSecret("linkedinSecret");
        linkedin2Props.setName("LinkedIn2Client");
        oauthProps.setLinkedin2(linkedin2Props);

        Pac4jOAuthOkClientProperties okProps = new Pac4jOAuthOkClientProperties();
        okProps.setKey("okKey");
        okProps.setSecret("okSecret");
        okProps.setPublicKey("okPublicKey");
        okProps.setName("OkClient");
        oauthProps.setOk(okProps);

        Pac4jOAuthClientProperties oschinaProps = new Pac4jOAuthClientProperties();
        oschinaProps.setKey("oschinaKey");
        oschinaProps.setSecret("oschinaSecret");
        oschinaProps.setName("OschinaClient");
        oauthProps.setOschina(oschinaProps);

        Pac4jOAuthClientProperties paypalProps = new Pac4jOAuthClientProperties();
        paypalProps.setKey("paypalKey");
        paypalProps.setSecret("paypalSecret");
        paypalProps.setName("PayPalClient");
        oauthProps.setPaypal(paypalProps);

        Pac4jOAuthClientProperties qqProps = new Pac4jOAuthClientProperties();
        qqProps.setKey("qqKey");
        qqProps.setSecret("qqSecret");
        qqProps.setName("QQClient");
        oauthProps.setQq(qqProps);

        Pac4jOAuthStravaClientProperties stravaProps = new Pac4jOAuthStravaClientProperties();
        stravaProps.setKey("stravaKey");
        stravaProps.setSecret("stravaSecret");
        stravaProps.setName("StravaClient");
        stravaProps.setApprovalPrompt("auto");
        oauthProps.setStrava(stravaProps);

        Pac4jOAuthClientProperties twitterProps = new Pac4jOAuthClientProperties();
        twitterProps.setKey("twitterKey");
        twitterProps.setSecret("twitterSecret");
        twitterProps.setName("TwitterClient");
        oauthProps.setTwitter(twitterProps);

        Pac4jOAuthClientProperties vkProps = new Pac4jOAuthClientProperties();
        vkProps.setKey("vkKey");
        vkProps.setSecret("vkSecret");
        vkProps.setName("VkClient");
        oauthProps.setVk(vkProps);

        Pac4jOAuthClientProperties weiboProps = new Pac4jOAuthClientProperties();
        weiboProps.setKey("weiboKey");
        weiboProps.setSecret("weiboSecret");
        weiboProps.setName("WeiboClient");
        oauthProps.setWeibo(weiboProps);

        Pac4jOAuthClientProperties weixinProps = new Pac4jOAuthClientProperties();
        weixinProps.setKey("weixinKey");
        weixinProps.setSecret("weixinSecret");
        weixinProps.setName("WechatClient");
        oauthProps.setWeixin(weixinProps);

        Pac4jOAuthClientProperties windowsliveProps = new Pac4jOAuthClientProperties();
        windowsliveProps.setKey("wlKey");
        windowsliveProps.setSecret("wlSecret");
        windowsliveProps.setName("WindowsLiveClient");
        oauthProps.setWindowslive(windowsliveProps);

        Pac4jOAuthClientProperties wordpressProps = new Pac4jOAuthClientProperties();
        wordpressProps.setKey("wpKey");
        wordpressProps.setSecret("wpSecret");
        wordpressProps.setName("WordPressClient");
        oauthProps.setWordpress(wordpressProps);

        Pac4jOAuthClientProperties yahooProps = new Pac4jOAuthClientProperties();
        yahooProps.setKey("yahooKey");
        yahooProps.setSecret("yahooSecret");
        yahooProps.setName("YahooClient");
        oauthProps.setYahoo(yahooProps);

        Pac4jOAuthClientProperties yibanProps = new Pac4jOAuthClientProperties();
        yibanProps.setKey("yibanKey");
        yibanProps.setSecret("yibanSecret");
        yibanProps.setName("YibanClient");
        oauthProps.setYiban(yibanProps);

        try {
            var field = Pac4jOAuthConfiguration.class.getDeclaredField("oauthProperties");
            field.setAccessible(true);
            field.set(config, oauthProps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ajaxResolver = Mockito.mock(AjaxRequestResolver.class);
        urlResolver = Mockito.mock(UrlResolver.class);
    }

    @Test
    void baiduClient() {
        var client = config.baiduClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void bitbucketClient() {
        var client = config.bitbucketClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void casOAuthWrapperClient() {
        var client = config.casOAuthWrapperClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void dropboxClient() {
        var client = config.dropboxClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void facebookClient() {
        var client = config.facebookClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void foursquareClient() {
        var client = config.foursquareClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void githubClient() {
        var client = config.githubClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void google2Client() {
        var client = config.google2Client(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void linkedin2Client() {
        var client = config.linkedin2Client(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void okClient() {
        var client = config.okClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void oschinaClient() {
        var client = config.oschinaClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void paypalClient() {
        var client = config.paypalClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void qqClient() {
        var client = config.qqClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void stravaClient() {
        var client = config.stravaClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void twitterClient() {
        var client = config.twitterClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void vkClient() {
        var client = config.vkClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void weiboClient() {
        var client = config.weiboClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void wechatClient() {
        var client = config.wechatClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void windowsliveClient() {
        var client = config.windowsliveClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void wordpressClient() {
        var client = config.wordpressClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void yahooClient() {
        var client = config.yahooClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }

    @Test
    void yibanClient() {
        var client = config.yibanClient(ajaxResolver, urlResolver);
        assertThat(client).isNotNull();
    }
}
