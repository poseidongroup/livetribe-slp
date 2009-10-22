/**
 *
 * Copyright 2009 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livetribe.slp.osgi;

import java.util.Dictionary;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import org.livetribe.slp.SLP;
import org.livetribe.slp.osgi.util.DictionarySettings;
import org.livetribe.slp.spi.ua.IUserAgent;
import org.livetribe.slp.ua.UserAgent;


/**
 * @version $Revision$ $Date$
 */
public class UserAgentManagedService implements ManagedService
{
    private final static String CLASS_NAME = UserAgentManagedService.class.getName();
    private final static Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private final Object lock = new Object();
    private final BundleContext bundleContext;
    private UserAgent userAgent;
    private ServiceRegistration serviceRegistration;

    public UserAgentManagedService(BundleContext bundleContext)
    {
        if (bundleContext == null) throw new IllegalArgumentException("Bundle context cannot be null");
        this.bundleContext = bundleContext;

        userAgent = SLP.newUserAgent(null);
        userAgent.start();

        serviceRegistration = bundleContext.registerService(IUserAgent.class.getName(), userAgent, null);
    }

    public void updated(Dictionary dictionary) throws ConfigurationException
    {
        LOGGER.entering(CLASS_NAME, "updated", dictionary);

        synchronized (lock)
        {
            serviceRegistration.unregister();
            userAgent.stop();

            userAgent = SLP.newUserAgent(dictionary == null ? null : DictionarySettings.from(dictionary));
            userAgent.start();
            serviceRegistration = bundleContext.registerService(IUserAgent.class.getName(), userAgent, dictionary);
        }

        LOGGER.exiting(CLASS_NAME, "updated");
    }
}