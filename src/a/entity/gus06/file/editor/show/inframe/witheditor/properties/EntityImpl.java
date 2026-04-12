package a.entity.gus06.file.editor.show.inframe.witheditor.properties;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191031";}


	private Service show;
	private Service newEditor;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.file.editor.show.inframe.witheditor");
		newEditor = Outside.service(this,"factory#gus06.file.editor.ext.properties");
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Object editor = newEditor.g();
		show.p(new Object[]{file,editor});
	}
}
