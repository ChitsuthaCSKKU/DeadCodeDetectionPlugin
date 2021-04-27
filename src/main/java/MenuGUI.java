import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final ComboBox comboBox;
    private final JButton okButton;
    private final JLabel homeLebel;

    public MenuGUI() {

        // path: /Users/Peeradon/IdeaProjects/DeadCodeDetection/src
        // Desktop: /Users/Peeradon/Desktop

        // Create a Frame
        frame = new JFrame();

        //Create a ComboBox
        String[] deadCodes = {"-","DeadClass/Interface","DeadMethod","DeadParameter","DeadVariable"};
        comboBox = new ComboBox(deadCodes);
        comboBox.addActionListener(this);

        //Create a Button
        okButton = new JButton("OK");
        okButton.addActionListener(this);

        // Create Label
        homeLebel = new JLabel("Select DeadCode types to detect.");
        homeLebel.setBounds(10,10,10,10);

        // Create a Panel
        panel = new JPanel();
        // Default 30,30,10,30
        panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        panel.setLayout(new GridLayout(0,1));
        // Adding things to panel
        panel.add(homeLebel);
        panel.add(comboBox);
        panel.add(okButton);

        // Setting GUI

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dead Code Detector");

        frame.setBounds(500,500,500,500);
        frame.pack();


        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(comboBox.getSelectedItem().equals("DeadClass/Interface") &&
            e.getSource()==okButton){
            new SimpleInputGUI("DeadClass/Interface");
            frame.setVisible(false);
        }

        if(comboBox.getSelectedItem().equals("DeadParameter") &&
                e.getSource()==okButton){
            new SimpleInputGUI("DeadParameter");
            frame.setVisible(false);
        }

        if(comboBox.getSelectedItem().equals("DeadVariable") &&
                e.getSource()==okButton){
            new SimpleInputGUI("DeadVariable");
            frame.setVisible(false);
        }
        if(comboBox.getSelectedItem().equals("DeadMethod") &&
                e.getSource()==okButton){
            new DeadMethodGUI();
            frame.setVisible(false);
        }
        if(comboBox.getSelectedItem().equals("-") && e.getSource()==okButton) {
            JOptionPane.showMessageDialog(null,"Please Select DeadCode Type!",
                    "Error Dialog",JOptionPane.WARNING_MESSAGE);
        }

    }

}
