package a.entity.gus06.file.editor.ext.db;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250719";}


	private Service mimeSqlite;
	
	private Service editorUnsupported;
	private Service editorSqlite;
	private Service editorH2;
	private Service editorH2Trace;
	private Service shiftPanel;
	
	private File file;
	private Object currentEditor;
	

	public EntityImpl() throws Exception
	{
		mimeSqlite = Outside.service(this,"gus06.file.filter.mime.isoftype.application.sqlite");
		
		editorUnsupported = Outside.service(this,"*gus06.file.editor.ext.db.unsupported");
		editorSqlite = Outside.service(this,"*gus06.file.editor.ext.sqlite");
		editorH2 = Outside.service(this,"*gus06.file.editor.ext.db.h2");
		editorH2Trace = Outside.service(this,"*gus06.file.editor.ext.db.h2trace");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception
	{
		if(currentEditor!=null) ((P)currentEditor).p(null);
		currentEditor = null;
		shiftPanel.p(null);
	}
	
	
	private void updateGui() throws Exception
	{
		resetGui();
		currentEditor = findEditor();
		((P) currentEditor).p(file);
		shiftPanel.p(currentEditor);
	}
	
	private Object findEditor() throws Exception
	{
		String name = file.getName().toLowerCase();
		if(name.endsWith(".mv.db")) return editorH2;
		if(name.endsWith(".trace.db")) return editorH2Trace;
		
		if(mimeSqlite.f(file)) return editorSqlite;
		return editorUnsupported;
	}
}