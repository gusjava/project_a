package a.entity.gus.y.entityeditor1.maingui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20240113";}
	
	private Service buildData;
	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;

	private JPanel panel;
	private JTabbedPane tab;
	private JLabel labelTitle;
	private Icon entityIcon;

	private String entityName;
	private Object engine;
	private Object data;

	public EntityImpl() throws Exception
	{
		buildData = Outside.service(this, "gus.y.entityeditor1.builddata");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.entityeditor1.gui1.src");
		gui2 = Outside.service(this, "*gus.y.entityeditor1.gui2.doc");
		gui3 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos");
		gui4 = Outside.service(this, "*gus.y.entityeditor1.gui4.err");
		gui5 = Outside.service(this, "*gus.y.entityeditor1.gui5.db");

		entityIcon = (Icon) Outside.resource(this, "icon#ELEMENT_entity");

		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());

		tabHolder.v("FILE_java#Sources", gui1);
		tabHolder.v("UTIL_doc#Doc", gui2);
		tabHolder.v("UTIL_infos#Infos", gui3);
		tabHolder.v("UTIL_error#Errors", gui4);
		tabHolder.v("UTIL_debug#Debug", gui5);

		tab = (JTabbedPane) tabHolder.i();

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}

	public void p(Object obj) throws Exception
	{
		if (obj == null) {reset();return;}
		Object[] o = (Object[]) obj;
		if (o[0]==null || o[1]==null){reset();return;}
		
		if(engine!=null) ((S) engine).removeActionListener(this);
		engine = o[0];
		entityName = (String) o[1];
		((S) engine).addActionListener(this);
		
		data = buildData.t(new Object[]{engine,entityName});
		labelTitle.setText(entityName);
		labelTitle.setIcon(entityIcon);
		refreshGui();
	}
	
	public void actionPerformed(ActionEvent e)
	{handleEngineEvent(e.getActionCommand());}
	
	private void handleEngineEvent(String cmd)
	{
		if(cmd.equals("loaded()")) reload();
	}

	private void reset() throws Exception
	{
		if(engine!=null) ((S) engine).removeActionListener(this);
		entityName = null;
		engine = null;
		data = null;

		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		refreshGui();
	}
	
	private void reload()
	{
		try
		{
			if(engine==null) return;
			data = buildData.t(new Object[]{engine,entityName});
			refreshGui();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	private void refreshGui() throws Exception
	{
		gui1.p(data);
		gui2.p(data);
		gui3.p(data);
		gui4.p(data);
		gui5.p(data);
	}
}
