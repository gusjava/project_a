package a.entity.gus06.sys.expression1.apply.op._fileduration_ms;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}


	private Service buildFile;
	private Service findDuration;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		findDuration = Outside.service(this,"gus06.file.video.generic.duration");
	}



	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return fileDuration(file((String) value, opMap));
		if(value instanceof File) return fileDuration((File) value);
		if(value instanceof File[]) return fileDuration((File[]) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private Long fileDuration(File file) throws Exception
	{
		if(!file.exists()) return null;
		return Long.valueOf(totalDuration(file));
	}
	
	private Long fileDuration(File[] files) throws Exception
	{
		return Long.valueOf(totalDuration(files));
	}
	
	
	
	
	
	private long totalDuration(File path) throws Exception
	{
		if(!path.exists()) return 0;
		if(path.isFile()) return (Long) findDuration.t(path);
		
		long duration = 0;
		File[] ff = path.listFiles();
		for(int i=0;i<ff.length;i++)
			duration += totalDuration(ff[i]);
		return duration;
	}
	
	private long totalDuration(File[] paths) throws Exception
	{
		long duration = 0;
		for(File path:paths)
			duration += totalDuration(path);
		return duration;
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}