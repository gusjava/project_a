package a.entity.gus06.sys.filemanagement1.perform.root.edit;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191127";}


	private Service perform;
	private Service showErr;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.filemanagement1.perform.root.edit.dialog");
		showErr = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String name = (String) o[1];
		
		File file = new File(dir,name+".properties");
		if(!file.isFile())
		{
			showErr.p("Unknown Name: "+name);
			return false;
		}
		return perform.f(file);
	}
}
