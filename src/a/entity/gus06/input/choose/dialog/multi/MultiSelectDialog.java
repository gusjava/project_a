package a.entity.gus06.input.choose.dialog.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MultiSelectDialog {

    public static List showMultiSelectDialog(
            Component parent,
            String message,
            String title,
            String[] values,
            List preselected
    ) {

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JCheckBox[] checkBoxes = new JCheckBox[values.length];
        for (int i = 0; i < values.length; i++) {
            JCheckBox cb = new BoldCheckBox(values[i]);
            if (preselected != null && preselected.contains(values[i])) {
                cb.setSelected(true);
            }
            checkBoxes[i] = cb;
            checkBoxPanel.add(cb);
        }

        JCheckBox selectAll = new BoldCheckBox("Cocher/D�cocher tout");
        selectAll.addActionListener(e -> {
            boolean state = selectAll.isSelected();
            for (JCheckBox cb : checkBoxes) {
                cb.setSelected(state);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(selectAll, BorderLayout.WEST);
        
        JScrollPane scroll = new JScrollPane(checkBoxPanel);
        scroll.setPreferredSize(new Dimension(250, 150));
		scroll.getVerticalScrollBar().setUnitIncrement(20);

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        panel.add(new JLabel(message), BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(
                parent,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            List selectedValues = new ArrayList();
            for (int i = 0; i < values.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    selectedValues.add(values[i]);
                }
            }
            return selectedValues;
        } 
        return null;
    }
}