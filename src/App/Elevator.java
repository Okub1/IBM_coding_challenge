package App;

import UI.DrawCanvas;

public class Elevator extends Thread {
    static final int MAX_FLOOR = 55;
    private static int SLEEP_TIME;
    private Direction direction = Direction.UP;
    private int floor = 0;
    private Request request;
    private final int id;

    private DrawCanvas drawCanvas;

    public Elevator(int sleepPerFloor, int id) {
        SLEEP_TIME = sleepPerFloor;
        this.id = id;
    }

    public Elevator(DrawCanvas drawCanvas) {
        SLEEP_TIME = 250;
        this.id = 0;
        this.drawCanvas = drawCanvas;
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
//                System.out.println(this);
            }

            if (this.floor != request.getSrcFloor()) {
                move(request.getSrcFloor());
            }

            move();
        }
    }

    public int getFloor() {
        return floor;
    }

    // FOR TESTING ONLY!!
    public void reset() {
        this.request = null;
        this.floor = 0;
        this.direction = Direction.UP;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addCanvas(DrawCanvas drawCanvas) {
        this.drawCanvas = drawCanvas;
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

        try {
            while (this.floor != this.request.getDestFloor()) {
                sleep();

                this.floor -= this.direction == Direction.UP ? -1 : 1;
//                System.out.println(this);
                drawCanvas.repaint();
            }

            removeRequest();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    // move elevator to floor
    public void move(int floor) {
        Direction oldDir = this.direction;

        this.direction = floor > this.floor ? Direction.UP : Direction.DOWN;

        while (Math.abs(floor - this.floor) != 0) {
            sleep();
            this.floor -= this.direction == Direction.UP ? -1 : 1;
//            System.out.println(this);
            drawCanvas.repaint();
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
        return "App.Elevator " + id + " [" +
                "direction: " + direction +
                ", floor: " + floor +
                ", request: " + request +
                ']';
    }
}
