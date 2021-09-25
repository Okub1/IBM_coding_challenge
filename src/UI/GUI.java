package UI;

import App.Elevator;
import App.ElevatorHandler;
import App.Request;
import App.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GUI extends JFrame {
    private JTable requests;
    private JPanel panelElevators;
    private JScrollPane panelRequests;
    private JPanel mainPanel;
    private JButton button1;
    private JButton addRequestButton;

    private final Elevator[] elevators = new Elevator[1];
    private ElevatorHandler elevatorHandler;

    public GUI() {
        super("Elevators demo - IBM coding challenge");
        setContentPane(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        JFrame parent = this;

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

//                for (Elevator elevator : elevators) {
//                    elevator.reset();
//                }

                Random random = new Random();
                Random rng = new Random();
                int returnTrips = 50; // RNG is 50/50 for "return trips"/"floor accesses", change accordingly...

                if (returnTrips > rng.nextInt(100)) {
                    elevatorHandler.addRequest(new Request(random.nextInt(Utility.MAX_FLOOR), 0));
                } else {
                    elevatorHandler.addRequest(new Request(0, random.nextInt(Utility.MAX_FLOOR)));
                }
            }
        });

        addRequestButton.addActionListener(e -> new NewRequest(parent, elevatorHandler));
    }

    private void createUIComponents() {
        String[] header = new String[]{
                "From", "To", "(Direction)"
        };

        requests = new JTable();
        requests.getTableHeader().setReorderingAllowed(false);

        DefaultTableModel dm = new DefaultTableModel(0, 0);
        dm.setColumnIdentifiers(header);
        requests.setModel(dm);

        panelElevators = new DrawCanvas();
        panelElevators.setPreferredSize(new Dimension(Utility.MAX_WIDTH, Utility.MAX_HEIGHT)); // to change resolution, go to utility class...

        elevatorHandler = new ElevatorHandler((DrawCanvas) panelElevators, dm);

        elevatorHandler.start();
    }
}
