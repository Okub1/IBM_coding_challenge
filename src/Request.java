public class Request {
    private int srcFloor;
    private int destFloor;
    Direction direction;

    public Request(int srcFloor, int destFloor, Direction direction) {
        this.srcFloor = srcFloor;
        this.destFloor = destFloor;
        this.direction = direction;
    }

    public int getSrcFloor() {
        return srcFloor;
    }

    public void setSrcFloor(int srcFloor) {
        this.srcFloor = srcFloor;
    }

    public int getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(int destFloor) {
        this.destFloor = destFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Request[" +
                "current floor:" + srcFloor +
                ", destination floor:" + destFloor +
                ", direction:" + direction +
                ']';
    }
}
