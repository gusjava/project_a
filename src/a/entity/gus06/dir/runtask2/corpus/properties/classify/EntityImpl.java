package a.entity.gus06.dir.runtask2.corpus.properties.classify;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}
	
	public static final String KEY_CLASSIFY_FIELD = "classify.field";


	private Service listing;
	private Service classifyOp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		classifyOp = Outside.service(this,"gus06.file.properties.perform.classify.forfield");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		File dir = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		String field = get(map,KEY_CLASSIFY_FIELD);
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			classifyOp.p(new Object[]{f,dir,field});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
