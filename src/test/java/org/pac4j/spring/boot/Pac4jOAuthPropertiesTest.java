package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthPropertiesTest {

    @Test
    void defaultValues() {
        Pac4jOAuthProperties props = new Pac4jOAuthProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getBaidu()).isNotNull();
        assertThat(props.getBitbucket()).isNotNull();
        assertThat(props.getCas()).isNotNull();
        assertThat(props.getDropbox()).isNotNull();
        assertThat(props.getFacebook()).isNotNull();
        assertThat(props.getFoursquare()).isNotNull();
        assertThat(props.getGithub()).isNotNull();
        assertThat(props.getGoogle2()).isNotNull();
        assertThat(props.getLinkedin2()).isNotNull();
        assertThat(props.getOk()).isNotNull();
        assertThat(props.getOrcid()).isNotNull();
        assertThat(props.getOschina()).isNotNull();
        assertThat(props.getPaypal()).isNotNull();
        assertThat(props.getQq()).isNotNull();
        assertThat(props.getRenren()).isNotNull();
        assertThat(props.getStrava()).isNotNull();
        assertThat(props.getSohu()).isNotNull();
        assertThat(props.getTwitter()).isNotNull();
        assertThat(props.getVk()).isNotNull();
        assertThat(props.getWeibo()).isNotNull();
        assertThat(props.getWeixin()).isNotNull();
        assertThat(props.getWindowslive()).isNotNull();
        assertThat(props.getWordpress()).isNotNull();
        assertThat(props.getYahoo()).isNotNull();
        assertThat(props.getYiban()).isNotNull();
    }

    @Test
    void setAndGetValues() {
        Pac4jOAuthProperties props = new Pac4jOAuthProperties();

        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();

        Pac4jOAuthClientProperties baiduProps = new Pac4jOAuthClientProperties();
        baiduProps.setKey("baiduKey");
        props.setBaidu(baiduProps);
        assertThat(props.getBaidu().getKey()).isEqualTo("baiduKey");
    }

    @Test
    void prefixConstant() {
        assertThat(Pac4jOAuthProperties.PREFIX).isEqualTo("pac4j.oauth");
    }

    @Test
    void toStringContainsEnabled() {
        Pac4jOAuthProperties props = new Pac4jOAuthProperties();
        props.setEnabled(true);
        assertThat(props.toString()).contains("enabled=true");
    }
}
