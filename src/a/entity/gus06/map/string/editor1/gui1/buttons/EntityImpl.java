package a.entity.gus06.map.string.editor1.gui1.buttons;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140911";}


	private Service complete;
	private Service replacer;
	
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		complete = Outside.service(this,"gus06.map.string.completefromclipboard");
		replacer = Outside.service(this,"gus06.map.string.replacer.ask");
		
		button1 = new JButton("Complete");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{complete();}
		});
		
		button2 = new JButton("Replace");
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt)
			{replace();}
		});
		
		panel = new JPanel(new GridLayout(1,2));
		panel.add(button1);
		panel.add(button2);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
	}
	
	
	
	
	
	
	private void complete()
	{
		try
		{
			if(map==null) return;
			complete.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"complete()",e);}
	}
	
	
	private void replace()
	{
		try
		{
			if(map==null) return;
			replacer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"replace()",e);}
	}

}
