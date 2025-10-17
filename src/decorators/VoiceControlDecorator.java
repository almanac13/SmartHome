package decorators;

import devices.Device;

public class VoiceControlDecorator extends DeviceDecorator{
    public VoiceControlDecorator(Device device) {
        super(device);
    }

    @Override
    public void on() {
        super.on();
        System.out.println("Voice control is allowed");
    }
    public void off() {
        super.off();
        System.out.println("voice control off");
    }
}
