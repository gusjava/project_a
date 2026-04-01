package a.entity.gus06.jdbc.gui.user1.list.control;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, ActionListener, V, I, P {

	public String creationDate() {return "20150625";}
	
	
	public static final String ACTIONID_ADD = "USER_add#Add user";
	public static final String ACTIONID_REMOVE = "USER_remove#Remove user";
	public static final String ACTIONID_RENAME = "USER_rename#Rename user";
	public static final String ACTIONID_EDIT = "USER_edit#Edit user";


	private Service addUser;
	private Service removeUser;
	private Service renameUser;
	
	private Service selectorSup;
	private Service actionBuilder;
	private Service toolbar;



	private JToolBar bar;
	
	private G holder;
	private G selector;
	
	
	private E execute_add;
	private E execute_remove;
	private E execute_rename;
	
	private Action action_add;
	private Action action_remove;
	private Action action_rename;
	
	
	
	public EntityImpl() throws Exception
	{
		addUser = Outside.service(this,"gus06.jdbc.mysql.perform.user.create.ask");
		removeUser = Outside.service(this,"gus06.jdbc.mysql.perform.user.drop.ask");
		renameUser = Outside.service(this,"gus06.jdbc.mysql.perform.user.rename.ask");
		
		selectorSup = Outside.service(this,"*gus06.support.holder");
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		selectorSup.addActionListener(this);
		
		execute_add = new E(){public void e() throws Exception {add();}};
		execute_remove = new E(){public void e() throws Exception {remove();}};
		execute_rename = new E(){public void e() throws Exception {rename();}};
		
		action_add = (Action) actionBuilder.t(new Object[]{ACTIONID_ADD,execute_add});
		action_remove = (Action) actionBuilder.t(new Object[]{ACTIONID_REMOVE,execute_remove});
		action_rename = (Action) actionBuilder.t(new Object[]{ACTIONID_RENAME,execute_rename});
		
		
		bar = (JToolBar) toolbar.i();
		
		bar.add(action_add);
		bar.add(action_remove);
		bar.add(action_rename);
	}
	
	
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = (G) obj;
		updateGui();
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selector"))
		{
			selector = (G) obj;
			selectorSup.p(selector);
			updateGui();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			if(selector==null) return;
			String selection = (String) selector.g();
			
			boolean selected = selection!=null && !selection.equals("");
			boolean initialized = holder!=null;
			
			action_add.setEnabled(initialized);
			action_rename.setEnabled(initialized && selected);
			action_remove.setEnabled(initialized && selected);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	
	
	private void add()
	{
		try
		{
			if(holder==null) return;
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = addUser.f(cx);
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}

	
	
	
	
	private void remove()
	{
		try
		{
			String selection = (String) selector.g();
			if(selection==null) return;
			
			if(holder==null) return;
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = removeUser.f(new Object[]{cx,selection});
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}
	
	
	
	
	private void rename()
	{
		try
		{
			String selection = (String) selector.g();
			if(selection==null) return;
			
			if(holder==null) return;
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = renameUser.f(new Object[]{cx,selection});
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
}
