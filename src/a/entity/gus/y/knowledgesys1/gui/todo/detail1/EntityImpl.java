package a.entity.gus.y.knowledgesys1.gui.todo.detail1;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, I, P {
	public String creationDate() {return "20260423";}

	private JPanel panel;
	private JLabel labelTitle;
	private JTextArea textDescription;

	public EntityImpl() throws Exception {
		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		textDescription = new JTextArea();
		textDescription.setEditable(false);
		textDescription.setLineWrap(true);
		textDescription.setWrapStyleWord(true);

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(new JScrollPane(textDescription), BorderLayout.CENTER);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		if (obj instanceof Map) {
			Map m = (Map) obj;
			labelTitle.setText(m.get("code") + ":" + m.get("title"));
			Object desc = m.get("description");
			textDescription.setText(desc != null ? desc.toString() : "");
		} else {
			labelTitle.setText(" ");
			textDescription.setText("");
		}
	}
}
