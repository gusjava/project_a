package a.entity.gus06.dir.hdd.name_serial;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191126";}


	private Service findName;
	private Service findSerial;

	public EntityImpl() throws Exception
	{
		findName = Outside.service(this,"gus06.dir.hdd.drivername");
		findSerial = Outside.service(this,"gus06.dir.hdd.vol.serialnumber");
	}

	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null) return "";
		
		String name = (String) findName.t(f);
		String serial = (String) findSerial.t(f);
		return name+"|"+serial;
	}
}
