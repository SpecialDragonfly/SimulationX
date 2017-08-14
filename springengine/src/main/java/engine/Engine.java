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

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Engine {
	private static Logger logger = Logger.getLogger(Engine.class);

	private AtomicInteger sourceCounter = new AtomicInteger();

	private AtomicInteger serviceCounter = new AtomicInteger();

	@ServiceActivator(inputChannel="sourceRegistrator", outputChannel="registeredSources")
	public Source registerSource(RegistrationRequest request) {
		logger.info(Thread.currentThread().getName()
				+ " registering source #" + sourceCounter.incrementAndGet() + " for request #"
				+ request.getNumber());
		return new Source(request.getNumber());
	}

	@ServiceActivator(inputChannel="serviceRegistrator", outputChannel="registeredServices")
	public Service registerService(RegistrationRequest request) {
		logger.info(Thread.currentThread().getName()
				+ " registering service #" + serviceCounter.incrementAndGet() + " for request #"
				+ request.getNumber());
		return new Service(request.getNumber());
	}

}
