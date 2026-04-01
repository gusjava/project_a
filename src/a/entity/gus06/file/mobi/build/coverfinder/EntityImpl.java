package a.entity.gus06.file.mobi.build.coverfinder;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191009";}


	private Service readImage;

	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		return new Finder(data);
	}
	
	
	private class Finder implements G
	{
		private Map data;
		public 	Finder(Map data) {this.data = data;}
		
		public Object g() throws Exception
		{
			Map exthMap = (Map) data.get("exthMap");
			Map mobiHeader = (Map) data.get("mobiHeader");
			List mobiContents = (List) data.get("mobiContents");
			
			Integer offset = (Integer) exthMap.get("COVER_OFFSET");
			Integer index0 = (Integer) mobiHeader.get("firstImageIndex");
			
			int index = index0 + offset;
			byte[] data = (byte[]) mobiContents.get(index);
			
			return readImage.t(data);
		}
	}
}
