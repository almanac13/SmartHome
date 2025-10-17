package decorators;

import devices.Device;

public class RemoteAccessDecorator extends DeviceDecorator{
    public RemoteAccessDecorator(Device device) {
        super(device);
    }

    @Override
    public void on() {
        super.on();
        System.out.println("Remote access is on");
    }
    public void off() {
        super.off();
        System.out.println("Remote access is off");
    }
}
