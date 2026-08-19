/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.spring.boot;

import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.oauth.client.*;
import org.pac4j.oauth.config.OAuth10Configuration;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.OAuth10Profile;
import org.pac4j.oauth.profile.OAuth20Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.scribejava.apis.SinaWeiboApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * Auto-configuration for PAC4J OAuth authentication.
 * <p>Registers OAuth clients for various providers (Facebook, GitHub, Google, etc.)
 * when their respective properties are configured.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@AutoConfigureBefore(Pac4jAutoConfiguration.class)
@ConditionalOnClass({ OAuth20Client.class, DefaultApi20.class, SinaWeiboApi20.class })
@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ Pac4jOAuthProperties.class, Pac4jProperties.class })
public class Pac4jOAuthConfiguration {

	@Autowired
	private Pac4jOAuthProperties oauthProperties;

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "baidu")
    /**
     * <p>Baidu client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the baidu client
     */
	public BaiduClient baiduClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getBaidu();
		final BaiduClient client = new BaiduClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "bitbucket")
    /**
     * <p>Bitbucket client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the bitbucket client
     */
	public BitbucketClient bitbucketClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getBitbucket();
		final BitbucketClient client = new BitbucketClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "cas")
    /**
     * <p>Cas o auth wrapper client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the cas o auth wrapper client
     */
	public CasOAuthWrapperClient casOAuthWrapperClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthCasClientProperties properties = oauthProperties.getCas();
		final CasOAuthWrapperClient client = new CasOAuthWrapperClient(properties.getKey(), properties.getSecret(), properties.getCasOAuthUrl());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "dropbox")
    /**
     * <p>Dropbox client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the dropbox client
     */
	public DropBoxClient dropboxClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getDropbox();
		final DropBoxClient client = new DropBoxClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "facebook")
    /**
     * <p>Facebook client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the facebook client
     */
	public FacebookClient facebookClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthFacebookClientProperties properties = oauthProperties.getFacebook();
		final FacebookClient client = new FacebookClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		client.setFields(properties.getFields());
		client.setLimit(properties.getLimit());

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "foursquare")
    /**
     * <p>Foursquare client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the foursquare client
     */
	public FoursquareClient foursquareClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getFoursquare();
		final FoursquareClient client = new FoursquareClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "github")
    /**
     * <p>Github client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the github client
     */
	public GitHubClient githubClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getGithub();
		final GitHubClient client = new GitHubClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "google2")
    /**
     * <p>Google2 client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the google2 client
     */
	public Google2Client google2Client(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getGoogle2();
		final Google2Client client = new Google2Client(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "linkedin2")
    /**
     * <p>Linkedin2 client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the linkedin2 client
     */
	public LinkedIn2Client linkedin2Client(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getLinkedin2();
		final LinkedIn2Client client = new LinkedIn2Client(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "ok")
    /**
     * <p>Ok client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the ok client
     */
	public OkClient okClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthOkClientProperties properties = oauthProperties.getOk();
		final OkClient client = new OkClient(properties.getKey(), properties.getSecret(), properties.getPublicKey());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "oschina")
    /**
     * <p>Oschina client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the oschina client
     */
	public OschinaClient oschinaClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getOschina();
		final OschinaClient client = new OschinaClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "paypal")
    /**
     * <p>Paypal client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the paypal client
     */
	public PayPalClient paypalClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getPaypal();
		final PayPalClient client = new PayPalClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "qq")
    /**
     * <p>Qq client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the qq client
     */
	public QQClient qqClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getQq();
		final QQClient client = new QQClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "strava")
    /**
     * <p>Strava client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the strava client
     */
	public StravaClient stravaClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthStravaClientProperties properties = oauthProperties.getStrava();
		final StravaClient client = new StravaClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		client.setApprovalPrompt(properties.getApprovalPrompt());

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "twitter")
    /**
     * <p>Twitter client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the twitter client
     */
	public TwitterClient twitterClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getTwitter();
		final TwitterClient client = new TwitterClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;

	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "vk")
    /**
     * <p>Vk client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the vk client
     */
	public VkClient vkClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getVk();
		final VkClient client = new VkClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "weibo")
    /**
     * <p>Weibo client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the weibo client
     */
	public WeiboClient weiboClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getWeibo();
		final WeiboClient client = new WeiboClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;

	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "wechat")
    /**
     * <p>Wechat client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the wechat client
     */
	public WechatClient wechatClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getWeixin();
		final WechatClient client = new WechatClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;

	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "windowslive")
    /**
     * <p>Windowslive client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the windowslive client
     */
	public WindowsLiveClient windowsliveClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getWindowslive();
		final WindowsLiveClient client = new WindowsLiveClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "wordpress")
    /**
     * <p>Wordpress client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the wordpress client
     */
	public WordPressClient wordpressClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getWordpress();
		final WordPressClient client = new WordPressClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "yahoo")
    /**
     * <p>Yahoo client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the yahoo client
     */
	public YahooClient yahooClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getYahoo();
		final YahooClient client = new YahooClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = Pac4jOAuthProperties.PREFIX, value = "yiban")
    /**
     * <p>Yiban client.</p>
     * @param ajaxRequestResolver
     * @param urlResolver
     * @return the yiban client
     */
	public YibanClient yibanClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final Pac4jOAuthClientProperties properties = oauthProperties.getGithub();
		final YibanClient client = new YibanClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);

		return client;
	}


	/**
	 * Initialises an OAuth 1.0 client with the given properties.
	 * @param <U> the OAuth 1.0 profile type
	 * @param client the OAuth 1.0 client to initialise
	 * @param properties the client properties
	 * @param ajaxRequestResolver the AJAX request resolver
	 * @param urlResolver the URL resolver
	 */
	protected <U extends OAuth10Profile> void initOAuth10Client(OAuth10Client client,
			Pac4jOAuthClientProperties properties, AjaxRequestResolver ajaxRequestResolver,
			UrlResolver urlResolver) {

		final OAuth10Configuration configuration = client.getConfiguration();

		//configuration.setConnectTimeout(properties.getConnectTimeout());
		//configuration.setHasGrantType(properties.isHasGrantType());
		//configuration.setReadTimeout(properties.getReadTimeout());
		configuration.setResponseType(properties.getResponseType());
		configuration.setTokenAsHeader(properties.isTokenAsHeader());

		client.setName(properties.getName());
		client.setKey(properties.getKey());
		client.setConfiguration(configuration);
		//client.setIncludeClientNameInCallbackUrl(pac4jProperties.isIncludeClientNameInCallbackUrl());
		client.setSecret(properties.getSecret());
		client.setUrlResolver(urlResolver);

	}

	/**
	 * Initialises an OAuth 2.0 client with the given properties.
	 * @param <U> the OAuth 2.0 profile type
	 * @param client the OAuth 2.0 client to initialise
	 * @param properties the client properties
	 * @param ajaxRequestResolver the AJAX request resolver
	 * @param urlResolver the URL resolver
	 */
	protected <U extends OAuth20Profile> void initOAuth20Client(OAuth20Client client,
			Pac4jOAuthClientProperties properties, AjaxRequestResolver ajaxRequestResolver,
			UrlResolver urlResolver) {

		final OAuth20Configuration configuration = client.getConfiguration();

		configuration.setCustomParams(properties.getCustomParams());
		//configuration.setHasGrantType(properties.isHasGrantType());
		//configuration.setReadTimeout(properties.getReadTimeout());
		configuration.setScope(properties.getScope());
		configuration.setResponseType(properties.getResponseType());
		configuration.setWithState(properties.isWithState());
		//configuration.setStateData(properties.getStateData());
		configuration.setTokenAsHeader(properties.isTokenAsHeader());

		client.setName(properties.getName());
		client.setAjaxRequestResolver(ajaxRequestResolver);
		client.setCallbackUrl(properties.getCallbackUrl());
		client.setKey(properties.getKey());
		client.setConfiguration(configuration);
		//client.setIncludeClientNameInCallbackUrl(pac4jProperties.isIncludeClientNameInCallbackUrl());
		client.setSecret(properties.getSecret());
		client.setUrlResolver(urlResolver);
	}


}
