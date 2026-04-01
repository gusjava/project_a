package a.entity.gus06.jdbc.gui.cx1.db.table.list.control;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, ActionListener, I, P, V, R {

	public String creationDate() {return "20150622";}


	public static final String ACTIONID_REFRESH = "TABLE_refresh#Refresh";
	public static final String ACTIONID_ADD = "TABLE_add#Add table";
	public static final String ACTIONID_REMOVE = "TABLE_remove#Remove table";
	public static final String ACTIONID_RENAME = "TABLE_rename#Rename table";
	public static final String ACTIONID_DUPLICATE = "TABLE_duplicate#Duplicate table";
	public static final String ACTIONID_ANALYZE = "TABLE_eye#Analyze table";


	private Service addTable;
	private Service removeTable;
	private Service renameTable;
	private Service duplicateTable;
	private Service analyzeTable;
	
	private Service selectorSup;
	private Service actionBuilder;
	private Service toolbar;



	private JToolBar bar;
	
	private Object[] data;
	private G selector;
	
	
	private E execute_refresh;
	private E execute_add;
	private E execute_remove;
	private E execute_rename;
	private E execute_duplicate;
	private E execute_analyze;
	
	private Action action_refresh;
	private Action action_add;
	private Action action_remove;
	private Action action_rename;
	private Action action_duplicate;
	private Action action_analyze;
	
	
	
	public EntityImpl() throws Exception
	{
		addTable = Outside.service(this,"gus06.jdbc.mysql.perform.table.create.ask");
		removeTable = Outside.service(this,"gus06.jdbc.mysql.perform.table.drop.ask");
		renameTable = Outside.service(this,"gus06.jdbc.generic.perform.table.rename.ask");
		duplicateTable = Outside.service(this,"gus06.jdbc.generic.perform.table.duplicate.ask");
		analyzeTable = Outside.service(this,"gus06.jdbc.gui.cx1.db.table.list.control.analyze");
		
		selectorSup = Outside.service(this,"*gus06.support.holder");
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		selectorSup.addActionListener(this);
		
		execute_refresh = new E(){public void e() throws Exception {refresh();}};
		execute_add = new E(){public void e() throws Exception {add();}};
		execute_remove = new E(){public void e() throws Exception {remove();}};
		execute_rename = new E(){public void e() throws Exception {rename();}};
		execute_duplicate = new E(){public void e() throws Exception {duplicate();}};
		execute_analyze = new E(){public void e() throws Exception {analyze();}};
		
		action_refresh = (Action) actionBuilder.t(new Object[]{ACTIONID_REFRESH, execute_refresh});
		action_add = (Action) actionBuilder.t(new Object[]{ACTIONID_ADD, execute_add});
		action_remove = (Action) actionBuilder.t(new Object[]{ACTIONID_REMOVE, execute_remove});
		action_rename = (Action) actionBuilder.t(new Object[]{ACTIONID_RENAME, execute_rename});
		action_duplicate = (Action) actionBuilder.t(new Object[]{ACTIONID_DUPLICATE, execute_duplicate});
		action_analyze = (Action) actionBuilder.t(new Object[]{ACTIONID_ANALYZE, execute_analyze});
		
		
		bar = (JToolBar) toolbar.i();
		
		bar.add(action_refresh);
		bar.add(action_add);
		bar.addSeparator();
		bar.add(action_remove);
		bar.add(action_rename);
		bar.add(action_duplicate);
		bar.add(action_analyze);
		
		updateGui();
	}
	
	
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (Object[]) obj;
		updateGui();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("execute_refresh")) return execute_refresh;
		if(key.equals("execute_add")) return execute_add;
		if(key.equals("execute_rename")) return execute_rename;
		if(key.equals("execute_remove")) return execute_remove;
		if(key.equals("execute_duplicate")) return execute_duplicate;
		if(key.equals("execute_analyze")) return execute_analyze;
		
		if(key.equals("keys")) return new String[]{
			"execute_refresh",
			"execute_add",
			"execute_rename",
			"execute_remove",
			"execute_duplicate",
			"execute_analyze"
		};
		throw new Exception("Unknown key: "+key);
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
			boolean initialized = data!=null;
			
			action_add.setEnabled(initialized);
			action_rename.setEnabled(initialized && selected);
			action_remove.setEnabled(initialized && selected);
			action_duplicate.setEnabled(initialized && selected);
			action_analyze.setEnabled(initialized && selected);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	
	
	private void refresh()
	{
		try
		{
			if(data==null) return;
			G holder = (G) data[0];
			((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void add()
	{
		try
		{
			if(data==null) return;
			
			G holder = (G) data[0];
			String dbName = (String) data[1];
			
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = addTable.f(new Object[]{cx,dbName});
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
			
			if(data==null) return;
			
			G holder = (G) data[0];
			String dbName = (String) data[1];
			
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = removeTable.f(new Object[]{cx,dbName,selection});
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
			
			if(data==null) return;
			
			G holder = (G) data[0];
			String dbName = (String) data[1];
			
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = renameTable.f(new Object[]{cx,dbName,selection});
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
	
	
	
	private void duplicate()
	{
		try
		{
			String selection = (String) selector.g();
			if(selection==null) return;
			
			if(data==null) return;
			
			G holder = (G) data[0];
			String dbName = (String) data[1];
			
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = duplicateTable.f(new Object[]{cx,dbName,selection});
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}
	
	
	
	private void analyze()
	{
		try
		{
			String selection = (String) selector.g();
			if(selection==null) return;
			
			if(data==null) return;
			
			G holder = (G) data[0];
			String dbName = (String) data[1];
			
			Connection cx = (Connection) holder.g();
			if(cx==null) return;
			
			boolean done = analyzeTable.f(new Object[]{cx,dbName,selection});
			if(done) ((P) holder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"analyze()",e);}
	}
}