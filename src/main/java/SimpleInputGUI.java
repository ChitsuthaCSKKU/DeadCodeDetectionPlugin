import DeadClassInterface.DeadClassInterfaceDetector;
import DeadVariableParameter.DeadParameter.DeadParameterDetector;
import DeadVariableParameter.DeadVariable.DeadVariableDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for input path for DeadClass/Interface, DeadParameter and DeadVariable
public class SimpleInputGUI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JPanel buttonPanel;
    private final JButton okButton;
    private final JButton backButton;
    private final JTextField sourcePath;
    private final JTextField reportPath;
    private final JLabel sourceLabel;
    private final JLabel reportLabel;
    private String deadType;

    public SimpleInputGUI(String type) {
        this.deadType = type;
        frame = new JFrame();
        panel = new JPanel();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        sourcePath = new JTextField();
        sourcePath.setPreferredSize(new Dimension(700,10));
        reportPath = new JTextField();

        sourceLabel = new JLabel("Input Source Code File/Directory Path");
        reportLabel = new JLabel("Input Detection Result Path");


        panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        panel.setLayout(new GridLayout(0,1));

        panel.add(sourceLabel);
        panel.add(sourcePath);
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
            if(sourcePath.getText().isEmpty() || reportPath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter source or report " +
                        "path and try again!","Error Dialog",JOptionPane.WARNING_MESSAGE);
            }else {
                String source = sourcePath.getText();
                String report = reportPath.getText();

                if(deadType.equals("DeadClass/Interface")){
                    DeadClassInterfaceDetector detector = new DeadClassInterfaceDetector();
                    detector.setInputSource(source);
                    detector.run();
                    detector.createReport(report);
                    JOptionPane.showMessageDialog(null,"DeadClass/Interface " +
                                    "are Detected! \nReport created at "+reportPath.getText()+".",
                            "Message Dialog",JOptionPane.INFORMATION_MESSAGE);
                }
                if(deadType.equals("DeadParameter")){
                    DeadParameterDetector deadParameterDetector = new DeadParameterDetector();
                    deadParameterDetector.setInputSource(source);
                    deadParameterDetector.run();
                    deadParameterDetector.createReport(report);
                    JOptionPane.showMessageDialog(null,"DeadParameter " +
                                    "are Detected! \nReport created at "+reportPath.getText()+".",
                            "Message Dialog",JOptionPane.INFORMATION_MESSAGE);
                }
                if(deadType.equals("DeadVariable")){
                    DeadVariableDetector deadVariableDetector = new DeadVariableDetector();
                    deadVariableDetector.setInputSource(source);
                    deadVariableDetector.run();
                    deadVariableDetector.createReport(report);
                    JOptionPane.showMessageDialog(null,"DeadVariable " +
                                    "are Detected! \nReport created at "+reportPath.getText()+".",
                            "Message Dialog",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if(e.getSource() == backButton){
            frame.setVisible(false);
            new MenuGUI();
        }
    }

}
