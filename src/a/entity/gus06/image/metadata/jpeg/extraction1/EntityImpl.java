package a.entity.gus06.image.metadata.jpeg.extraction1;

import java.io.File;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}


	private Service readSegments;
	private Service exifReading;
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		readSegments = Outside.service(this,"gus06.image.metadata.jpeg.extraction1.readsegments");
		exifReading = Outside.service(this,"gus06.image.metadata.jpeg.extraction1.exifreading.essai3");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		map = (Map) readSegments.t(file);
		
		// ANALYZE "EXIF" SEGMENT
		byte[] data = getSegment(SEGMENTS.SEGMENT_APP1,0);
		if(data==null) return null;
		Map exifMap = (Map) exifReading.t(data);
		
		return exifMap;
	}
	
	
	private byte[] getSegment(byte marker, int occ)
	{
		Byte key = Byte.valueOf(marker);
		if(!map.containsKey(key)) return null;
		List list = (List) map.get(key);
		return (byte[]) list.get(occ);
	}
}
