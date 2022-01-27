
package br.com.ads.utils;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LimparCamposUltis {
    
    public void LimpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component compo : components) {
            if(compo instanceof  JTextField) {
                ((JTextField)compo).setText(null);
            }
            
        }
    }

}
