/*
 * Copyright 2006-2008 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livetribe.slp.sa;

import java.util.EventListener;


/**
 * The listener that receives ServiceNotificationEvents.
 *
 * @see ServiceNotificationEvent
 */
public interface ServiceNotificationListener extends EventListener
{
    /**
     * Invoked when a service is registered or when attributes are added to an existing service.
     *
     * @param event the service notification event
     */
    public void serviceRegistered(ServiceNotificationEvent event);

    /**
     * Invoked when a service is deregistered or when attributes are removed from an existing service.
     *
     * @param event the service notification event
     */
    public void serviceDeregistered(ServiceNotificationEvent event);
}
