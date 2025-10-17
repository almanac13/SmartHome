package decorators;

import devices.Device;

public abstract class DeviceDecorator implements Device {
    protected Device decoratedDevice;

    public DeviceDecorator(Device device) {
        this.decoratedDevice = device;
    }

    @Override
    public void on() {
        decoratedDevice.on();
    }

    @Override
    public void off() {
        decoratedDevice.off();
    }

    public Device getWrappedDevice() {
        return decoratedDevice;
    }
}