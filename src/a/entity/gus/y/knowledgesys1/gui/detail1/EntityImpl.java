package a.entity.gus.y.knowledgesys1.gui.detail1;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import a.framework.*;

public class EntityImpl implements Entity, I, P
{
	public String creationDate() {return "20260423";}

	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;

	private JPanel panel;
	private JLabel labelTitle;
	private Icon icon;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui1.form");
		gui2 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui2.uplinks");
		gui3 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui3.downlinks");
		gui4 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui4.tags");

		icon = (Icon) Outside.resource(this, "icon#KNOWLEDGE");

		tabHolder.v("Form", gui1);
		tabHolder.v("Up links", gui2);
		tabHolder.v("Down links", gui3);
		tabHolder.v("Tags", gui4);

		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		JTabbedPane tab = (JTabbedPane) tabHolder.i();

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}

	public void p(Object obj) throws Exception
	{
		if (obj instanceof Map) {
			Map m = (Map) obj;
			labelTitle.setText(m.get("code") + ":" + m.get("action") + ":" + m.get("object"));
			labelTitle.setIcon(icon);
		} else {
			labelTitle.setText(" ");
			labelTitle.setIcon(null);
		}

		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
		gui4.p(obj);
	}
}
