package a.entity.gus06.sys.vault2.read.file;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231026";}

	public static final String KEY_CRYPTED = "CRYPTED";


	private Service readProp;
	private Service writeProp;
	private Service decryptMap;
	private Service encryptMap;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		decryptMap = Outside.service(this,"gus06.sys.vault2.decrypt.map");
		encryptMap = Outside.service(this,"gus06.sys.vault2.encrypt.map");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Map map = (Map) readProp.t(file);
		if(map.containsKey(KEY_CRYPTED)) return decryptMap.t(map);
		
		Map mapEnc = (Map) encryptMap.t(map);
		if(mapEnc!=null) writeProp.p(new Object[]{file, mapEnc});
		return map;
	}
}