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

import org.pac4j.core.util.Pac4jConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(Pac4jHttpProperties.PREFIX)
@Getter
@Setter
@ToString
/**
 * {@code pac4j.http.*} configuration properties for the HTTP-based clients. <p>Binds the
 * toggles, names and credential parameters for the form, indirect basic-auth and direct
 * basic-auth clients.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Pac4jHttpProperties {

	public static final String PREFIX = "pac4j.http";
	
	/** Whether Enable Pac4j Http. */
	private boolean enabled = false;

	/** 登录地址：会话不存在时访问的地址 */
	private String loginUrl;
    private String usernameParameter = Pac4jConstants.USERNAME;
    private String passwordParameter = Pac4jConstants.PASSWORD;
	
    /** DirectCasProxyClient */
    
    private boolean formClient = false;
    private String formClientName = "form";
    
    /** IndirectBasicAuthClient */
    
    private boolean indirectBasicAuthClient = false;
    private String indirectBasicAuthClientName = "indirect-basic-auth";
    private String realmName;
    
    /** DirectBasicAuthClient */
    
    private boolean directBasicAuthClient = false;
    private String directBasicAuthClientName = "direct-basic-auth";

    /**
     * <p>Checks if enabled.</p>
     * @return the is enabled
     */
	public boolean isEnabled() {
		return enabled;
	}

    /**
     * <p>Sets the enabled.</p>
     * @param enabled
     */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    /**
     * <p>Returns the login url.</p>
     * @return the get login url
     */
	public String getLoginUrl() {
		return loginUrl;
	}

    /**
     * <p>Sets the login url.</p>
     * @param loginUrl
     */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

    /**
     * <p>Returns the username parameter.</p>
     * @return the get username parameter
     */
	public String getUsernameParameter() {
		return usernameParameter;
	}

    /**
     * <p>Sets the username parameter.</p>
     * @param usernameParameter
     */
	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

    /**
     * <p>Returns the password parameter.</p>
     * @return the get password parameter
     */
	public String getPasswordParameter() {
		return passwordParameter;
	}

    /**
     * <p>Sets the password parameter.</p>
     * @param passwordParameter
     */
	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

    /**
     * <p>Checks if form client.</p>
     * @return the is form client
     */
	public boolean isFormClient() {
		return formClient;
	}

    /**
     * <p>Sets the form client.</p>
     * @param formClient
     */
	public void setFormClient(boolean formClient) {
		this.formClient = formClient;
	}

    /**
     * <p>Returns the form client name.</p>
     * @return the get form client name
     */
	public String getFormClientName() {
		return formClientName;
	}

    /**
     * <p>Sets the form client name.</p>
     * @param formClientName
     */
	public void setFormClientName(String formClientName) {
		this.formClientName = formClientName;
	}

    /**
     * <p>Checks if indirect basic auth client.</p>
     * @return the is indirect basic auth client
     */
	public boolean isIndirectBasicAuthClient() {
		return indirectBasicAuthClient;
	}

    /**
     * <p>Sets the indirect basic auth client.</p>
     * @param indirectBasicAuthClient
     */
	public void setIndirectBasicAuthClient(boolean indirectBasicAuthClient) {
		this.indirectBasicAuthClient = indirectBasicAuthClient;
	}

    /**
     * <p>Returns the indirect basic auth client name.</p>
     * @return the get indirect basic auth client name
     */
	public String getIndirectBasicAuthClientName() {
		return indirectBasicAuthClientName;
	}

    /**
     * <p>Sets the indirect basic auth client name.</p>
     * @param indirectBasicAuthClientName
     */
	public void setIndirectBasicAuthClientName(String indirectBasicAuthClientName) {
		this.indirectBasicAuthClientName = indirectBasicAuthClientName;
	}
	
    /**
     * <p>Returns the realm name.</p>
     * @return the get realm name
     */
	public String getRealmName() {
		return realmName;
	}

    /**
     * <p>Sets the realm name.</p>
     * @param realmName
     */
	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

    /**
     * <p>Checks if direct basic auth client.</p>
     * @return the is direct basic auth client
     */
	public boolean isDirectBasicAuthClient() {
		return directBasicAuthClient;
	}

    /**
     * <p>Sets the direct basic auth client.</p>
     * @param directBasicAuthClient
     */
	public void setDirectBasicAuthClient(boolean directBasicAuthClient) {
		this.directBasicAuthClient = directBasicAuthClient;
	}

    /**
     * <p>Returns the direct basic auth client name.</p>
     * @return the get direct basic auth client name
     */
	public String getDirectBasicAuthClientName() {
		return directBasicAuthClientName;
	}

    /**
     * <p>Sets the direct basic auth client name.</p>
     * @param directBasicAuthClientName
     */
	public void setDirectBasicAuthClientName(String directBasicAuthClientName) {
		this.directBasicAuthClientName = directBasicAuthClientName;
	}

}
