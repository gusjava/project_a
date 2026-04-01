package a.entity.gus06.data.perform.exec;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20151114";}

	private Service execCmd;
	private Service execFile;
	private Service execMap;
	
	public EntityImpl() throws Exception
	{
		execCmd = Outside.service(this,"gus06.runtime.exec.cmd");
		execFile = Outside.service(this,"gus06.runtime.exec.file");
		execMap = Outside.service(this,"gus06.runtime.exec.map");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return execCmd.t(obj);
		if(obj instanceof File) return execFile.t(obj);
		if(obj instanceof Map) return execMap.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
