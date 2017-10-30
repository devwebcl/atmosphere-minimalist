
package org.atmosphere.samples.chat;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;
import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Singleton;
import org.atmosphere.config.service.DeliverTo;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceFactory;
import org.atmosphere.cpr.Broadcaster;

import org.atmosphere.plugin.hazelcast.HazelcastBroadcaster;
import org.atmosphere.plugin.hazelcast.HazelcastSerializedBroadcaster;
import org.atmosphere.cache.UUIDBroadcasterCache;

/**
 * Simple AtmosphereHandler that implement the logic to build a Server Sent
 * Events Chat application.
 *
 * modified !
 *
 */
@ManagedService(path = "/chat",
		broadcasterCache = UUIDBroadcasterCache.class,
		broadcaster = HazelcastBroadcaster.class,
        interceptors = {AtmosphereResourceLifecycleInterceptor.class,
            HeartbeatInterceptor.class,
            TrackMessageSizeInterceptor.class,
        })
public class SSEAtmosphereHandler {

    private final Logger logger = LoggerFactory.getLogger(SSEAtmosphereHandler.class);

    @Inject
    @Singleton
    private AtmosphereResourceFactory resourceFactory;

    @Ready
    @DeliverTo(DeliverTo.DELIVER_TO.BROADCASTER) // .ALL
    public void onOpen(final AtmosphereResource resource) throws IOException {
        logger.info("onOpen: " + resource.uuid() + "***************************************************************************");
        //resource.addEventListener(new SSEEventListener());
        //resourceFactory.registerUuidForFindCandidate(resource);
    }



    @Message
    public void onMessage(String message) throws IOException {
        logger.info("onMessage: " + message);

        Set<String> listId = new TreeSet<>(resourceFactory.resources().keySet());

        //es como un tipo de broadcaster
        for (String id : listId) {
            try {
            	logger.info("mensaje id: " + id);
                AtmosphereResource r = resourceFactory.find(id);
                r.getResponse().getWriter().write(message);
                r.getResponse().getWriter().flush();
            } catch (Exception ex) {
                logger.error("error: " + id, ex);
                resourceFactory.remove(id);
            }
        }

    }


    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) throws IOException {
        String uuid = event.getResource().uuid();
        event.getResource().close();
        resourceFactory.remove(uuid);
    }

}

