package UI;

public class GUIWrapper extends Thread{

    public GUIWrapper() {
        start();
    }

    @Override
    public synchronized void start() {
        super.start();
        GUI gui = new GUI();
    }
}
