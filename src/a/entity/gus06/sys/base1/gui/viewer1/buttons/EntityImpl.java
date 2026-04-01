package a.entity.gus06.sys.base1.gui.viewer1.buttons;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Map;

public class EntityImpl extends S1 implements Entity, I, V, P {

	public String creationDate() {return "20170508";}



	private Object base;
	private String selectedId;
	private G mapProvider;
	
	private JPanel panel;
	
	private JButton button_add;
	private JButton button_remove;
	private JButton button_modify;
	private JButton button_refresh;
	private JButton button_clear;
	


	public EntityImpl() throws Exception
	{
		button_add = new JButton("add");
		button_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{add();}
		});
		
		button_remove = new JButton("remove");
		button_remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{remove();}
		});
		
		button_modify = new JButton("modify");
		button_modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{modify();}
		});
		
		button_refresh = new JButton("refresh");
		button_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		button_clear = new JButton("clear");
		button_clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{clear();}
		});
		
		panel = new JPanel(new GridLayout(1,0));
		panel.add(button_add);
		panel.add(button_remove);
		panel.add(button_modify);
		panel.add(button_refresh);
		panel.add(button_clear);
		
		updateButtonStates();
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		selectedId = (String) obj;
		updateButtonStates();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("base"))
		{
			base = obj;
			updateButtonStates();
			return;
		}
		if(key.equals("mapProvider"))
		{
			mapProvider = (G) obj;
			updateButtonStates();
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void updateButtonStates()
	{
		boolean hasBase = base!=null;
		boolean hasId = selectedId!=null;
		boolean hasMapProvider = mapProvider!=null;
		
		button_add.setEnabled(hasBase && hasMapProvider);
		button_remove.setEnabled(hasBase && hasId);
		button_modify.setEnabled(hasBase && hasId && hasMapProvider);
		button_refresh.setEnabled(hasBase);
		button_clear.setEnabled(hasBase);
	}

	
	
	private void clear()
	{
		try
		{
			if(base==null) return;
			
			((E)base).e();
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"clear()",e);}
	}
	
	
	
	
	private void add()
	{
		try
		{
			if(base==null) return;
			
			((V)base).v("map",mapProvider.g());
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}
	
	
	
	private void remove()
	{
		try
		{
			if(base==null) return;
			if(selectedId==null) return;
			
			((V)base).v("map_"+selectedId,null);
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}
	
	
	private void modify()
	{
		try
		{
			if(base==null) return;
			if(selectedId==null) return;
			
			((V)base).v("map_"+selectedId,mapProvider.g());
		}
		catch(Exception e)
		{Outside.err(this,"modify()",e);}
	}
	
	
	
	
	private void refresh()
	{send(this,"refresh()");}
}
