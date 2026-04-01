package a.entity.gus06.sys.webserver1.web3.processor2.output.file;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160219";}

	public static final String KEY_OUTPUT = "output";
	public static final String KEY_OUTPUT_HEADER = "output_header";


	private Service getExtension;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
		readFile = Outside.service(this,"gus06.file.read.raw");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		File output = (File) get(main,KEY_OUTPUT);
		if(!output.isFile()) return null;
		
		Map h1 = (Map) get(main,KEY_OUTPUT_HEADER);
		
		String type = (String) getExtension.t(output);
		byte[] data = (byte[]) readFile.t(output);
		Map h = buildHeader(type,output,h1);
		
		if(!h.isEmpty())
			return new Object[]{type,data,h};
		return new Object[]{type,data};
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	private Map buildHeader(String type, File file, Map h1) throws Exception
	{
		Map h = new HashMap();
	
		if(isTransfer(type))
		{
			h.put("Content-Description","File Transfer");
			h.put("Content-Disposition","attachment; filename="+fileName(file));
			h.put("Content-Transfer-Encoding","binary");
			h.put("Content-Length",""+file.length());
			h.put("Expires","0");
			h.put("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			h.put("Pragma","public");
		}
		if(h1!=null) h.putAll(h1);
		return h;
	}
	
	
	private boolean isTransfer(String type)
	{return type.equals("jar") || type.equals("exe") || type.equals("zip");}
	
	private String fileName(File file) throws Exception
	{return encode(file.getName());}
	
	private String encode(String s) throws Exception
	{return URLEncoder.encode(s,"UTF-8");}
}
