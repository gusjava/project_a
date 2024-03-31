package a.entity.gus.y.entityeditor1.gui1.src.single;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240113";}

	public static final String DISPLAY_ADD = "FILE_java_add#Add file";

	
	private Service actionBuilder;
	private Service toolbarFactory;
	private Service javaEditor;
	private Service performAdd;
	
	private Action actionAdd;
	private Action actionSave;
	private Action actionReload;
	
	private JPanel panel;
	private JToolBar bar;
	
	private Object data;
	
	private String entityName;
	private File javaFile;

	public EntityImpl() throws Exception {
		actionBuilder = Outside.service(this, "gus.y.swing1.action.builder1");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		javaEditor = Outside.service(this, "*gus.y.entityeditor1.gui1.src.java");
		performAdd = Outside.service(this,"gus.y.entityeditor1.perform.file.add.ask");
		
		actionAdd = (Action) actionBuilder.t(new Object[] {DISPLAY_ADD, (E) this::addFile});
		actionSave = (Action) javaEditor.r("actionSave");
		actionReload = (Action) javaEditor.r("actionReload");

		bar = (JToolBar) toolbarFactory.i();
		bar.setOrientation(JToolBar.VERTICAL);
		
		bar.add(actionSave);
		bar.add(actionReload);
		bar.addSeparator();
		bar.add(actionAdd);

		panel = new JPanel(new BorderLayout());
		panel.add(bar, BorderLayout.WEST);
		panel.add((JComponent) javaEditor.i(), BorderLayout.CENTER);
	}

	public void p(Object obj) throws Exception {
		if (obj == null) {
			reset();
			return;
		}
		data = obj;
		entityName = (String) ((R) data).r("entityName");
		javaFile = ((File[]) ((R) data).r("javaFiles"))[0];

		javaEditor.p(new Object[] {data, javaFile});
		actionAdd.setEnabled(canModifyEntity());
	}

	public Object i() throws Exception {
		return panel;
	}

	private void reset() throws Exception {
		data = null;
		entityName = null;
		javaFile = null;
	}

	private void addFile() {
		try {
			if (canModifyEntity())
				performAdd.p(new Object[] { data, entityName, bar });
		} catch (Exception e) {
			Outside.err(this, "addFile()", e);
		}
	}

	/*
	 * PERMISSIONS
	 */

	private boolean canModifyEntity() throws Exception {
		return permission("canModifyEntity");
	}

	private boolean permission(String permission) throws Exception {
		return data != null && ((F) data).f(new Object[] { permission, entityName });
	}
}
