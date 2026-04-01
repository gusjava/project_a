package a.entity.gus06.jdbc.gui.cx1.db.list.control;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JToolBar;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, V, I, P, R {

	public String creationDate() {return "20150622";}
	
	
	public static final String ACTIONID_REFRESH = "BASE_refresh#Refresh";
	public static final String ACTIONID_ADD = "BASE_add#Add database";
	public static final String ACTIONID_RENAME = "BASE_rename#Rename database";
	public static final String ACTIONID_REMOVE = "BASE_remove#Remove database";
	public static final String ACTIONID_EMPTY = "BASE_empty#Empty database";
	public static final String ACTIONID_BACKUP = "BASE_backup#Backup database";
	public static final String ACTIONID_RESTORE = "BASE_restore#Restore database";
	public static final String ACTIONID_DUPLICATE = "BASE_duplicate#Duplicate database";
	public static final String ACTIONID_DUPLICATE_TS = "BASE_duplicate#Duplicate database (timestamped)";
	public static final String ACTIONID_COPY = "BASE_copy#Copy database";
	public static final String ACTIONID_PASTE1 = "BASE_paste#Paste inside database";
	public static final String ACTIONID_PASTE2 = "BASE_add2#Paste databases";
	public static final String ACTIONID_ANALYZE = "BASE_eye#Analyze database";
	public static final String ACTIONID_SCRIPT = "BASE_gus#Apply script";


	private Service addDb;
	private Service removeDb;
	private Service renameDb;
	private Service emptyDb;
	
	private Service selectorSup;
	private Service actionBuilder;
	private Service toolbar;
	private Service backup;
	private Service restore;
	private Service duplicate;
	private Service duplicateTS;
	private Service copy;
	private Service paste1;
	private Service paste2;
	private Service analyze;
	private Service script;



	private JToolBar bar;
	
	private G cxHolder;
	private G selector;
	private Object dumpHolder;
	
	private E execute_refresh;
	private E execute_add;
	private E execute_rename;
	private E execute_remove;
	private E execute_empty;
	private E execute_backup;
	private E execute_restore;
	private E execute_duplicate;
	private E execute_duplicateTS;
	private E execute_copy;
	private E execute_paste1;
	private E execute_paste2;
	private E execute_analyze;
	private E execute_script;
	
	private Action action_refresh;
	private Action action_add;
	private Action action_rename;
	private Action action_remove;
	private Action action_empty;
	private Action action_backup;
	private Action action_restore;
	private Action action_duplicate;
	private Action action_duplicateTS;
	private Action action_copy;
	private Action action_paste1;
	private Action action_paste2;
	private Action action_analyze;
	private Action action_script;
	
	
	
	public EntityImpl() throws Exception
	{
		addDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create.ask");
		
		selectorSup = Outside.service(this,"*gus06.support.holder");
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		renameDb = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.rename");
		removeDb = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.remove");
		emptyDb = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.empty");
		backup = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.backup");
		restore = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.restore");
		duplicate = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.duplicate");
		duplicateTS = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.duplicate.timestamped");
		copy = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.copy");
		paste1 = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.paste1");
		paste2 = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.paste2");
		analyze = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.analyze");
		script = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.script");
		
		selectorSup.addActionListener(this);
		
		execute_refresh = new E(){public void e() throws Exception {refresh();}};
		execute_add = new E(){public void e() throws Exception {add();}};
		execute_rename = new E(){public void e() throws Exception {rename();}};
		execute_remove = new E(){public void e() throws Exception {remove();}};
		execute_empty = new E(){public void e() throws Exception {empty();}};
		execute_backup = new E(){public void e() throws Exception {backup();}};
		execute_restore = new E(){public void e() throws Exception {restore();}};
		execute_duplicate = new E(){public void e() throws Exception {duplicate();}};
		execute_duplicateTS = new E(){public void e() throws Exception {duplicateTS();}};
		execute_copy = new E(){public void e() throws Exception {copy();}};
		execute_paste1 = new E(){public void e() throws Exception {paste1();}};
		execute_paste2 = new E(){public void e() throws Exception {paste2();}};
		execute_analyze = new E(){public void e() throws Exception {analyze();}};
		execute_script = new E(){public void e() throws Exception {script();}};
		
		action_refresh = (Action) actionBuilder.t(new Object[]{ACTIONID_REFRESH,execute_refresh});
		action_add = (Action) actionBuilder.t(new Object[]{ACTIONID_ADD,execute_add});
		action_rename = (Action) actionBuilder.t(new Object[]{ACTIONID_RENAME,execute_rename});
		action_remove = (Action) actionBuilder.t(new Object[]{ACTIONID_REMOVE,execute_remove});
		action_empty = (Action) actionBuilder.t(new Object[]{ACTIONID_EMPTY,execute_empty});
		action_backup = (Action) actionBuilder.t(new Object[]{ACTIONID_BACKUP,execute_backup});
		action_restore = (Action) actionBuilder.t(new Object[]{ACTIONID_RESTORE,execute_restore});
		action_duplicate = (Action) actionBuilder.t(new Object[]{ACTIONID_DUPLICATE,execute_duplicate});
		action_duplicateTS = (Action) actionBuilder.t(new Object[]{ACTIONID_DUPLICATE_TS,execute_duplicateTS});
		action_copy = (Action) actionBuilder.t(new Object[]{ACTIONID_COPY,execute_copy});
		action_paste1 = (Action) actionBuilder.t(new Object[]{ACTIONID_PASTE1,execute_paste1});
		action_paste2 = (Action) actionBuilder.t(new Object[]{ACTIONID_PASTE2,execute_paste2});
		action_analyze = (Action) actionBuilder.t(new Object[]{ACTIONID_ANALYZE,execute_analyze});
		action_script = (Action) actionBuilder.t(new Object[]{ACTIONID_SCRIPT,execute_script});
		
		
		bar = (JToolBar) toolbar.i();
		
		bar.add(action_refresh);
		bar.addSeparator();
		bar.add(action_add);
		bar.add(action_paste2);
		bar.add(action_duplicate);
		bar.addSeparator();
		bar.add(action_rename);
		bar.add(action_remove);
		bar.add(action_empty);
		bar.add(action_analyze);
		bar.add(action_script);
		bar.addSeparator();
		bar.add(action_backup);
		bar.add(action_restore);
		
		updateGui();
	}
	
	
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		cxHolder = (G) obj;
		updateGui();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("execute_refresh")) return execute_refresh;
		if(key.equals("execute_add")) return execute_add;
		if(key.equals("execute_rename")) return execute_rename;
		if(key.equals("execute_remove")) return execute_remove;
		if(key.equals("execute_empty")) return execute_empty;
		if(key.equals("execute_backup")) return execute_backup;
		if(key.equals("execute_restore")) return execute_restore;
		if(key.equals("execute_duplicate")) return execute_duplicate;
		if(key.equals("execute_duplicateTS")) return execute_duplicateTS;
		if(key.equals("execute_copy")) return execute_copy;
		if(key.equals("execute_paste1")) return execute_paste1;
		if(key.equals("execute_paste2")) return execute_paste2;
		if(key.equals("execute_analyze")) return execute_analyze;
		if(key.equals("execute_script")) return execute_script;
		
		if(key.equals("keys")) return new String[]{
			"execute_refresh",
			"execute_add",
			"execute_rename",
			"execute_remove",
			"execute_empty",
			"execute_backup",
			"execute_restore",
			"execute_duplicate",
			"execute_duplicateTS",
			"execute_copy",
			"execute_paste1",
			"execute_paste2",
			"execute_analyze",
			"execute_script"
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
		if(key.equals("dumpHolder"))
		{
			dumpHolder = obj;
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
			List selection = selector!=null ? (List) selector.g() : null;
			
			boolean selected = selection!=null && !selection.isEmpty();
			boolean initialized = cxHolder!=null;
			boolean backupEnabled = backupEnabled();
			boolean restoreEnabled = restoreEnabled();
			
			action_refresh.setEnabled(initialized);
			action_add.setEnabled(initialized);
			action_paste2.setEnabled(initialized);
			
			action_rename.setEnabled(initialized && selected);
			action_remove.setEnabled(initialized && selected);
			action_empty.setEnabled(initialized && selected);
			action_analyze.setEnabled(initialized && selected);
			action_script.setEnabled(initialized && selected);
			
			action_copy.setEnabled(initialized && selected && backupEnabled);
			action_paste1.setEnabled(initialized && selected && backupEnabled);
			action_backup.setEnabled(initialized && selected && backupEnabled);
			action_restore.setEnabled(initialized && selected && restoreEnabled);
			action_duplicate.setEnabled(initialized && selected && backupEnabled && restoreEnabled);
			action_duplicateTS.setEnabled(initialized && selected && backupEnabled && restoreEnabled);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	private boolean backupEnabled() throws Exception
	{
		if(dumpHolder==null) return false;
		return ((F) dumpHolder).f("backupEnabled");
	}
	
	
	private boolean restoreEnabled() throws Exception
	{
		if(dumpHolder==null) return false;
		return ((F) dumpHolder).f("restoreEnabled");
	}
	
	
	
	private void refresh()
	{
		try
		{
			if(cxHolder==null) return;
			((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void add()
	{
		try
		{
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = addDb.f(cx);
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}
	
	
	
	private void rename()
	{
		try
		{
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = renameDb.f(new Object[]{cx, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}

	
	
	private void remove()
	{
		try
		{
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = removeDb.f(new Object[]{cx, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}

	
	
	private void empty()
	{
		try
		{
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = emptyDb.f(new Object[]{cx, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"empty()",e);}
	}
	
	
	
	private void backup()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			backup.p(new Object[]{dumpHolder, selection});
		}
		catch(Exception e)
		{Outside.err(this,"backup()",e);}
	}
	
	
	
	private void restore()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = restore.f(new Object[]{cx, dumpHolder, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"restore()",e);}
	}
	
	
	
	private void duplicate()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = duplicate.f(new Object[]{cx, dumpHolder, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}
	
	
	
	private void duplicateTS()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = duplicateTS.f(new Object[]{cx, dumpHolder, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"duplicateTS()",e);}
	}
	
	
	
	private void copy()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			copy.p(new Object[]{dumpHolder, selection});
		}
		catch(Exception e)
		{Outside.err(this,"copy()",e);}
	}
	
	
	private void paste1()
	{
		try
		{
			if(dumpHolder==null) return;
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = paste1.f(new Object[]{cx, dumpHolder, selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"paste1()",e);}
	}
	
	
	private void paste2()
	{
		try
		{
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = paste2.f(new Object[]{cx, dumpHolder});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"paste2()",e);}
	}
	
	
	
	private void analyze()
	{
		try
		{
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = analyze.f(new Object[]{cx,selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"analyze()",e);}
	}
	
	
	
	private void script()
	{
		try
		{
			List selection = (List) selector.g();
			if(selection==null || selection.isEmpty()) return;
			
			if(cxHolder==null) return;
			Connection cx = (Connection) cxHolder.g();
			if(cx==null) return;
			
			boolean done = script.f(new Object[]{cx,selection});
			if(done) ((P) cxHolder).p("update");
		}
		catch(Exception e)
		{Outside.err(this,"script()",e);}
	}
}