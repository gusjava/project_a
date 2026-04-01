package a.entity.gus06.dir.runtask2.report.filesdico2;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150825";}
	
	public static final String KEY_FILEPATH = "file.path";
	public static final String KEY_FILEENCODING = "file.encoding";
	
	public static final String DEFAULT_ENCODING = "UTF-8";



	private Service listing;
	private Service buildLine;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		buildLine = Outside.service(this,"gus06.sys.filesdico2.buildline");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		File dir = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		
		String path = get(map,KEY_FILEPATH);
		String encoding = get(map,KEY_FILEENCODING);
		
		if(path==null) throw new Exception("File path not defined");
		if(encoding==null) encoding = DEFAULT_ENCODING;
		
		
		PrintStream p = new PrintStream(new File(path),encoding);
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String line = (String) buildLine.t(f);
			if(line!=null) p.println(line);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
