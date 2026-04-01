package a.entity.gus06.sys.base1.gui.viewer1.list;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.List;

public class EntityImpl implements Entity, I, V, G, E, S {

	public String creationDate() {return "20170508";}


	private Service listView;

	private Object base;
	private JPanel panel;
	private JLabel label_list;
	

	public EntityImpl() throws Exception
	{
		listView = Outside.service(this,"*gus06.data.collection.guilist1.delayed");
		
		label_list = new JLabel("List");
		label_list.setHorizontalAlignment(JLabel.CENTER);
		label_list.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label_list,BorderLayout.NORTH);
		panel.add((JComponent) listView.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public Object g() throws Exception
	{return listView.g();}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("base"))
		{
			base = obj;
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	public void e() throws Exception
	{
		if(base==null) return;
		listView.p(((G)base).g());
	}
	
	
	
	
	public void addActionListener(ActionListener l) throws Exception
	{listView.addActionListener(l);}
	
	public void removeActionListener(ActionListener l) throws Exception
	{listView.removeActionListener(l);}
	
	public List listeners() throws Exception
	{return listView.listeners();}
}
