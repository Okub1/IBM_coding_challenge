import java.util.LinkedList;

public class Elevator {
    private static final int MAX_FLOOR = 55;
    private Direction direction = Direction.UP;
    private int floor = 0;
    private LinkedList<Request> requests = new LinkedList<>();

    public Elevator() {
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void switchDirection() {
        this.direction = this.direction == Direction.UP ? Direction.DOWN : Direction.UP;
    }

    public int getFloor() {
        return floor;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void move() {
        if (this.floor > 0 && this.floor < MAX_FLOOR) {
            this.floor -= this.direction == Direction.UP ? -1 : 1;

//            for (Request item : this.requests) {
//                if (item.direction == this.direction) {
//
//                }
//            }
        } else {
            switchDirection();
        }
    }
}
