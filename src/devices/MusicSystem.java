package devices;

public class MusicSystem implements Device {
    @Override
    public void on() {
        System.out.println("Music on");
    }

    @Override
    public void off() {
        System.out.println("Music off");
    }
}
