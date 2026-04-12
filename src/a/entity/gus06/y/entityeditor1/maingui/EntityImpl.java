package a.entity.gus06.y.entityeditor1.maingui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251115";}


	private Service tabHolder;
	private Service buildData;
	private Service gui1;
	private Service gui2;
	private Service gui3;

	private JPanel panel;
	private JTabbedPane tab;
	private JLabel labelTitle;
	private Icon entityIcon;

	private String entityName;
	private Object data;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.swing.tabbedpane.holder1");
		buildData = Outside.service(this, "gus.y.entityeditor1.builddata");
		gui1 = Outside.service(this, "*gus.y.entityeditor1.gui1.src");
		gui2 = Outside.service(this, "*gus.y.entityeditor1.gui2.err");
		gui3 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos");
		
		entityIcon = (Icon) Outside.resource(this, "icon#ENTITY");

		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		tabHolder.v("FILE_java#Sources", gui1);
		tabHolder.v("UTIL_error#Errors", gui2);
		tabHolder.v("UTIL_infos#Infos", gui3);
		
		tab = (JTabbedPane) tabHolder.i();

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}

	public void p(Object obj) throws Exception
	{
		if (obj == null)
		{reset();return;}
		
		Object[] o = (Object[]) obj;
		entityName = (String) o[1];
		
		if (entityName==null)
		{reset();return;}
		
		data = buildData.t(obj);
		labelTitle.setText(entityName);
		labelTitle.setIcon(entityIcon);
		handleData(data);
	}

	private void reset() throws Exception
	{
		entityName = null;
		data = null;

		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		handleData(null);
	}
	
	private void handleData(Object data) throws Exception
	{
		gui1.p(data);
		gui2.p(data);
		gui3.p(data);
	}
}