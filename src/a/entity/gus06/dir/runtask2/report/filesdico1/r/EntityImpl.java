package a.entity.gus06.dir.runtask2.report.filesdico1.r;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170109";}
	
	public static final String KEY_FILEPATH = "file.path";
	public static final String KEY_FILEPATH0 = "file.path0";
	public static final String KEY_FILEENCODING = "file.encoding";
	
	public static final String DEFAULT_ENCODING = "UTF-8";



	private Service listing;
	private Service buildLine;
	private Service previousData;
	
	private PrintStream out;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		buildLine = Outside.service(this,"gus06.sys.filesdico1.buildline");
		previousData = Outside.service(this,"gus06.dir.runtask2.report.filesdico1.r.previousdata");
		
		out = (PrintStream) Outside.resource(this,"sysout");
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
		String path0 = get(map,KEY_FILEPATH0);
		String encoding = get(map,KEY_FILEENCODING);
		
		if(path==null) throw new Exception("File path not defined");
		if(encoding==null) encoding = DEFAULT_ENCODING;
		
		Map previous = (Map) previousData.t(new Object[]{dir,path0});
		PrintStream p = new PrintStream(new File(path),encoding);
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			
			String line = findLine(f,previous);
			if(line!=null) p.println(line);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
	
	
	
	private String findLine(File f, Map previous) throws Exception
	{
		String p = f.getAbsolutePath();
		if(previous.containsKey(p))
			return (String) previous.get(p);
		
		out.println("scanning file: "+f);
		return (String) buildLine.t(f);
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
