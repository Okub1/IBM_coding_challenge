public class App {
    public static void main(String[] args) {
        ElevatorHandler elevatorHandler = new ElevatorHandler();

        elevatorHandler.addRequest(new Request(0, 25));
    }
}
