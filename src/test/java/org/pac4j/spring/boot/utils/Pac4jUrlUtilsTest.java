package org.pac4j.spring.boot.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class Pac4jUrlUtilsTest {

    @Test
    void appendsTheClientParameterAndRedirectsThroughJakartaServlet() {
        var response = new MockHttpServletResponse();
        var url = Pac4jUrlUtils.constructRedirectUrl("/callback?state=fixture", "client_name", "cas-fixture");

        Pac4jUrlUtils.sendRedirect(response, url);

        assertThat(response.getStatus()).isEqualTo(302);
        assertThat(response.getRedirectedUrl()).isEqualTo("/callback?state=fixture&client_name=cas-fixture");
        assertThat(Pac4jUrlUtils.constructRedirectUrl("/callback", "client_name", "cas-fixture"))
                .isEqualTo("/callback?client_name=cas-fixture");
        assertThat(Pac4jUrlUtils.constructRedirectUrl("/回调", "client_name", "cas-fixture", true))
                .isEqualTo("%2F%E5%9B%9E%E8%B0%83?client_name=cas-fixture");
    }
}
