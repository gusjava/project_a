package a.entity.gus06.sys.filetool.ext.library1.tool.add;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230216";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service genAlphanum;
	private Service fileDispay;
	private Service fileProvider;

	public EntityImpl() throws Exception
	{
		genAlphanum = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		fileDispay = Outside.service(this,"gus06.file.getdisplay");
		fileProvider = Outside.service(this,"m102.r.fileprovider");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String[] data = findData(o[1]);
		if(data.length!=2) throw new Exception("Wrong data number (2): "+data.length);
		
		String path = data[0];
		String display = data[1];
		
		String struct = get0(map,STRUCT);
		if(struct==null) struct = "";
		
		String key = (String) genAlphanum.g();
		String[] nn = struct.split(";");
		while(indexOf(nn,key)!=-1) key = (String) genAlphanum.g();
		
		map.put(CONTENT+"."+key,path);
		map.put(DISPLAY+"."+key,display);
		map.put(STRUCT,struct.equals("") ? key : (key+";"+struct));
	}
	
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private int indexOf(String[] nn, String s)
	{
		if(nn==null || s==null) return -1;
		for(int i=0;i<nn.length;i++) if(nn[i].equals(s)) return i;
		return -1;
	}
	
	
	private String[] findData(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof File) return findDataFromFile((File) obj);
		if(obj instanceof Map) return findDataFromMap((Map) obj);
		if(obj instanceof String) return findDataFromString((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] findDataFromFile(File file) throws Exception
	{
		String path = file.getAbsolutePath();
		String display = (String) fileDispay.t(file);
		return new String[]{path, display};
	}
	
	private String[] findDataFromMap(Map map) throws Exception
	{
		String path = "";
		String display = "";
		
		if(map.containsKey("file"))
		{
			File file = (File) map.get("file");
			path = file.getAbsolutePath();
			display = (String) fileDispay.t(file);
		}
		else if(map.containsKey("path"))
		{
			path = (String) map.get("path");
			display = (String) fileDispay.t(file(path));
		}
		
		if(map.containsKey("display"))
		display = (String) map.get("display");
		
		return new String[]{path, display};
	}
	
	private String[] findDataFromString(String path) throws Exception
	{
		String display = (String) fileDispay.t(new File(path));
		return new String[]{path, display};
	}
	
	private File file(String path) throws Exception
	{return (File) fileProvider.r(path);}
}