package a.entity.gus06.file.mobi.extract.data.exthmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}


	private Service findName;
	private Service findFormatter;
	
	public EntityImpl() throws Exception
	{
		findName = Outside.service(this,"gus06.file.mobi.tool.exth.typetoname");
		findFormatter = Outside.service(this,"gus06.file.mobi.tool.exth.typetoformatter");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map mobiHeader = (Map) obj;
		Map exthHeader = (Map) mobiHeader.get("exthHeader");
		List records = (List) exthHeader.get("records");
		
		Map map = new HashMap();
		
		for(int i=0;i<records.size();i++)
		{
			Map record = (Map) records.get(i);
			
			Integer type = (Integer) record.get("type");
			byte[] data = (byte[]) record.get("data");
			
			String name = (String) findName.t(type);
			T formatter = (T) findFormatter.t(type);
			Object value = formatter.t(data);
			
			map.put(name,value);
		}
		return map;
	}
}
