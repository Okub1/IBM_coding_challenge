package UI;

import App.Direction;
import App.ElevatorHandler;
import App.Request;
import App.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;

public class NewRequest extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider slider1;
    private JRadioButton UPRadioButton;
    private JRadioButton DOWNRadioButton;
    private final ElevatorHandler elevatorHandler;

    public NewRequest(JFrame parent, ElevatorHandler elevatorHandler) {
        setTitle("New request");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(parent);
        setSize(new Dimension(700, 200));
//        setModal(true); not needed anyway...
        setVisible(true);

        this.elevatorHandler = elevatorHandler;

        ButtonGroup group = new ButtonGroup();
        group.add(UPRadioButton);
        group.add(DOWNRadioButton);

        this.slider1.setMaximum(Utility.MAX_FLOOR);

        UPRadioButton.setSelected(true);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void onOK() {
        elevatorHandler.addRequest(UPRadioButton.isSelected() ? new Request(0, slider1.getValue()) : new Request(slider1.getValue(), 0));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
