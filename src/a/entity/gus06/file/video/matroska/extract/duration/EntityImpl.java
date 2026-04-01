package a.entity.gus06.file.video.matroska.extract.duration;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		FileDataSource source = new FileDataSource(file);
		MatroskaFile mskFile = new MatroskaFile(source);
		mskFile.readFile();
		
		long duration = (long) mskFile.getDuration();
		
		return Long.valueOf(duration);
	}
}