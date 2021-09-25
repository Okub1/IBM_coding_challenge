package UI;

import App.Elevator;
import App.ElevatorHandler;
import App.Request;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GUI extends JFrame {
    private JTable requests;
    private JPanel panelElevators;
    private JScrollPane panelRequests;
    private JPanel mainPanel;
    private JButton button1;

    private final Elevator[] elevators = new Elevator[1];
    private ElevatorHandler elevatorHandler;

    public GUI() {
        super("Elevators demo - IBM coding challenge");
        setContentPane(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

//                for (Elevator elevator : elevators) {
//                    elevator.reset();
//                }

                Random random = new Random();

                elevatorHandler.addRequest(new Request(random.nextInt(55), random.nextInt(55)));
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        String[] header = new String[] {
                "From", "To", "(Direction)"
        };

        requests = new JTable();
        requests.getTableHeader().setReorderingAllowed(false);

        DefaultTableModel dm = new DefaultTableModel(0, 0);
        dm.setColumnIdentifiers(header);
        requests.setModel(dm);

        panelElevators = new DrawCanvas();
        panelElevators.setPreferredSize(new Dimension(640, 480));

        elevatorHandler = new ElevatorHandler((DrawCanvas) panelElevators, dm);

        // TODO elevator handler blocks GUI from working, therefore I need it to run GUI on separate thread (?)
//        elevatorHandler.start();
    }
}
