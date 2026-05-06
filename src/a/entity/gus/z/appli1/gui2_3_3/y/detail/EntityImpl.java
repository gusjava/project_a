package a.entity.gus.z.appli1.gui2_3_3.y.detail;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240113";}

	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;

	private JPanel panel;
	private JTabbedPane tab;
	private JLabel labelTitle;
	private Icon entityIcon;

	private String yName;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.z.appli1.gui2_3_3.y.detail.entities");
		gui2 = Outside.service(this, "*gus.z.appli1.gui2_3_3.y.detail.doc");
		gui3 = Outside.service(this, "*gus.z.appli1.gui2_3_3.y.detail.infos");

		entityIcon = (Icon) Outside.resource(this, "icon#ELEMENT_entity");

		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		tabHolder.v("ELEMENT_entity#Entities", gui1);
		tabHolder.v("UTIL_doc#Doc", gui2);
		tabHolder.v("UTIL_infos#Infos", gui3);

		tab = (JTabbedPane) tabHolder.i();

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{
		return panel;
	}

	public void p(Object obj) throws Exception
	{
		if (obj == null) { reset(); return; }
		yName = (String) obj;
		labelTitle.setText(yName);
		labelTitle.setIcon(entityIcon);
		refreshGui();
	}

	private void reset() throws Exception
	{
		yName = null;
		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		refreshGui();
	}

	private void refreshGui() throws Exception
	{
		gui1.p(yName);
		gui2.p(yName);
		gui3.p(yName);
	}
}
