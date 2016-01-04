package org.openhab.binding.sensecube;

public class SenseCubeSensorData {

    private float temperature;
    private float sound;
    private float light;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getSound() {
        return sound;
    }

    public void setSound(float sound) {
        this.sound = sound;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }
}
