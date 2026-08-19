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

import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.profile.facebook.FacebookProfileDefinition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties for the PAC4J Facebook OAuth client.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class Pac4jOAuthFacebookClientProperties extends Pac4jOAuthClientProperties {

	protected String fields = FacebookClient.ATTEMPTED_AUTHENTICATION_SUFFIX;
	protected int limit = FacebookProfileDefinition.DEFAULT_LIMIT;
	protected boolean requiresExtendedToken = false;
	protected boolean useAppsecretProof = false;

    /**
     * <p>Returns the fields.</p>
     * @return the get fields
     */
	public String getFields() {
		return fields;
	}

    /**
     * <p>Sets the fields.</p>
     * @param fields
     */
	public void setFields(String fields) {
		this.fields = fields;
	}

    /**
     * <p>Returns the limit.</p>
     * @return the get limit
     */
	public int getLimit() {
		return limit;
	}

    /**
     * <p>Sets the limit.</p>
     * @param limit
     */
	public void setLimit(int limit) {
		this.limit = limit;
	}

    /**
     * <p>Checks if requires extended token.</p>
     * @return the is requires extended token
     */
	public boolean isRequiresExtendedToken() {
		return requiresExtendedToken;
	}

    /**
     * <p>Sets the requires extended token.</p>
     * @param requiresExtendedToken
     */
	public void setRequiresExtendedToken(boolean requiresExtendedToken) {
		this.requiresExtendedToken = requiresExtendedToken;
	}

    /**
     * <p>Checks if use appsecret proof.</p>
     * @return the is use appsecret proof
     */
	public boolean isUseAppsecretProof() {
		return useAppsecretProof;
	}

    /**
     * <p>Sets the use appsecret proof.</p>
     * @param useAppsecretProof
     */
	public void setUseAppsecretProof(boolean useAppsecretProof) {
		this.useAppsecretProof = useAppsecretProof;
	}

}
