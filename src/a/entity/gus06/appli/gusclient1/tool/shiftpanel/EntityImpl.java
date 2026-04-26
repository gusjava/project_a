package a.entity.gus06.appli.gusclient1.tool.shiftpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140814";}

	private Service shiftPanel;
	private Service toolManager;
	private Service toolBuilder;
	
	private P toolObj;
	
	
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		toolManager = Outside.service(this,"gus06.appli.gusclient1.tool.manager");
		toolBuilder = Outside.service(this,"gus06.appli.gusclient1.tool.builder");
		
		toolManager.addActionListener(this);
		updateGui();
	}
	
	
		
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{toolObj.p(obj);}

	
	

	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	
	private void updateGui()
	{
		try
		{
			String id = (String) toolManager.g();
			toolObj = (P) toolBuilder.t(id);
			shiftPanel.p(toolObj);
			toolChanged();
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	private void toolChanged()
	{send(this,"toolChanged()");}
}
