package a.entity.gus06.file.video.matroska.extract.infomap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201004";}
	
	public static final String API = "matroska1";
	
	public static final String KEY_API = "api";
	public static final String KEY_DURATION = "duration";
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		FileDataSource source = new FileDataSource(file);
		MatroskaFile mskFile = new MatroskaFile(source);
		mskFile.readFile();
		
		long duration = (long) mskFile.getDuration();
		
		Map map = new HashMap();
		map.put(KEY_API,API);
		map.put(KEY_DURATION,""+duration);
		
		return map;
	}
}