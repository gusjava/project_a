package a.entity.gus06.sys.filemanagement1.analyze.stats.allocine.fields;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201021";}


	private Service fieldsCount;
	private Service mapToString;

	public EntityImpl() throws Exception
	{
		fieldsCount = Outside.service(this,"gus06.dir.properties.count.fields");
		mapToString = Outside.service(this,"gus06.tostring.map.tn");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		File dir = (File) ((R)engine).r("dirAllocine");
		File propDir = new File(dir,"code_prop");
		
		Map resultMap = (Map) fieldsCount.t(propDir);
		String summary = (String) mapToString.t(resultMap);
		p.println(summary);
	}
}