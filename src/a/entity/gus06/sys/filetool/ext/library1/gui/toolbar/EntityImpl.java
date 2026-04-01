package a.entity.gus06.sys.filetool.ext.library1.gui.toolbar;

import a.framework.*;
import javax.swing.Action;
import javax.swing.JToolBar;
import java.util.List;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20190808";}

	public static final String ACTIONID_COPY_SELECTED = "ACTION_copySelected#Copy selected";
	public static final String ACTIONID_COPY_ALL = "ACTION_copyAll#Copy all";
	

	private Service toolbarBuilder;
	private Service buildAction;
	
	private Service buildAdd;
	private Service buildDelete;
	private Service buildClear;
	private Service buildEdit;
	private Service buildUp;
	private Service buildDown;
	
	private Action actionAdd;
	private Action actionDelete;
	private Action actionClear;
	private Action actionEdit;
	private Action actionUp;
	private Action actionDown;
	private Action actionCopy;
	private Action actionCopyAll;
	
	private JToolBar bar;
	
	

	public EntityImpl() throws Exception
	{
		toolbarBuilder = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		
		buildAdd = Outside.service(this,"gus06.lib.actions1.crud.add");
		buildDelete = Outside.service(this,"gus06.lib.actions1.crud.delete");
		buildClear = Outside.service(this,"gus06.lib.actions1.crud.empty");
		buildEdit = Outside.service(this,"gus06.lib.actions1.crud.edit");
		buildUp = Outside.service(this,"gus06.lib.actions1.move.up");
		buildDown = Outside.service(this,"gus06.lib.actions1.move.down");
		
		bar = (JToolBar) toolbarBuilder.i();
		
		actionAdd = (Action) buildAdd.t((E) this::add);
		actionDelete = (Action) buildDelete.t((E) this::delete);
		actionClear = (Action) buildClear.t((E) this::clear);
		actionEdit = (Action) buildEdit.t((E) this::edit);
		actionUp = (Action) buildUp.t((E) this::up);
		actionDown = (Action) buildDown.t((E) this::down);
		
		actionCopy = (Action) buildAction.t(new Object[]{ACTIONID_COPY_SELECTED, (E) this::copy});
		actionCopyAll = (Action) buildAction.t(new Object[]{ACTIONID_COPY_ALL, (E) this::copyAll});
		
		bar.add(actionAdd);
		bar.add(actionEdit);
		bar.add(actionDelete);
		bar.add(actionClear);
		bar.addSeparator();
		bar.add(actionUp);
		bar.add(actionDown);
		bar.addSeparator();
		bar.add(actionCopy);
		bar.add(actionCopyAll);
	}
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		List selection = (List) obj;
		boolean hasSelection = selection!=null && selection.size()>0;
		
		actionDelete.setEnabled(hasSelection);
		actionEdit.setEnabled(hasSelection);
		actionUp.setEnabled(hasSelection);
		actionDown.setEnabled(hasSelection);
		actionCopy.setEnabled(hasSelection);
	}
	
	
	
	private void add()
	{send(this,"add()");}

	private void edit()
	{send(this,"edit()");}
	
	private void delete()
	{send(this,"delete()");}
	
	private void clear()
	{send(this,"clear()");}
	
	private void up()
	{send(this,"up()");}
	
	private void down()
	{send(this,"down()");}
	
	private void copy()
	{send(this,"copy()");}
	
	private void copyAll()
	{send(this,"copyAll()");}
}
