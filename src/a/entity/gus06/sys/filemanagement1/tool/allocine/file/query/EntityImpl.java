package a.entity.gus06.sys.filemanagement1.tool.allocine.file.query;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class EntityImpl implements Entity, ActionListener, F {

	public String creationDate() {return "20200920";}


	private Service show;
	private Service dialog;
	private Service gui;
	private Service update;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		gui = Outside.service(this,"*gus06.sys.allocinesearch.gui.maingui");
		update = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.file.update");
		
		gui.addActionListener(this);
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		Map prop = (Map) o[2];
		
		String name0 = (String) prop.get("name0");
		gui.p(name0);
		
		((JButton) dialog.r("button_ok")).setEnabled(false);
		
		boolean ok = dialog.f(gui.i());
		
		if(ok)
		{
			Object movie = gui.g();
			update.p(new Object[]{engine,selected,prop,movie});
			return true;
		}
		
		return false;
	}


	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	private void selected()
	{
		try
		{
			((JButton) dialog.r("button_ok")).setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}

}
