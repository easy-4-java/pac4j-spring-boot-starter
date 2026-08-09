package org.pac4j.spring.boot.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link StringUtils}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class StringUtilsTest {

    @Test
    void configLocationDelimiters() {
        assertThat(StringUtils.CONFIG_LOCATION_DELIMITERS).isEqualTo(",; \t\n");
    }

    @Test
    void tokenizeToStringArrayWithDefaultDelimiters() {
        String[] result = StringUtils.tokenizeToStringArray("a,b;c d");
        assertThat(result).containsExactly("a", "b", "c", "d");
    }

    @Test
    void tokenizeToStringArrayWithEmptyString() {
        String[] result = StringUtils.tokenizeToStringArray("");
        assertThat(result).isEmpty();
    }

    @Test
    void tokenizeToStringArrayWithNull() {
        String[] result = StringUtils.tokenizeToStringArray(null);
        assertThat(result).isEmpty();
    }

    @Test
    void extendsSpringStringUtils() {
        assertThat(StringUtils.hasText("hello")).isTrue();
        assertThat(StringUtils.hasText("")).isFalse();
        assertThat(StringUtils.hasText(null)).isFalse();
    }
}
