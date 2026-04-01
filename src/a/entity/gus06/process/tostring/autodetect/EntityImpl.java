package a.entity.gus06.process.tostring.autodetect;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250510";}


	private Service isToString;


	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Process proc = (Process) obj;
		InputStream is = proc.getInputStream();
		return isToString.t(is);
	}
}