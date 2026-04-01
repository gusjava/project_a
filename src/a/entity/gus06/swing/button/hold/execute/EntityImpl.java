package a.entity.gus06.swing.button.hold.execute;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P, G, I, E, V, ActionListener {

	public String creationDate() {return "20200407";}


	private Service paint;

	private JButton button;
	private E execute;
	
	public EntityImpl() throws Exception
	{
		paint = Outside.service(this,"gus06.swing.button.cust2.display");
		
		button = new JButton();
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{e_();}
	
	
	private void e_()
	{
		try{e();}
		catch(Exception e)
		{Outside.err(this,"e_()",e);}
	}
	
	
	
	public Object i() throws Exception
	{return button;}

	public Object g() throws Exception
	{return execute;}
	
	public void p(Object obj) throws Exception
	{execute = (E) obj;}
	
	public void e() throws Exception
	{if(execute!=null) execute.e();}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("display")) {setDisplay((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void setDisplay(String display)
	{
		try{paint.v(display,button);}
		catch(Exception e)
		{Outside.err(this,"setDisplay(String)",e);}
	}

}
