package a.entity.gus06.map.string.editor1.gui1;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, I, P, R, S {

	public String creationDate() {return "20200206";}

	
	private Service form;
	private Service table;
	private Service label;
	private Service buttons;
	
	private JPanel panel;
	
	private Map map;
	
	

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.map.string.editor1.gui1.form");
		table = Outside.service(this,"*gus06.map.string.editor1.gui1.table");
		label = Outside.service(this,"*gus06.map.string.editor1.gui1.label");
		buttons = Outside.service(this,"*gus06.map.string.editor1.gui1.buttons");
		
		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.add((JComponent) label.i(),BorderLayout.CENTER);
		p_bottom.add((JComponent) buttons.i(),BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) form.i(),BorderLayout.NORTH);
		panel.add((JComponent) table.i(),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		form.p(map);
		table.p(map);
		label.p(map);
		buttons.p(map);
	}
	
	
	public Object r(String key) throws Exception
	{return table.r(key);}
	
	public void addActionListener(ActionListener l) throws Exception
	{table.addActionListener(l);}
	
	public void removeActionListener(ActionListener l) throws Exception
	{table.removeActionListener(l);}
	
	public List listeners() throws Exception
	{return table.listeners();}
}
