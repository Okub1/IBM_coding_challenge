package UI;

import App.Direction;
import App.Elevator;
import App.Utility;

import javax.swing.*;
import java.awt.*;

public class DrawCanvas extends JPanel {
    private Elevator[] elevators;

    public DrawCanvas(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public DrawCanvas() {

    }

    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    // DO NOT TOUCH ANY NUMBERS PLEASE!!! <<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // took me whole day to set them properly
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        int padding = 50; // padding from all sides

        int width = super.getSize().width;
        int height = super.getSize().height;
        int count = this.elevators.length;

        int floors = Utility.MAX_FLOOR;

        int floor = ((height - padding) - padding) / floors;
        int elevatorWidth = floor * 2; // N x N pixels

        int frequency = (width - (padding * 2)) / count;

        int groundFloor = padding * 2;
        int maxFloor = height - groundFloor - floor * floors;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));

        for (int i = 0; i < count; i++) {
            int x = padding + i * frequency + elevatorWidth / 2;

            // vertical line of elevator shaft
            g.drawLine(x, height - groundFloor + floor, x, maxFloor - 1);

            // horizontal lines of elevator shaft
            g.drawLine(x - 10, maxFloor - 1, x + 10, maxFloor - 1);
            g.drawLine(x - 10, height - groundFloor + floor, x + 10, height - groundFloor + floor);

            // min floor text
            g.drawString("" + 0, x - 25, height - groundFloor + floor);

            // max floor text
            g.drawString("" + floors, x - 30, maxFloor + 5);

            // elevator rectangle
            g.setColor(elevators[i].getDirection() == Direction.UP ? Color.GREEN : Color.RED);
            g.drawRect(x - elevatorWidth / 2, height - groundFloor - elevators[i].getFloor() * floor, elevatorWidth, floor);

            // elevator current floor
            g.setColor(Color.WHITE);
            g.drawString("" + elevators[i].getFloor(), x + 5 + elevatorWidth, height - groundFloor - elevators[i].getFloor() * floor + floor);

            // display of elevator's request under floor indicators
            if (!elevators[i].isAvailable()) {
                g.setColor(elevators[i].getDirection() == Direction.UP ? Color.GREEN : Color.RED);

                String text = "" + elevators[i].getRequest().getSrcFloor() + "->" + "" + elevators[i].getRequest().getDestFloor();
                g.drawString(text, x - 18, height - padding + 20);
                g.setColor(Color.WHITE);
            }
        }

        // TODO
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
