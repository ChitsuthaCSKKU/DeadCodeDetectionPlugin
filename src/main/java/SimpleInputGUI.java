import DeadClassInterface.DeadClassInterfaceDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for input path for DeadClass/Interface, DeadParameter and DeadVariable
public class SimpleInputGUI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JButton okButton;
    private final JTextField sourcePath;
    private final JTextField reportPath;
    private final JLabel sourceLabel;
    private final JLabel reportLabel;
    private String deadType;

    public SimpleInputGUI(String type) {
        this.deadType = type;
        frame = new JFrame();
        panel = new JPanel();

        okButton = new JButton("OK");
        okButton.addActionListener(this);

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
        panel.add(okButton);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dead Code Detection");

        frame.setBounds(300,300,250,250);
        frame.pack();
        frame.setVisible(true);

    }
    // path : /Users/Peeradon/IdeaProjects/DeadCodeDetection/src/main/java
    // Desktop : /Users/Peeradon/Desktop

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton){
            if(sourcePath.getText().isEmpty() || reportPath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter source or report " +
                        "path and try again!","Error Dialog",JOptionPane.WARNING_MESSAGE);
            }else {
                if(deadType.equals("DeadClass/Interface")){
                    DeadClassInterfaceDetector detector = new DeadClassInterfaceDetector();
                    detector.setInputSource(sourcePath.getText());
                    detector.run();
                    detector.createReport(reportPath.getText());
                    JOptionPane.showMessageDialog(null,"DeadClass/Interface " +
                                    "are Detected! \nReport created at "+reportPath.getText()+".",
                            "Message Dialog",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

            }


            //            JOptionPane.showMessageDialog(null,"You selected " +
//                    "DeadClass/Interface","Message Dialog",JOptionPane.PLAIN_MESSAGE);
//            System.exit(0);
        }
    }

}
