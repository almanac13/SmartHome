package devices;

public class Thermostat implements Device {
    private int temperature = 22;
    private boolean isOn = false;

    @Override
    public void on() {
        isOn = true;
        System.out.println("Thermostat is ON — Temperature set to " + temperature + "°C");
    }

    @Override
    public void off() {
        isOn = false;
        System.out.println("Thermostat is OFF");
    }

    public void setTemperature(int temperature) {
        if (isOn) {
            this.temperature = temperature;
            System.out.println("Temperature adjusted to " + temperature + "°C");
        } else {
            System.out.println("Turn on thermostat first!");
        }
    }

    public int getTemperature() {
        return temperature;
    }
}
