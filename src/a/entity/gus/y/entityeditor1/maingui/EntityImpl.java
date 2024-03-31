package a.entity.gus.y.entityeditor1.maingui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240113";}
	
	private Service buildData;
	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;

	private JPanel panel;
	private JTabbedPane tab;
	private JLabel labelTitle;
	private Icon entityIcon;

	private String entityName;
	private Object data;

	public EntityImpl() throws Exception {
		buildData = Outside.service(this, "gus.y.entityeditor1.builddata");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.entityeditor1.gui1.src");
		gui2 = Outside.service(this, "*gus.y.entityeditor1.gui2.doc");
		gui3 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos");
		gui4 = Outside.service(this, "*gus.y.entityeditor1.gui4.err");

		entityIcon = (Icon) Outside.resource(this, "icon#ELEMENT_entity");

		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		tabHolder.v("FILE_java#Sources", gui1);
		tabHolder.v("UTIL_doc#Doc", gui2);
		tabHolder.v("UTIL_infos#Infos", gui3);
		tabHolder.v("UTIL_error#Errors", gui4);

		tab = (JTabbedPane) tabHolder.i();

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}

	public void p(Object obj) throws Exception {
		if (obj == null) {
			reset();
			return;
		}
		Object[] o = (Object[]) obj;
		entityName = (String) o[1];
		
		if (entityName==null) {
			reset();
			return;
		}
		
		data = buildData.t(obj);
		labelTitle.setText(entityName);
		labelTitle.setIcon(entityIcon);
		gui1.p(data);
		gui2.p(data);
		gui3.p(data);
		gui4.p(data);
	}

	private void reset() throws Exception {
		entityName = null;
		data = null;

		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		gui1.p(null);
		gui2.p(null);
		gui3.p(null);
		gui4.p(null);
	}

	public Object i() throws Exception {
		return panel;
	}
}
