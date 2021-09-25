package App;

import UI.DrawCanvas;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorHandler {
    private static final int NUMBER_OF_ELEVATORS = 7;
    static final int MILISECONDS_PER_FLOOR = 250; // used due to threads
    private final Elevator[] elevators = new Elevator[NUMBER_OF_ELEVATORS];
    private final Queue<Request> queue = new LinkedList<>();
    private final DefaultTableModel defaultTableModel;

    public ElevatorHandler(DrawCanvas drawCanvas, DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;

        for (int i = 0; i < this.elevators.length; i++) {
            this.elevators[i] = new Elevator(MILISECONDS_PER_FLOOR, i);
            elevators[i].addCanvas(drawCanvas);
            elevators[i].start();
        }

        drawCanvas.setElevators(elevators);
    }

    public void addRequest(Request request) {
        int best = checkAvailable();

        queue.add(request);

        String[] row = {"" + request.getSrcFloor(), "" + request.getDestFloor(), "" + request.getDirection()};
        this.defaultTableModel.addRow(row);
        this.defaultTableModel.fireTableDataChanged();


        System.out.println("Current waiting request: " + queue.peek());

        while (best == -1) {
            best = checkAvailable();
        }

        System.out.println("App.Request " + queue.peek() + " asigned to " + best);
        this.elevators[best].addRequest(queue.remove());
//        this.defaultTableModel.removeRow(0);
        this.defaultTableModel.fireTableDataChanged();
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
