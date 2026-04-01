package a.entity.gus06.file.editor.ext.xhtml.tab3;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTree;
import java.util.Map;
import java.io.File;
import java.util.List;
import javax.swing.tree.TreeModel;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, V, E {

	public String creationDate() {return "20200103";}

	
	private Service buildData;
	private Service tab;
	private Service guiHolder1;
	private Service guiHolder2;
	private Service guiHolder3;

	private JPanel panel;
	private JButton button;
	
	private JTree tree;
	private JTextArea comp;
	
	private File file;
	private Map data;


	public EntityImpl() throws Exception
	{
		buildData = Outside.service(this,"gus06.sys.xhtml1.include.findfiles2.asmap");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		guiHolder1 = Outside.service(this,"*gus06.sys.xhtml1.include.gui.panel");
		guiHolder2 = Outside.service(this,"*gus06.sys.xhtml1.ids.gui.panel");
		guiHolder3 = Outside.service(this,"*gus06.sys.xhtml1.search.gui.panel");
		
		tab.v("Includes", guiHolder1.i());
		tab.v("Ids", guiHolder2.i());
		tab.v("Search", guiHolder3.i());
		
		button = new JButton("Refresh");
		button.setEnabled(false);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{refresh();}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("comp")) {initComp((JTextArea) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		button.setEnabled(file!=null);
		refresh();
	}
	
	private void initComp(JTextArea comp1) throws Exception
	{
		if(comp!=null) throw new Exception("Comp already initialized");
		comp = comp1;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	private void refresh()
	{
		try
		{
			data = (Map) buildData.t(file);
			
			guiHolder1.p(data);
			guiHolder2.p(data);
			guiHolder3.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}