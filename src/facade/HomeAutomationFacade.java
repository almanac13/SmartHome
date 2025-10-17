package facade;

import decorators.*;
import devices.*;

public class HomeAutomationFacade {
    private final Device baseLight;
    private final Device baseMusic;
    private final Device baseCamera;
    private final Device baseThermostat;

    private Device light;
    private Device music;
    private Device camera;
    private Device thermostat;

    private String currentMode = "normal";

    private Battery power = new Battery(100);

    public HomeAutomationFacade(Device light, Device music, Device camera, Device thermostat) {
        this.baseLight = light;
        this.baseMusic = music;
        this.baseCamera = camera;
        this.baseThermostat = thermostat;

        this.light = light;
        this.music = music;
        this.camera = camera;
        this.thermostat = thermostat;
    }

    private void resetDevices() {
        light = baseLight;
        music = baseMusic;
        camera = baseCamera;
        thermostat = baseThermostat;
    }

    private void drainPower(double amount) {
        double newLevel = Math.max(0, power.getBatteryCapacity() - amount);
        power.setBatteryCapacity(newLevel);
        System.out.println("Power drained by " + amount + "%. Current power: " + power.getBatteryCapacity() + "%");
    }


    private void checkPower() {
        System.out.println("\nCurrent power: " + power.getBatteryCapacity() + "%");
        if (power.getBatteryCapacity() < 30) {
            System.out.println("Power is low! Switching to Energy Saving Mode...");
            activateEnergySavingMode();
        }
    }
    public double getPowerLevel() {
        return power.getBatteryCapacity();
    }


    public void setThermostatTemperature(int temp) {
        Device base = thermostat;
        // unwrap decorators
        while (base instanceof DeviceDecorator decorator) {
            base = decorator.getWrappedDevice();
        }

        if (base instanceof Thermostat t) {
            t.setTemperature(temp);
            System.out.println("Thermostat temperature set to " + temp + "°C");
        } else {
            System.out.println("Cannot set temperature — not a thermostat!");
        }
    }


    private void activateEnergySavingMode() {
        resetDevices();

        light = new EnergySavingDecorator(baseLight, power);
        thermostat = new EnergySavingDecorator(baseThermostat, power);
        music = new EnergySavingDecorator(baseMusic, power);
        camera = new EnergySavingDecorator(baseCamera, power);

        light.on();
        thermostat.on();
        music.on();
        camera.on();

        currentMode = "energy_saving";
        System.out.println("Energy Saving Mode Active for all devices.");
    }

    public void startPartyMode() {
        checkPower();
        if (power.getBatteryCapacity() < 30) return;

        System.out.println("\nStarting Party Mode...");
        resetDevices();
        currentMode = "party";

        light = new LightModeDecorator(baseLight, "party");
        music = new VoiceControlDecorator(baseMusic);

        light.on();
        music.on();
        thermostat.on();

        drainPower(15); // simulate battery usage
        System.out.println("Party Mode Active (" + currentMode.toUpperCase() + ")");
    }

    public void activateNightMode() {
        checkPower();
        if (power.getBatteryCapacity() < 30) return;

        System.out.println("\nActivating Night Mode...");
        resetDevices();
        currentMode = "night";

        light = new VoiceControlDecorator(new LightModeDecorator(baseLight, "night"));
        thermostat = new VoiceControlDecorator(new EnergySavingDecorator(baseThermostat, power));
        camera = new RemoteAccessDecorator(baseCamera);

        light.on();
        thermostat.on();
        camera.on();

        drainPower(10); // simulate battery usage
        System.out.println("Night Mode Active (" + currentMode.toUpperCase() + ")");
    }

    public void relaxMode() {
        checkPower();
        if (power.getBatteryCapacity() < 30) return;

        System.out.println("\nActivating Relax Mode...");
        resetDevices();
        currentMode = "relax";

        light = new LightModeDecorator(baseLight, "relax");
        music = new VoiceControlDecorator(baseMusic);

        light.on();
        music.on();
        thermostat.on();

        drainPower(12); // simulate battery usage
        System.out.println("Relax Mode Active (" + currentMode.toUpperCase() + ")");
    }

    public void activateSecurityMode() {
        checkPower();
        if (power.getBatteryCapacity() < 30) return;

        System.out.println("\nActivating Security Mode...");
        resetDevices();
        currentMode = "security";

        camera = new RemoteAccessDecorator(baseCamera);
        light = new EnergySavingDecorator(baseLight, power);
        thermostat = new EnergySavingDecorator(baseThermostat, power);

        camera.on();
        light.on();
        thermostat.on();

        drainPower(18); // simulate battery usage
        System.out.println("Security Mode Active (" + currentMode.toUpperCase() + ")");
    }

    public void leaveHome() {
        checkPower();
        if (power.getBatteryCapacity() < 30) return;

        System.out.println("\nLeaving Home...");
        resetDevices();
        currentMode = "away";

        camera = new RemoteAccessDecorator(baseCamera);
        light = new RemoteAccessDecorator(new EnergySavingDecorator(baseLight, power));
        thermostat = new RemoteAccessDecorator(new EnergySavingDecorator(baseThermostat, power));
        music = new RemoteAccessDecorator(baseMusic);

        camera.on();

        drainPower(10); // simulate battery usage
        System.out.println("Remote Access Enabled — Security Active (" + currentMode.toUpperCase() + ")");
    }


    public void returnHome() {
        System.out.println("\n🏡 Welcome Home!");
        resetDevices();
        currentMode = "normal";

        light.on();
        thermostat.on();
        camera.off();
        music.off();

        drainPower(5);
        System.out.println("✅ Normal Mode Restored — All systems ready");
    }

    public void normalMode() {
        System.out.println("\nSwitching to Normal Mode...");
        resetDevices();
        currentMode = "normal";
        System.out.println("All decorators removed — devices back to normal.");
    }
}
