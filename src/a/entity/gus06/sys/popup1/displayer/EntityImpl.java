package a.entity.gus06.sys.popup1.displayer;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JDialog;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20161005";}


	private Service gui;
	private Service pack1;
	private Service buildController;
	private Service dragFrame;
	private Service onDelete;
	
	private JComponent guiComp;
	private JDialog dialog;
	private E controller;
	
	private List list;
	private F retainer;


	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.sys.popup1.gui.panel");
		pack1 = Outside.service(this,"gus06.awt.window.pack1");
		buildController = Outside.service(this,"gus06.sys.popup1.gui.controller");
		dragFrame = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		onDelete = Outside.service(this,"gus06.swing.comp.cust3.execute.del");
		
		list = new ArrayList();
		
		guiComp = (JComponent) gui.i();
		
		dragFrame.p(guiComp);
		onDelete.p(new Object[]{guiComp, (E) this::hide});
		
		dialog = new JDialog();
		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);
		dialog.setResizable(false);
		dialog.setContentPane(guiComp);
		dialog.setLocation(10,10);
		dialog.setSize(10,10);
		
		retainer = (F) o->retainPopup();
		
		controller = (E) buildController.t(dialog);
		((V)controller).v("retainer", retainer);
		((S) controller).addActionListener(this);
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map notif = (Map) obj;
		addNotif(notif);
		controller.e();
	}


	public void actionPerformed(ActionEvent e)
	{clearAll();}
	
	
	
	
	private synchronized void addNotif(Map notif)
	{
		try
		{
			list.add(notif);
			gui.p(list);
			pack1.p(dialog);
			dialog.setLocation(10,10);
		}
		catch(Exception e)
		{Outside.err(this,"addNotif(Map)",e);}
	}
	
	
	private synchronized void clearAll()
	{
		try
		{
			list.clear();
			gui.p(list);
			pack1.p(dialog);
			dialog.setLocation(10,10);
		}
		catch(Exception e)
		{Outside.err(this,"clearAll()",e);}
	}
	
	
	private void hide()
	{
		dialog.setVisible(false);
	}
	
	
	private boolean retainPopup()
	{
		return retainPopup_();
	}
	
	private boolean retainPopup_()
	{
		try
		{
			for(int i=0;i<list.size();i++)
			{
				Map notif = (Map) list.get(i);
				if(isRetainingNotif(notif)) return true;
			}
		}
		catch(Exception e)
		{Outside.err(this,"retainPopup()",e);}
		return false;
	}
	
	private boolean isRetainingNotif(Map notif) throws Exception
	{
		if(!notif.containsKey("progress")) return false;
		F progress = (F) notif.get("progress");
		return progress.f(null);
	}

}