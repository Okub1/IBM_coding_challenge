import java.util.LinkedList;

public class Elevator {
    static final int MAX_FLOOR = 55;
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
            // TODO in case of no other requests, no need to move in certain direction till floor/MAX_FLOOR, and switch

            // removing all (in case of duplicates) requests that were in my direction and floor
            this.requests.removeIf(item -> item.direction == this.direction && this.floor == item.getDestFloor());
        } else {
            switchDirection();
        }
    }

    @Override
    public String toString() {
        return "Elevator[" +
                "direction: " + direction +
                ", floor: " + floor +
                ", requests: " + requests +
                ']';
    }
}
