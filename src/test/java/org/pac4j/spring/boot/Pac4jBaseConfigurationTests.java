package org.pac4j.spring.boot;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.pac4j.core.logout.handler.LogoutHandler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pac4jBaseConfigurationTests {

    @Test
    void logoutHandlerUsesConfiguredSessionPolicy() {
        Pac4jLogoutProperties properties = new Pac4jLogoutProperties();
        properties.setDestroySession(false);

        LogoutHandler<JEEContext> handler = new Pac4jBaseConfiguration().logoutHandler(properties);

        assertTrue(handler instanceof DefaultLogoutHandler);
        assertFalse(((DefaultLogoutHandler<JEEContext>) handler).isDestroySession());
    }
}
