package decorators;

import devices.Device;
import java.util.Scanner;

public class LightModeDecorator extends DeviceDecorator {
    private String mode;

    public LightModeDecorator(Device device, String mode) {
        super(device);
        this.mode = mode;
    }

    @Override
    public void on() {
        super.on();
        switch (mode.toLowerCase()) {
            case "party":
                discoMode();
                break;
            case "night":
                nightMode();
                break;
            case "relax":
                relaxMode();
                break;
            default:
                System.out.println("Default light mode ON");
        }
    }


    @Override
    public void off() {
        super.off();
        System.out.println("Light Mode Deactivated — Lights OFF");
    }


    private void discoMode() {
        System.out.println("Party Mode — Flashing disco lights!");
    }

    private void nightMode() {
        System.out.println("Night Mode — Soft warm light ON.");
    }

    private void relaxMode() {
        System.out.println("Relax Mode — Dim yellow light ON.");
    }

}
