package a.entity.gus06.input.choose.dialog.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoldCheckBox extends JCheckBox implements ItemListener {

    public BoldCheckBox(String text) {
        super(text);
        this.addItemListener(this);
        updateFont();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        updateFont();
    }

    private void updateFont() {
        if (this.isSelected()) {
            this.setFont(this.getFont().deriveFont(Font.BOLD));
        } else {
            this.setFont(this.getFont().deriveFont(Font.PLAIN));
        }
    }
}
