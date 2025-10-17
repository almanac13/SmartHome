package decorators;

import home.Battery;
import devices.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    private Battery battery;

    public EnergySavingDecorator(Device device, Battery battery) {
        super(device);
        this.battery = battery;
    }

    @Override
    public void on() {
        decoratedDevice.on();
        System.out.println("Energy Saving decorated");
    }

    @Override
    public void off() {
        decoratedDevice.off();
        System.out.println("⚡ Energy Saving Mode: Deactivated");
    }
}