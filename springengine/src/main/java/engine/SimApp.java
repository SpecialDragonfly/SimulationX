/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration()
@ImportResource("classpath:/META-INF/spring/integration/sim-annotation.xml")
public class SimApp {

		public static void main(String[] args) {
			ApplicationContext context = SpringApplication.run(SimApp.class, args);

            Registration registration = (Registration) context.getBean("registration");
            for (int i = 1; i <= 100; i++) {
                RegistrationRequest req = new RegistrationRequest(i, "Source");
                registration.placeRegistration(req);
            }

            for (int i = 1; i <= 100; i++) {
                RegistrationRequest req = new RegistrationRequest(i, "Service");
                registration.placeRegistration(req);
            }
	}
}
