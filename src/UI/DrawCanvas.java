package UI;

import App.Direction;
import App.Elevator;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        int padding = 50; // padding from all sides

        int width = super.getSize().width;
        int height = super.getSize().height;
        int count = this.elevators.length;

        int floor = ((height - padding * 2) - padding) / 55;
        int elevatorWidth = floor * 2; // N x N pixels

        int frequency = (width - (padding * 2)) / count;

        g.setColor(Color.WHITE);

        for (int i = 0; i < count; i++) {
            int x = padding + i * frequency + elevatorWidth / 2;

            g.drawLine(x, padding - floor, x, height - padding * 2);

            g.setColor(elevators[i].getDirection() == Direction.UP ? Color.GREEN : Color.RED);
            g.drawRect(x - elevatorWidth / 2, height - padding * 2 - floor - elevators[i].getFloor() * floor, elevatorWidth, floor);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
            g.drawString("" + elevators[i].getFloor(), x, height - padding);
        }

        // TODO
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
