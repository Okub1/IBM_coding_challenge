import java.util.Random;

public class App {
    public static void main(String[] args) {
        ElevatorHandler elevatorHandler = new ElevatorHandler();

        Random random = new Random(1);

        for (;;) {
            elevatorHandler.addRequest(new Request(random.nextInt(55), random.nextInt(55)));
        }
    }
}
