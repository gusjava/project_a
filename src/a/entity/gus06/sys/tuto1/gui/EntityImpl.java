package a.entity.gus06.sys.tuto1.gui;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, V, I {

	public String creationDate() {return "20160701";}


	private Service manager;
	private Service shiftPanel;
	
	private Object validator;
	
	
	private JSplitPane split;
	private JButton button_next;
	private JButton button_previous;
	private JPanel panel1;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"*gus06.sys.tuto1.manager");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		
		button_next = new JButton("Next");
		button_next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{next();}
		});
		button_previous = new JButton("Previous");
		button_previous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{previous();}
		});
		panel1 = (JPanel) shiftPanel.i();
		
		
		JPanel p_bottom = new JPanel(new GridLayout(1,2,5,5));
		p_bottom.add(button_previous);
		p_bottom.add(button_next);
		
		
		JPanel panel_right = new JPanel(new BorderLayout());
		panel_right.add(panel1,BorderLayout.CENTER);
		panel_right.add(p_bottom,BorderLayout.SOUTH);
		
		
		split = new JSplitPane();
		split.setRightComponent(panel_right);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("load")) {load(obj);return;}
		if(key.equals("validator")) {initValidator(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void load(Object obj) throws Exception
	{
		manager.v("load",obj);
	}
	
	
	private void initValidator(Object obj) throws Exception
	{
		validator = obj;
		shiftPanel.p(validator);
	}


	private void next()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"next()",e);}
	}

	
	private void previous()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"previous()",e);}
	}

}
