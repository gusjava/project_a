package a.entity.gus06.sys.mailclient1.gui.tab1.control;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JToolBar;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, I, P, R {

	public String creationDate() {return "20240314";}
	
	
	public static final String ACTIONID_INFOS = "ACTION_details#Show infos";


	private Service actionBuilder;
	private Service toolbar;
	private Service infos;

	private JToolBar bar;
	
	private Object holder;
	
	private E execute_infos;
	
	private Action action_infos;
	
	
	
	public EntityImpl() throws Exception
	{
		infos = Outside.service(this,"gus06.sys.mailclient1.gui.tab1.control.infos");
		
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		execute_infos = new E(){public void e() throws Exception {infos();}};
		action_infos = (Action) actionBuilder.t(new Object[]{ACTIONID_INFOS, execute_infos});
		
		bar = (JToolBar) toolbar.i();
		bar.add(action_infos);
		updateGui();
	}
	
	
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = obj;
		updateGui();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("execute_infos")) return execute_infos;
		
		if(key.equals("keys")) return new String[]{
			"execute_infos",
		};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void updateGui()
	{
		try
		{
			action_infos.setEnabled(holder!=null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	private void infos()
	{
		try
		{
			if(holder==null) return;
			infos.p(holder);
		}
		catch(Exception e)
		{Outside.err(this,"infos()",e);}
	}
}