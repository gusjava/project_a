package a.entity.gus06.swing.button.toggleaction.builder0;

import a.framework.*;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220526";}

	public final static Border EMPTY = BorderFactory.createEmptyBorder();
	public final static Dimension DIM = new Dimension(20,20);


	private Service repaint;

	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.button.cust2.icon.tooltip");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String display = (String) o[0];
		E executeOn = (E) o[1];
		E executeOff = (E) o[2];
		
		return new JToggleButton1(display, executeOn, executeOff);
	}
	
	
	private class JToggleButton1 extends JToggleButton implements ActionListener
	{
		private String display;
		private E executeOn;
		private E executeOff;
		
		private boolean state = false;
		
		public JToggleButton1(String display, E executeOn, E executeOff) throws Exception
		{
			super();
			this.display = display;
			this.executeOn = executeOn;
			this.executeOff = executeOff;
			
    			setBorder(EMPTY);
    			setMinimumSize(DIM);
		    	setMaximumSize(DIM);
		
			repaint.v(display,this);
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			boolean done = execute(state ? executeOff : executeOn);
			if(done) state = !state;
		}
	}
	
	
	
	private boolean execute(E execute)
	{
		try
		{
			execute.e();
			return true;
		}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
		return false;
	}
}
