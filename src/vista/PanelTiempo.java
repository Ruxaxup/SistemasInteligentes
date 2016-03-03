/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Ruxaxup
 */
public class PanelTiempo extends JPanel{
    JLabel lTiempo;
    
    public PanelTiempo(){
        initComponents();
        addComponents();
    }

    private void initComponents() {
        Font label = new Font("TimesRoman",Font.PLAIN,18);
        lTiempo = new JLabel("Días:");
        setBorder(new TitledBorder("Tiempo Transcurrido"));
        ((TitledBorder)getBorder()).setTitleFont(label);
    }

    private void addComponents() {
        add(lTiempo);
    }
    
    public void setTime(long tiempo){
        lTiempo.setText("Dias: "+tiempo);
    }
}
