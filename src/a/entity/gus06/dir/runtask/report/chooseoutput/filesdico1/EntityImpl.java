package a.entity.gus06.dir.runtask.report.chooseoutput.filesdico1;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150710";}
	
	public static final String KEY_FILEPATH = "file.path";


	private Service runtask2;
	private Service chooseFile;
	
	public EntityImpl() throws Exception
	{
		runtask2 = Outside.service(this,"gus06.dir.runtask2.report.filesdico1");
		chooseFile = Outside.service(this,"gus06.file.choose.save.file.ext.txt.en");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) chooseFile.g();
		if(f==null) return;
		
		Map map = new HashMap();
		map.put(KEY_FILEPATH,f.getAbsolutePath());
		
		runtask2.p(new Object[]{map,o[0],o[1],o[2]});
	}
}
