package App;

import UI.DrawCanvas;

public class Elevator extends Thread {
    static final int MAX_FLOOR = Utility.MAX_FLOOR;

    private Direction direction = Direction.UP;
    private int floor = 0;
    private Request request;

    private DrawCanvas drawCanvas;

    public Elevator() {
    }

    public Elevator(DrawCanvas drawCanvas) {
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

    public Request getRequest() {
        return request;
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

            sleep(Utility.WAIT_TIME_ON_FLOOR);

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
        sleep(Utility.TRAVEL_TIME_PER_FLOOR);
    }

    private void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "App.Elevator [" +
                "direction: " + direction +
                ", floor: " + floor +
                ", request: " + request +
                ']';
    }
}
