package org.pac4j.spring.boot.utils;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class Pac4jUrlUtilsTest {

    @Test
    void redirectsWithJavaxServletResponse() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);

        Pac4jUrlUtils.sendRedirect(response, "/callback");

        verify(response).sendRedirect("/callback");
    }

    @Test
    void encodesRedirectValueAsUtf8() {
        assertEquals("a+b%2Fc", Pac4jUrlUtils.urlEncode("a b/c"));
    }
}
