/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sensecube.handler;

import static org.openhab.binding.sensecube.SenseCubeBindingConstants.*;

import java.util.Date;

import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.sensecube.SenseCubeSensorData;
import org.openhab.binding.sensecube.SenseCubeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link SenseCubeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Marcel Zillekens - Initial contribution
 */
public class SenseCubeHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(SenseCubeHandler.class);
    private long lastUpdateRecevied;

    public SenseCubeHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.info("Command unhandled ...");
    }

    @Override
    public void initialize() {
        // SenseCube will connect to SenseCube service to transmit sensor data
        SenseCubeService service = new SenseCubeService(this);
        try {
            service.start();
            updateStatus(ThingStatus.ONLINE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO: Initialize the thing. If done set status to ONLINE to indicate proper working.
        // Long running initialization should be done asynchronously in background.
        // updateStatus(ThingStatus.ONLINE);

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work
        // as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }

    public void update(SenseCubeSensorData sensorData) {
        if (sensorData != null) {
            lastUpdateRecevied = System.currentTimeMillis();
            System.out.println("Last updated: " + (new Date(lastUpdateRecevied)).toString());
            updateState(new ChannelUID(getThing().getUID(), TEMPERATURE),
                    (new DecimalType(sensorData.getTemperature())));
            updateState(new ChannelUID(getThing().getUID(), SOUND), (new DecimalType(sensorData.getSound())));
            updateState(new ChannelUID(getThing().getUID(), LIGHT), (new DecimalType(sensorData.getLight())));
        }
    }
}
