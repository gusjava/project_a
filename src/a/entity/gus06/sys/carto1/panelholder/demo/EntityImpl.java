package a.entity.gus06.sys.carto1.panelholder.demo;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import java.awt.Color;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20161202";}


	private Service panelholder;
	private Service drawer2;
	private Service buildButtonColor;
	
	private Map rectMap;
	private Map linksMap;
	private Map colorMap;

	private JTextField field_start;
	private JTextField field_end;
	
	private JButton button_start;
	private JButton button_end;
	
	private JPanel panel;




	public EntityImpl() throws Exception
	{
		panelholder = Outside.service(this,"gus06.sys.carto1.panelholder");
		drawer2 = Outside.service(this,"gus06.sys.carto1.paneldrawer1");
		buildButtonColor = Outside.service(this,"gus06.swing.button.build.colorchooser");
		
		linksMap = new HashMap();
		colorMap = new HashMap();
		
		drawer2.v("linksMap",linksMap);
		drawer2.v("colorMap",colorMap);
		
		panelholder.v("drawer",drawer2);
		
		rectMap = (Map) panelholder.g();
		
		field_start = new JTextField();
		field_end = new JTextField();
		
		field_start.addActionListener(this);
		field_end.addActionListener(this);
		
		button_start = (JButton) buildButtonColor.i();
		button_end = (JButton) buildButtonColor.i();
		
		
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(field_start,BorderLayout.CENTER);
		p1.add(button_start,BorderLayout.EAST);
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(field_end,BorderLayout.CENTER);
		p2.add(button_end,BorderLayout.EAST);
		
		
		JPanel p_top = new JPanel(new GridLayout(1,2));
		p_top.add(p1);
		p_top.add(p2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(p_top,BorderLayout.NORTH);
		panel.add((JComponent) panelholder.i(),BorderLayout.CENTER);
	}



	public Object i() throws Exception
	{return panel;}
	
	public void actionPerformed(ActionEvent e)
	{perform();}

	
	
	
	private void perform()
	{
		try
		{
			String s_start = field_start.getText();
			String s_end = field_end.getText();
			
			Color color_start = button_start.getBackground();
			Color color_end = button_end.getBackground();
			
			colorMap.put(s_start,color_start);
			colorMap.put(s_end,color_end);
			
			
			if(!s_start.equals("") && !rectMap.containsKey(s_start))
				panelholder.p(s_start);
			
			if(!s_end.equals("") && !rectMap.containsKey(s_end))
				panelholder.p(s_end);
			
			if(!s_start.equals("") && !s_end.equals(""))
				addLink(s_start,s_end);
			
			panel.repaint();
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

	
	
	private void addLink(String s1, String s2)
	{
		if(!linksMap.containsKey(s1))
			linksMap.put(s1,new HashSet());
		Set set = (Set) linksMap.get(s1);
		
		if(!set.contains(s2)) set.add(s2);
	}
}
