package a.entity.gus06.sys.base2.gui.backup;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150329";}


	private Object base;
	
	private JPanel panel;
	
	private JButton button_backup;
	private JButton button_restore;
	private JButton button_refresh;


	public EntityImpl() throws Exception
	{
		button_backup = new JButton("backup");
		button_backup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{backup();}
		});
		
		button_restore = new JButton("restore");
		button_restore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{restore();}
		});
		
		button_refresh = new JButton("refresh");
		button_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		
		JPanel p_bottom = new JPanel(new GridLayout(1,0));
		p_bottom.add(button_backup);
		p_bottom.add(button_restore);
		p_bottom.add(button_refresh);
		
		panel = new JPanel(new BorderLayout());
		panel.add(p_bottom,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		refresh();
	}


	
	
	private void refresh()
	{
		try
		{
			if(base==null) return;
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	private void backup()
	{
		try
		{
			if(base==null) return;
		}
		catch(Exception e)
		{Outside.err(this,"backup()",e);}
	}
	
	private void restore()
	{
		try
		{
			if(base==null) return;
		}
		catch(Exception e)
		{Outside.err(this,"restore()",e);}
	}
}
