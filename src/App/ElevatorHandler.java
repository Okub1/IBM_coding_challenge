package App;

import UI.DrawCanvas;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorHandler extends Thread {
    private final Elevator[] elevators = new Elevator[Utility.NUMBER_OF_ELEVATORS];
    private final Queue<Request> queue = new LinkedList<>();
    private final DefaultTableModel defaultTableModel;

    public ElevatorHandler(DrawCanvas drawCanvas, DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;

        for (int i = 0; i < this.elevators.length; i++) {
            this.elevators[i] = new Elevator();
            elevators[i].addCanvas(drawCanvas);
            elevators[i].start();
        }

        drawCanvas.setElevators(elevators);
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                Thread.sleep(200); // did not how to make this work using sync of threads (await, notify, etc)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (queue.isEmpty()) {
                continue;
            }

            System.out.println("Current waiting request: " + queue.peek());

            int best = checkAvailable();

            if (best != -1) {
                System.out.println("App.Request " + queue.peek() + " asigned to " + best);
                this.elevators[best].addRequest(queue.remove());
                this.defaultTableModel.removeRow(0);
                this.defaultTableModel.fireTableDataChanged();
            }
        }
    }

    public void addRequest(Request request) {
        queue.add(request);

        String[] row = {"" + request.getSrcFloor(), "" + request.getDestFloor(), "" + request.getDirection()};
        this.defaultTableModel.addRow(row);
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
