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

package Engine;

import Engine.Events.TickEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

import static java.lang.Thread.sleep;

@SpringBootApplication
@EnableIntegration()
@ImportResource("classpath:/META-INF/spring/integration/sim-annotation.xml")
@Configuration
@ComponentScan("Engine")
public class SimApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SimApp.class, args);

        Queue queue = (Queue) context.getBean("queue");
        RegistrationQueue registrationQueue = (RegistrationQueue) context.getBean("registrationQueue");
        SimpleEngineStrategy engineStrategy = (SimpleEngineStrategy) context.getBean("simpleEngineStrategy");

        //queue.subscribe("TickEvent", this);
        registrationQueue.subscribe("RegisterEvent", engineStrategy);
        long count = 0;
        while (count < 100) {
            // Do all PlanarEnvironment Objects still respond when poked?
            engineStrategy.verifyObjects();

            // We've ticked, so tell anything that cares.
            queue.push(new TickEvent());
            try {
                sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
	}
}
