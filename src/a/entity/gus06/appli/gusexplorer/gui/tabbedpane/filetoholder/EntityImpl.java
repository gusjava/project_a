package a.entity.gus06.appli.gusexplorer.gui.tabbedpane.filetoholder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141211";}

	private Service editorFactory;
	
	public EntityImpl() throws Exception
	{
		editorFactory = Outside.service(this,"factory#gus06.file.editor.main");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object editor = editorFactory.g();
		((P) editor).p(obj);
		return editor;
	}
}
