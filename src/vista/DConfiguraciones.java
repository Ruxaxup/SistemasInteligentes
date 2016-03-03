/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Ruxaxup
 */
public class DConfiguraciones extends JDialog implements ActionListener{
    JButton bClose;
    JTextField tfTiempo;
    EscenarioPanel ep;
    
    public DConfiguraciones(JFrame parent,EscenarioPanel ep){
        setTitle("Velocidad de ejecución");
        this.setLocationRelativeTo(parent);
        this.ep = ep;
        initComponents();
        addComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setPreferredSize(new Dimension(400,150));
        pack();
    }

    private void initComponents() {
        tfTiempo = new JTextField(10);        
        tfTiempo.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
        tfTiempo.setText("0");
        bClose = new JButton("Close");
        bClose.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bClose){
            int tiempo = Integer.valueOf(tfTiempo.getText());
            ep.setThreadSleep(tiempo);
            dispose();
        }
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        JPanel pSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        pSuperior.add(new JLabel("Velocidad de ejecución:"));
        pSuperior.add(tfTiempo);
        mainPanel.add(pSuperior);
        mainPanel.add(bClose);
        add(mainPanel);
    }
    
    class JTextFieldFilter extends PlainDocument {
        public static final String NUMERIC = "0123456789";

        protected String acceptedChars = null;

        protected boolean negativeAccepted = false;

        public JTextFieldFilter() {
          this(NUMERIC);
        }

        public JTextFieldFilter(String acceptedchars) {
          acceptedChars = acceptedchars;
        }

        public void setNegativeAccepted(boolean negativeaccepted) {
          if (acceptedChars.equals(NUMERIC)) {
            negativeAccepted = negativeaccepted;
            acceptedChars += "-";
          }
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
          if (str == null)
            return;

          for (int i = 0; i < str.length(); i++) {
            if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
              return;
          }

          if (negativeAccepted && str.indexOf("-") != -1) {
            if (str.indexOf("-") != 0 || offset != 0) {
              return;
            }
          }

          super.insertString(offset, str, attr);
        }
      }
}
