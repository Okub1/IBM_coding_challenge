package App;

public class Request {
    private int srcFloor;
    private int destFloor;
    private Direction direction;

    public Request(int srcFloor, int destFloor) {
        this.srcFloor = srcFloor;
        this.destFloor = destFloor;
        this.direction = srcFloor - destFloor < 0 ? Direction.UP : Direction.DOWN;
    }

    public Request(int srcFloor, int destFloor, Direction direction) {
        this.srcFloor = srcFloor;
        this.destFloor = destFloor;
        this.direction = direction;
    }

    public int getSrcFloor() {
        return srcFloor;
    }

    public int getDestFloor() {
        return destFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "App.Request[" +
                srcFloor +
                " -> " + destFloor +
                ", (" + direction +
                ")]";
    }
}
