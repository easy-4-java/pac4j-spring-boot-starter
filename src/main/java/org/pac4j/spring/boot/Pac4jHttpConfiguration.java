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

import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(Pac4jAutoConfiguration.class)
@ConditionalOnClass({ FormClient.class, IndirectBasicAuthClient.class})
@ConditionalOnProperty(prefix = Pac4jHttpProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ Pac4jHttpProperties.class, Pac4jProperties.class })
/**
 * Spring Boot configuration for the HTTP-based pac4j clients. <p>Conditionally
 * registers the {@link FormClient}, {@link IndirectBasicAuthClient} and
 * {@link DirectBasicAuthClient} beans backed by a test username/password authenticator
 * when the corresponding properties are enabled.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Pac4jHttpConfiguration {

	@Bean
 	@ConditionalOnProperty(prefix = Pac4jHttpProperties.PREFIX, value = Pac4jClientNames.FORM_CLIENT, havingValue = "true")
    /**
     * <p>Form client.</p>
     * @param pac4jHttpProperties
     * @return the form client
     */
 	public FormClient formClient(Pac4jHttpProperties pac4jHttpProperties) {

		SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		final FormClient formClient = new FormClient(pac4jHttpProperties.getLoginUrl(),
				pac4jHttpProperties.getUsernameParameter(), pac4jHttpProperties.getPasswordParameter(),
				usernamePasswordAuthenticator);
		
 		return formClient;
 	}
    
	@Bean
	@ConditionalOnProperty(prefix = Pac4jHttpProperties.PREFIX, value = Pac4jClientNames.INDIRECT_BASIC_AUTH_CLIENT, havingValue = "true")
    /**
     * <p>Indirect basic auth client.</p>
     * @param pac4jHttpProperties
     * @return the indirect basic auth client
     */
	public IndirectBasicAuthClient indirectBasicAuthClient(Pac4jHttpProperties pac4jHttpProperties) {
		
		final SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		
		final IndirectBasicAuthClient indirectBasicAuthClient = new IndirectBasicAuthClient(pac4jHttpProperties.getRealmName(), usernamePasswordAuthenticator);

		return indirectBasicAuthClient;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = Pac4jHttpProperties.PREFIX, value = Pac4jClientNames.DIRECT_BASIC_AUTH_CLIENT, havingValue = "true")
    /**
     * <p>Direct basic auth client.</p>
     * @param pac4jHttpProperties
     * @return the direct basic auth client
     */
	public DirectBasicAuthClient directBasicAuthClient(Pac4jHttpProperties pac4jHttpProperties) {
		
		final SimpleTestUsernamePasswordAuthenticator usernamePasswordAuthenticator = new SimpleTestUsernamePasswordAuthenticator();
		// basic auth
	    final DirectBasicAuthClient directBasicAuthClient = new DirectBasicAuthClient(usernamePasswordAuthenticator);
	    
		return directBasicAuthClient;
		
	}
	
}
