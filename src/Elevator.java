import java.util.LinkedList;
import java.util.Queue;

public class Elevator extends Thread {
    static final int MAX_FLOOR = 55;
    private static int SLEEP_TIME;
    private Direction direction = Direction.UP;
    private int floor = 0;
    private Request request;
    private final int id;

    public Elevator(int sleepPerFloor, int id) {
        SLEEP_TIME = sleepPerFloor;
        this.id = id;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        super.run();
        while (true) { // warning, no exit condition!!
            while (isAvailable()) {
                sleep();
                System.out.println(this);
            }

            if (this.floor != request.getSrcFloor()) {
                move(request.getSrcFloor());
            }

            move();
        }
    }

    public void addRequest(Request request) {
        this.request = request;
        this.direction = request.getDirection();
    }

    public void removeRequest() {
        this.request = null;
    }

    // checks elevator availability
    public boolean isAvailable() {
        return this.request == null;
    }

    // moves depending on direction, till reaches destination
    public void move() {

        while (this.floor != this.request.getDestFloor()) {
            sleep();

            this.floor -= this.direction == Direction.UP ? -1 : 1;
            System.out.println(this);
        }

        removeRequest();
    }

    // move elevator to floor
    public void move(int floor) {
        Direction oldDir = this.direction;

        this.direction = floor > this.floor ? Direction.UP : Direction.DOWN;

        while (Math.abs(floor - this.floor) != 0) {
            sleep();
            this.floor -= this.direction == Direction.UP ? -1 : 1;
            System.out.println(this);
        }

        this.direction = oldDir;
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Elevator " + id + " [" +
                "direction: " + direction +
                ", floor: " + floor +
                ", request: " + request +
                ']';
    }
}
