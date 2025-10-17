package devices;

public class SecurityCamera implements Device {
    @Override
    public void on() {
        System.out.println("Security camera on");
    }

    @Override
    public void off() {
        System.out.println("Security camera off");
    }
}
