package a.entity.gus06.dir.runtask.report.directoutput.filesdico2;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150827";}
	
	public static final String KEY_FILEPATH = "file.path";


	private Service runtask2;
	
	public EntityImpl() throws Exception
	{
		runtask2 = Outside.service(this,"gus06.dir.runtask2.report.filesdico2");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File f = new File(dir.getAbsolutePath()+"_report.txt");
		
		Map map = new HashMap();
		map.put(KEY_FILEPATH,f.getAbsolutePath());
		
		runtask2.p(new Object[]{map,dir,progress,interrupt});
	}
}