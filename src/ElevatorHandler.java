public class ElevatorHandler {
    private static final int NUMBER_OF_ELEVATORS = 7;
    static final int MILISECONDS_PER_FLOOR = 250; // used due to threads
    private final Elevator[] elevators = new Elevator[NUMBER_OF_ELEVATORS];


    public ElevatorHandler() {
        for (int i = 0; i < this.elevators.length; i++) {
            this.elevators[i] = new Elevator(MILISECONDS_PER_FLOOR, i);
            elevators[i].start();
        }
    }

    public void addRequest(Request request) {
        int best = checkAvailable();

        System.out.println("Waiting request: " + request);

        while (best == -1) {
            best = checkAvailable();
        }

        System.out.println("Request " + request + " asigned to " + best);
        this.elevators[best].addRequest(request);
    }

    // checks for available elevators
    public int checkAvailable() {
        int best = -1;

        for (int i = 0; i < this.elevators.length; i++) {
            if (elevators[i].isAvailable()) {
                best = i;
                break;
            }
        }

        return best;
    }
}
