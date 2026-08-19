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

/**
 * <p>Configuration properties for Pac4jOAuthCasClient.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Pac4jOAuthCasClientProperties extends Pac4jOAuthClientProperties {

	 /**
     * The CAS OAuth server url (without a trailing slash).
     * For example: http://localhost:8080/cas/oauth2.0
     */
    private String casOAuthUrl;
    private String casLogoutUrl;
    private boolean springSecurityCompliant = false;
    private boolean implicitFlow = false;

    /**
     * <p>Returns the cas o auth url.</p>
     * @return the get cas o auth url
     */
	public String getCasOAuthUrl() {
		return casOAuthUrl;
	}

    /**
     * <p>Sets the cas o auth url.</p>
     * @param casOAuthUrl
     */
	public void setCasOAuthUrl(String casOAuthUrl) {
		this.casOAuthUrl = casOAuthUrl;
	}

    /**
     * <p>Returns the cas logout url.</p>
     * @return the get cas logout url
     */
	public String getCasLogoutUrl() {
		return casLogoutUrl;
	}

    /**
     * <p>Sets the cas logout url.</p>
     * @param casLogoutUrl
     */
	public void setCasLogoutUrl(String casLogoutUrl) {
		this.casLogoutUrl = casLogoutUrl;
	}

    /**
     * <p>Checks if spring security compliant.</p>
     * @return the is spring security compliant
     */
	public boolean isSpringSecurityCompliant() {
		return springSecurityCompliant;
	}

    /**
     * <p>Sets the spring security compliant.</p>
     * @param springSecurityCompliant
     */
	public void setSpringSecurityCompliant(boolean springSecurityCompliant) {
		this.springSecurityCompliant = springSecurityCompliant;
	}

    /**
     * <p>Checks if implicit flow.</p>
     * @return the is implicit flow
     */
	public boolean isImplicitFlow() {
		return implicitFlow;
	}

    /**
     * <p>Sets the implicit flow.</p>
     * @param implicitFlow
     */
	public void setImplicitFlow(boolean implicitFlow) {
		this.implicitFlow = implicitFlow;
	}

}
