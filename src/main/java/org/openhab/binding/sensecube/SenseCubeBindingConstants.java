/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sensecube;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link SenseCubeBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Marcel Zillekens - Initial contribution
 */
public class SenseCubeBindingConstants {

    public static final String BINDING_ID = "sensecube";

    // List of all Thing Type UIDs
    public final static ThingTypeUID SENSECUBE = new ThingTypeUID(BINDING_ID, "SenseCube");

    // List of all Channel ids
    public final static String TEMPERATURE = "temperature";
    public final static String SOUND = "sound";
    public final static String LIGHT = "light";

    // List of SenseCube connection parameters
    public final static String HOST_IP_ADDRESS = "host";
    public final static String HOST_PORT = "port";
}
