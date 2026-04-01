package a.entity.gus06.data.perform.splitfile;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250913";}
	
	public static final String KEY_FILE = "file";
	public static final String KEY_TYPE = "type";
	public static final String KEY_PARAMS = "params";


	private Service isPdf;
	private Service isWav;
	
	private Service performPdf;
	private Service performWav;
	
	public EntityImpl() throws Exception
	{
		isPdf = Outside.service(this,"gus06.file.filter.ext.istype.pdf");
		isWav = Outside.service(this,"gus06.file.filter.ext.istype.audio.wav");
		
		performPdf = Outside.service(this,"gus06.file.pdf.split.todir");
		performWav = Outside.service(this,"gus06.file.wav.split.todir");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof File) handleFile((File) o[0], (File) o[1]);
		else if(o[0] instanceof Map) handleMap((Map) o[0], (File) o[1]);
		else throw new Exception("Unsupported data type: "+o[0].getClass().getName());
	}
	
	
	
	private void handleFile(File inputFile, File outputDir) throws Exception
	{
		if(isPdf.f(inputFile)) 
		{performPdf(inputFile, outputDir);return;}
		
		if(isWav.f(inputFile)) 
		{performWav(inputFile, outputDir, null);return;}
		
		throw new Exception("Unsupported file type: "+inputFile);
	}
	
	
	private void handleMap(Map map, File outputDir) throws Exception
	{
		File inputFile = (File) get(map, KEY_FILE);
		String type = (String) get(map, KEY_TYPE);
		Object params = get(map, KEY_PARAMS);
		
		if(type!=null)
		{
			if(type.equals("pdf"))
			{performPdf(inputFile, outputDir);return;}
			
			if(type.equals("wav"))
			{performWav(inputFile, outputDir, params);return;}
			
			throw new Exception("Unsupported type: "+type);
		}
		
		if(isPdf.f(inputFile)) 
		{performPdf(inputFile, outputDir);return;}
		
		if(isWav.f(inputFile)) 
		{performWav(inputFile, outputDir, params);return;}
		
		throw new Exception("Unsupported file type: "+inputFile);
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
	
	
	private void performPdf(File inputFile, File outputDir) throws Exception
	{performPdf.p(new Object[]{inputFile, outputDir});}
	
	private void performWav(File inputFile, File outputDir, Object params) throws Exception
	{performWav.p(new Object[]{inputFile, outputDir, params});}
}
