package a.entity.gus06.appli.gusclient1.gui.maingui;


import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140717";}

	private Service shiftPanel;
	private Service startgui;
	private Service maingui0;
	private Service menuBar;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		startgui = Outside.service(this,"gus06.appli.gusclient1.gui.startgui");
		
		if(!startgui.f(null)) {validate();return;}
		
		shiftPanel.p((JComponent) startgui.i());
		startgui.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public void actionPerformed(ActionEvent e)
	{validate();}
	
	
	
	
	
	private void validate()
	{
		try
		{
			maingui0 = Outside.service(this,"gus06.appli.gusclient1.gui.maingui0");
			shiftPanel.p(maingui0.i());
		}
		catch(Exception e)
		{Outside.err(this,"validate()",e);}
	}
}
