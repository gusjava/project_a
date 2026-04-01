package a.entity.gus06.sys.filemanagement1.perform.root.delete;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191110";}


	private Service perform;
	private Service showErr;
	private Service confirm;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.op.delete");
		showErr = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String name = (String) o[1];
		
		File dir = (File) ((R) engine).r("dirRoots");
		File file = new File(dir,name+".properties");
		
		if(!file.isFile())
		{
			showErr.p("Unknown Name: "+name);
			return false;
		}
		
		boolean r = confirm.f("Are you sure to delete root: "+name);
		if(!r) return false;
		
		perform.p(file);
		return true;
	}
}
