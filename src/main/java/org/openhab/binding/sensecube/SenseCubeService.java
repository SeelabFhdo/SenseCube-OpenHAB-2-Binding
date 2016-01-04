package org.openhab.binding.sensecube;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openhab.binding.sensecube.handler.SenseCubeHandler;

public class SenseCubeService extends CoapServer {

    private static final String RESOURCE_ID = "s";
    private static final String RESOURCE_TITLE = "SenseCube service";
    private static final String JSON_TEMPERATURE_KEY = "temperature";
    private static final String JSON_SOUND_KEY = "sound";
    private static final String JSON_LIGHT_KEY = "light";

    public SenseCubeService(SenseCubeHandler handler) {
        super();
        add(new SenseCubeResource(handler));
    }

    public static class SenseCubeResource extends CoapResource {

        private SenseCubeHandler handler;

        public SenseCubeResource(SenseCubeHandler handler) {
            // Set resource identifier
            super(RESOURCE_ID);
            this.handler = handler;

            // Set display name
            getAttributes().setTitle(RESOURCE_TITLE);
        }

        @Override
        public void handlePOST(CoapExchange exchange) {
            JSONParser parser = new JSONParser();
            try {
                Object object = parser.parse(exchange.getRequestText());
                JSONObject jsonObject = (JSONObject) object;

                // Read temperature, sound and light value
                SenseCubeSensorData sensorData = new SenseCubeSensorData();
                sensorData.setTemperature(Float.valueOf(jsonObject.get(JSON_TEMPERATURE_KEY).toString()));
                sensorData.setSound(Float.valueOf(jsonObject.get(JSON_SOUND_KEY).toString()));
                sensorData.setLight(Float.valueOf(jsonObject.get(JSON_LIGHT_KEY).toString()));

                // Notify handler
                handler.update(sensorData);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
