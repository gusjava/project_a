package a.entity.gus.y.docview1.gui1.first;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20231231";}

	private Service buildList;
	
	private JPanel panel;
	private JTextArea area;

	public EntityImpl() throws Exception {
		buildList = Outside.service(this, "gus.y.docview1.buildlist");
		
		area = new JTextArea();
		area.setMargin(new Insets(5, 5, 5, 5));
		area.setEditable(false);
		area.setFont(area.getFont().deriveFont((float) 18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	public void p(Object obj) throws Exception {
		List data = (List) buildList.t(obj);
		if(data.isEmpty()) {
			area.setText("");
		}
		else {
			String[] infos = (String[]) data.get(0);
			area.setText(infos[1]);
			area.setCaretPosition(0);
		}
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
