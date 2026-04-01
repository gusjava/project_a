package a.entity.gus06.awt.focus.focussupport.history;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, ActionListener {

	public String creationDate() {return "20170314";}

	

	private Service focusSupport;
	private List list;

	
	
	public EntityImpl() throws Exception
	{
		focusSupport = Outside.service(this,"gus06.awt.focus.focussupport");
		focusSupport.addActionListener(this);
		list = new ArrayList();
	}


	
	public void actionPerformed(ActionEvent e)
	{focusChanged();}
	
	
	
	
	private void focusChanged()
	{ 
		try
		{
			Object focused = focusSupport.g();
			if(focused==null) return;
			
			list.remove(focused);
			list.add(focused);
			
			newFocus();
		}
		catch(Exception e)
		{Outside.err(this,"focusChanged()",e);}
	}

	
	
	
	public Object g() throws Exception
	{return list;}
	
	
	private void newFocus()
	{send(this,"newFocus()");}
}
