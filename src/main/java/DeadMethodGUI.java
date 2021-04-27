import DeadMethod.DeadMethodDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeadMethodGUI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JPanel buttonPanel;
    private final JButton okButton;
    private final JButton backButton;
    private final JTextField searchPath;
    private final JTextField sourcePath;
    private final JTextField reportPath;
    private final JTextField jarPath;
    private final JLabel searchLabel;
    private final JLabel sourceLabel;
    private final JLabel reportLabel;
    private final JLabel jarLabel;

    public DeadMethodGUI() {
        frame = new JFrame();
        panel = new JPanel();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        searchPath = new JTextField();
        sourcePath = new JTextField();
        sourcePath.setPreferredSize(new Dimension(700,10));
        reportPath = new JTextField();
        jarPath = new JTextField();

        searchLabel = new JLabel("Input File/Directory Path To Detect DeadMethod");
        sourceLabel = new JLabel("Input Whole Source Code File/Directory Path (Root of above source code path)");
        reportLabel = new JLabel("Input Detection Result Path");
        jarLabel = new JLabel("Input .jar File/Directory Path");

        panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        panel.setLayout(new GridLayout(0,1));

        panel.add(searchLabel);
        panel.add(searchPath);
        panel.add(sourceLabel);
        panel.add(sourcePath);
        panel.add(jarLabel);
        panel.add(jarPath);
        panel.add(reportLabel);
        panel.add(reportPath);

        buttonPanel.add(okButton);
        buttonPanel.add(backButton);

        Container container = frame.getContentPane();
        container.add(panel);
        container.add(buttonPanel,BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dead Code Detector");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton){
            if(sourcePath.getText().isEmpty() || reportPath.getText().isEmpty() || searchPath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter detection or source or report " +
                        "path and try again!","Error Dialog",JOptionPane.WARNING_MESSAGE);
            }else{
                DeadMethodDetector deadMethodDetector = new DeadMethodDetector();
                deadMethodDetector.setInputSearchPath(searchPath.getText());
                deadMethodDetector.setInputSourcePath(sourcePath.getText());

                if(!jarPath.getText().isEmpty()){
                    deadMethodDetector.setJarLibs(jarPath.getText());
                    deadMethodDetector.run();
                    deadMethodDetector.createReport(reportPath.getText());
                    JOptionPane.showMessageDialog(null,"DeadMethod " +
                                    "are Detected! \nReport created at "+reportPath.getText()+".",
                            "Message Dialog",JOptionPane.INFORMATION_MESSAGE);
                }

                deadMethodDetector.run();
                deadMethodDetector.createReport(reportPath.getText());
                JOptionPane.showMessageDialog(null,"DeadMethod " +
                                "are Detected! \nReport created at "+reportPath.getText()+".",
                        "Message Dialog",JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if(e.getSource() == backButton){
            frame.setVisible(false);
            new MenuGUI();
        }
    }
}
