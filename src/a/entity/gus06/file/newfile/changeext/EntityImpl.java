package a.entity.gus06.file.newfile.changeext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180307";}


	private Service getname;

	public EntityImpl() throws Exception
	{
		getname = Outside.service(this,"gus06.file.getname0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String extension = (String) o[1];
		
		String name = (String) getname.t(file);
		if(extension!=null && !extension.equals("")) name = name+"."+extension;
		
		return new File(file.getParentFile(),name);
	}
}
