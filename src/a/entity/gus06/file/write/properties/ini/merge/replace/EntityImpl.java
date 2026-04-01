package a.entity.gus06.file.write.properties.ini.merge.replace;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160325";}


	private Service toProp;
	private Service readIni;
	private Service writeIni;

	public EntityImpl() throws Exception
	{
		toProp = Outside.service(this,"gus06.find.properties");
		readIni = Outside.service(this,"gus06.file.read.properties.from.ini");
		writeIni = Outside.service(this,"gus06.file.write.properties.ini");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Map prop = toProp(o[1]);
		
		Map prop1 = new HashMap();
		prop1.putAll(prop);
		
		Map prop0 = readIni(file);
		prop1.putAll(prop0);
		
		writeIni.p(new Object[]{file,prop1});
	}
	
	
	
	private Map toProp(Object obj) throws Exception
	{return (Map) toProp.t(obj);}
	
	private Map readIni(File file) throws Exception
	{return (Map) readIni.t(file);}
}
