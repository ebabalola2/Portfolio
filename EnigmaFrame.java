import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {
    private JTextField Encrypt;
    private JTextField Decrypt;
    private JTextField InPosField;
    private JLabel lInPosField;
    private String[] rNum = {"1","2","3","4","5"};
    
    private JComboBox<String> bInner;
    private JComboBox<String> bMiddle;
    private JComboBox<String> bOut;

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JLabel inputAreaL;
    private JLabel outputAreaL;


    private int inInner =1;
    private int inMiddle =1;
    private int inOut =1;

    private JPanel top;
    private JPanel north;
    private JPanel center;
    private JPanel center2;
    private JPanel south;

    private JLabel lInner;
    private JLabel lMiddle;
    private JLabel lOut;

    private Enigma e;
    private JPanel rInner;
    private JPanel rMiddle;
    private JPanel rOut;
    private JPanel rotors;
    public EnigmaFrame(){
        super();
        //TODO: Complete the GUI
        top = new JPanel(new BorderLayout());

        north = new JPanel();
        center = new JPanel(new BorderLayout());
        center2 = new JPanel(new BorderLayout());
        south = new JPanel(new BorderLayout()); 

        InPosField= new JTextField(5);
        lInPosField = new JLabel("Initial Position");
        JButton bEncrypt = new JButton("Encrypt");
        JButton bDecrypt = new JButton("Decrypt");
        //Making labels
        lInner = new JLabel("Inner");
        lMiddle = new JLabel("Middle");
        lOut = new JLabel("Out");

        inputAreaL = new JLabel("Input");
        outputAreaL = new JLabel("Output");
        inputArea = new JTextArea(4,20);
        outputArea = new JTextArea(4,20);
        outputArea.setEditable(false);
        //Making JPanel for the Comobo box
        rInner = new JPanel(new BorderLayout());
        rMiddle = new JPanel(new BorderLayout());
        rOut = new JPanel(new BorderLayout());
        rotors = new JPanel(new BorderLayout());

        bInner= new JComboBox<String>();
        bMiddle = new JComboBox<String>();
        bOut = new JComboBox<String>();

        for (int i=0;i<rNum.length;i++) {
            bInner.addItem(rNum[i]);
            bMiddle.addItem(rNum[i]);
            bOut.addItem(rNum[i]);
        }

        bInner.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
              if (e.getSource()== bInner) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                  String x= (String)e.getItem();
                  inInner = Integer.parseInt(x);
                }
              }
            }
          });
  
          bMiddle.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
              if (e.getSource() == bMiddle) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                  String x= (String)e.getItem();
                  inMiddle = Integer.parseInt(x);
                }
              }
            }
          });
  
          bOut.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
              if (e.getSource() == bOut) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                  String x = (String)e.getItem();
                  inOut = Integer.parseInt(x);
                }
              }
            }
          });
          Encrypt = new JTextField();
        bEncrypt.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Enigma f = new Enigma(inInner,inMiddle,inOut,InPosField.getText());
            outputArea.setText(f.encrypt(inputArea.getText()));
          }
        });
        Decrypt = new JTextField();
        bDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              Enigma f= new Enigma(inInner,inMiddle,inOut,InPosField.getText());
              outputArea.setText(f.decrypt(inputArea.getText()));
            }
          });

          rInner.add(lInner,BorderLayout.WEST);
          rInner.add(bInner,BorderLayout.EAST);

          rMiddle.add(lMiddle,BorderLayout.WEST);
          rMiddle.add(bMiddle,BorderLayout.EAST);

          rOut.add(lOut,BorderLayout.WEST);
          rOut.add(bOut,BorderLayout.EAST);

         rotors.add(rInner,BorderLayout.WEST);
         rotors.add(rMiddle,BorderLayout.CENTER);
         rotors.add(rOut,BorderLayout.EAST);
         
         north.add(rotors);
         north.add(lInPosField);
         north.add(InPosField);
         north.add(bEncrypt);
         north.add(bDecrypt);

        center2.add(inputAreaL,BorderLayout.WEST);
        center2.add(inputArea,BorderLayout.CENTER);

        south.add(outputAreaL,BorderLayout.WEST);
        south.add(outputArea,BorderLayout.CENTER);

        center.add(center2,BorderLayout.NORTH);
        center.add(south,BorderLayout.SOUTH);

        top.add(north,BorderLayout.NORTH);
        top.add(center,BorderLayout.CENTER);
        setFrame();

    }

    private void setFrame()
    {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Enigma GUI");
      this.getContentPane().add(top);
      this.pack();
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
    }


}
