package a.entity.gus06.app.path.build.fromprop;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150312";}


	public static final Pattern pattern_tag = Pattern.compile("<([^>]+)>");
	public static final Pattern pattern_move = Pattern.compile("\\[(-?[0-9]+)\\]");
	
	public static final String GUSKLING_FILENAME = "guslink.txt";
	public static final int LOOKUP_LIMIT = 200;
	public static final String ROOT = "root";
	public static final String PATH_ROOTDIR = "path.rootdir";
	
	
	
	private Map predefined;

	public EntityImpl() throws Exception
	{predefined = (Map) Outside.resource(this,"path0");}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Map prop = new HashMap((Map) obj);
		Map paths = new HashMap(predefined);
		
		if(prop.containsKey(ROOT))
		{
			File rootDir = Tool_File.newFileOS((String) prop.get(ROOT));
			paths.put(PATH_ROOTDIR,rootDir);
		}
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("path."))
			{
				File file = searchFile(prop,paths,key,0);
				if(file!=null) paths.put(key,file);
			}
		}
		return paths;
	}
	
	
	
	
	
	
	
	
	private File searchFile(Map prop, Map paths, String info, int c) throws Exception
	{
		if(c>LOOKUP_LIMIT)
			throw new Exception("Infinite loop detected: stopping at value "+info);
		
		if(paths.containsKey(info))
			return (File) paths.get(info);
		
		if(info.startsWith("path."))
		{
			if(!prop.containsKey(info)) return null;
			String newInfo = (String) prop.get(info);
			if(newInfo.equals("null")) return null;
			
			File f = searchFile(prop,paths,newInfo,c+1);
			if(f==null) return null;
			paths.put(info,f);
			return f;
		}
		
		if(info.startsWith("["))
		{
			Matcher m = pattern_move.matcher(info);
			if(!m.find()) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			if(m.start()>0) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			
			int move = Integer.parseInt(m.group(1));
			String tail = info.substring(m.end());
			
			File f_tail = searchFile(prop,paths,tail,c+1);
			if(f_tail==null) return null;
			File file = moveFile(f_tail,move);
			return redirect(prop,paths,file,c);
		}

		if(info.startsWith("<"))
		{
			Matcher m = pattern_tag.matcher(info);
			if(!m.find()) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			if(m.start()>0) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			
			String tag = m.group(1);
			String tail = info.substring(m.end());
			
			File f_tag = searchFile(prop,paths,tag,c+1);
			if(f_tag==null) return null;
			File file = Tool_File.newFileOS(f_tag,tail);
			return redirect(prop,paths,file,c);
		}
		
		File file = Tool_File.newFileOS(info);
		return redirect(prop,paths,file,c);
	}
	
	
	
	
	
	private File moveFile(File file, int move)
	{
		if(!file.isAbsolute())
			file = file.getAbsoluteFile();
		
		int delta = move<0?move*-1:deep(file)-move;
		
		for(int i=0;i<delta;i++)
			if(file.getParentFile()!=null)
		file = file.getParentFile();
		
		return file;
	}
	
	
	
	private int deep(File file)
	{
		int d = 0;
		while(file.getParentFile()!=null)
		{file = file.getParentFile();d++;}
		return d;
	}
	
	
	
	private File redirect(Map prop, Map paths, File f, int c) throws Exception
	{
		if(f==null || !f.exists() || !f.isDirectory()) return f;
		
		File linkFile = new File(f,GUSKLING_FILENAME);
		if(!linkFile.exists() || !linkFile.isFile()) return f;
		
		String link = Tool_File.text(linkFile);
		return searchFile(prop,paths,link,c+1);
	}
}