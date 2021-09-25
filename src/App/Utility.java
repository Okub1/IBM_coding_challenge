package App;

public class Utility {
    // all times in milliseconds
    public static final int TRAVEL_TIME_PER_FLOOR = 100; // travel time between floors
    public static final int WAIT_TIME_ON_FLOOR = 2500; // waiting time on floor (destination floor of request)

    // whole app is scalable, you can set any number within range
    // (just bear in mind, that you need large enough display to show everything :D)
    public static int MAX_FLOOR = 55; // set by assignment
    public static int NUMBER_OF_ELEVATORS = 7; // set by assignment

    // resolution for elevator canvas, change according to your needs
    // also, canvas is resizable
    public static int MAX_WIDTH = 640;
    public static int MAX_HEIGHT = 480;

}
