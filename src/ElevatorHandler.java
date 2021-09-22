public class ElevatorHandler {
    private static final int NUMBER_OF_ELEVATORS = 7;
    private static final int MILISECONDS_PER_FLOOR = 500; // used due to threads
    private final Elevator[] elevators = new Elevator[NUMBER_OF_ELEVATORS];


    public ElevatorHandler() {
        for (int i = 0; i < this.elevators.length; i++) {
            this.elevators[i] = new Elevator();
        }

        // TODO create thread for each elevator and proceed to move every 0.5 sec
    }

    public void addRequest(Request request) {
        // TODO check for best available elevator and add request to it

        // TODO check if elevator is available,
        //  check direction of all elevators, check if they are going my way

        // check for most suitable elevator
        int best = -1;
        int bestScore = 0;
        for (int i = 0; i < elevators.length; i++) {
            System.out.print(i + " ");
            int score = 0;

            if (request.getDirection() == Direction.UP) {
                if (this.elevators[i].getFloor() < request.getDestFloor()) {
                    System.out.println("available"); // TODO
                } else {
                    System.out.println("not available");
                }
            } else {
                if (this.elevators[i].getFloor() > request.getDestFloor()) {
                    System.out.println("available");
                } else {
                    System.out.println("not available");
                }
            }

            if (score > bestScore) {
                bestScore = score;
                best = i;
            }
            System.out.println(" " + this.elevators[i]);
        }


    }

    // returns true if there is any available elevator for certain floor
    // also, this method rendered useless as of usage of threads there will be always elevator available,
    // because they move, so they receive order dynamically
    public boolean checkAvailableElevator(int floor, Direction direction) {
        boolean temp = false;

        if (floor >= 0 && floor <= Elevator.MAX_FLOOR) {
            for (Elevator elevator : this.elevators) {
                if (
                        (elevator.getDirection() == direction && elevator.getFloor() >= floor)
                                || (elevator.getDirection() == direction && elevator.getFloor() <= floor)
                ) {
                    temp = true;
                    break;
                }
            }
        }

        return temp;
    }
}
