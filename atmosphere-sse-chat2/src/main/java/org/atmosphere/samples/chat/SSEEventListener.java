
package org.atmosphere.samples.chat;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SSEEventListener implements AtmosphereResourceEventListener {

    private final Logger logger = LoggerFactory.getLogger(SSEEventListener.class);

    @Override
    public void onPreSuspend(AtmosphereResourceEvent are) {
        logger.info("onPreSuspend: " + are.getResource().uuid());
    }

    @Override
    public void onSuspend(AtmosphereResourceEvent are) {
        logger.info("onSuspend1: " );
        logger.info("onSuspend2: " + are.getResource().uuid());
    }

    @Override
    public void onResume(AtmosphereResourceEvent are) {
        logger.info("onResume: " + are.getResource().uuid());
    }

    @Override
    public void onDisconnect(AtmosphereResourceEvent are) {
        logger.info("onDisconnect: " + are.getResource().uuid());
    }

    @Override
    public void onBroadcast(AtmosphereResourceEvent are) {
        logger.info("onBroadcast: " + are.getResource().uuid());
        logger.info("onBroadcast message: " + are.getMessage().toString());
        logger.info("onBroadcast id: " + are.broadcaster().getID());
    }

    @Override
    public void onThrowable(AtmosphereResourceEvent are) {
        logger.info("onThrowable: " + are.getResource().uuid());
    }

    @Override
    public void onClose(AtmosphereResourceEvent are) {
        logger.info("onClose: " + are.getResource().uuid());
    }

    @Override
    public void onHeartbeat(AtmosphereResourceEvent are) {
        logger.info("onHeartbeat: " + are.getResource().uuid());
    }

}
