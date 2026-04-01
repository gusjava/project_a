package a.entity.gus06.swing.textcomp.autocopy.label;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.Icon;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190804";}


	private Service manager;
	private Icon icon;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.swing.textcomp.autocopy.manager");
		icon = (Icon) Outside.resource(this,"icon#TEXT_autoCopy");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		F holder = (F) manager.t(obj);
		return new JLabel1(holder);
	}
	
	
	private class JLabel1 extends JLabel implements ActionListener
	{
		private F holder;
		
		public JLabel1(F holder) throws Exception
		{
			super(" ");
			this.holder = holder;
			if(holder!=null) ((S) holder).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{updateGui();}
		
		private void updateGui()
		{
			boolean active = active(holder);
			setIcon(active?icon:null);
		}
	}
	
	private boolean active(F f)
	{
		try{return f.f(null);}
		catch(Exception e)
		{Outside.err(this,"active(F)",e);}
		return false;
	}
}