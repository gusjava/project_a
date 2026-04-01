package a.entity.gus06.sys.mapediting1.gui.main;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JScrollPane;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20161209";}

	
	private Service form;
	private Service table;
	private Service label;
	private Service button;
	
	private JPanel panel;
	
	

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.sys.mapediting1.gui.form");
		table = Outside.service(this,"*gus06.sys.mapediting1.gui.table");
		label = Outside.service(this,"*gus06.sys.mapediting1.gui.label");
		button = Outside.service(this,"*gus06.sys.mapediting1.gui.button");
		
		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.add((JComponent) label.i(),BorderLayout.CENTER);
		p_bottom.add((JComponent) button.i(),BorderLayout.EAST);
		
		JComponent tableComp = (JComponent) table.i();
		JComponent formComp = (JComponent) form.i();
		JScrollPane tableScroll = new JScrollPane(tableComp);
		
		panel = new JPanel(new BorderLayout());
		panel.add(formComp,BorderLayout.NORTH);
		panel.add(tableScroll,BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		form.p(map);
		table.p(map);
		label.p(map);
		button.p(map);
	}
}
