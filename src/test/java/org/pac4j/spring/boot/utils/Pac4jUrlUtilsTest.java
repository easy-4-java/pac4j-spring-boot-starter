package org.pac4j.spring.boot.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jUrlUtils}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jUrlUtilsTest {

    @Test
    void constructRedirectUrlWithoutExistingQuery() {
        String result = Pac4jUrlUtils.constructRedirectUrl(
                "https://example.com/callback", "client_name", "FormClient");
        assertThat(result).isEqualTo("https://example.com/callback?client_name=FormClient");
    }

    @Test
    void constructRedirectUrlWithExistingQuery() {
        String result = Pac4jUrlUtils.constructRedirectUrl(
                "https://example.com/callback?existing=param", "client_name", "FormClient");
        assertThat(result).isEqualTo("https://example.com/callback?existing=param&client_name=FormClient");
    }

    @Test
    void constructRedirectUrlWithEncode() {
        // When encodeUrl=true, the entire serviceUrl is encoded
        String result = Pac4jUrlUtils.constructRedirectUrl(
                "https://example.com/callback", "client_name", "FormClient", true);
        assertThat(result).contains("client_name=FormClient");
    }

    @Test
    void constructRedirectUrlWithoutEncode() {
        String result = Pac4jUrlUtils.constructRedirectUrl(
                "https://example.com/callback", "client_name", "FormClient", false);
        assertThat(result).isEqualTo("https://example.com/callback?client_name=FormClient");
    }

    @Test
    void constructRedirectUrlWithEncodeAndExistingQuery() {
        // When encodeUrl=true, the entire serviceUrl (including query) is encoded
        String result = Pac4jUrlUtils.constructRedirectUrl(
                "https://example.com/callback?a=1", "client_name", "FormClient", true);
        assertThat(result).contains("client_name=FormClient");
    }

    @Test
    void urlEncode() {
        String encoded = Pac4jUrlUtils.urlEncode("hello world");
        // URLEncoder.encode may use + or %20 for spaces
        assertThat(encoded).satisfiesAnyOf(
                e -> assertThat(e).isEqualTo("hello+world"),
                e -> assertThat(e).isEqualTo("hello%20world")
        );
    }

    @Test
    void urlEncodeSpecialChars() {
        String encoded = Pac4jUrlUtils.urlEncode("a&b=c");
        assertThat(encoded).isEqualTo("a%26b%3Dc");
    }

    @Test
    void sendRedirect() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        Pac4jUrlUtils.sendRedirect(response, "https://example.com");
        verify(response).sendRedirect("https://example.com");
    }

    @Test
    void sendRedirectHandlesIOException() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        doThrow(new IOException("test")).when(response).sendRedirect(anyString());
        // Should not throw - exception is caught and logged
        Pac4jUrlUtils.sendRedirect(response, "https://example.com");
        verify(response).sendRedirect("https://example.com");
    }
}
