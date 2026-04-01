package a.entity.gus06.string.extract.html.data3;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190702";}

	private Service extract;
	private Service buildTagInfo;


	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.string.extract.html.data1");
		buildTagInfo = Outside.service(this,"gus06.string.html.element.infomap");
	}


	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) extract.t(s);
		
		List output = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			String element = (String) list.get(i);
			if(!element.trim().equals(""))
			{
				Map tag = (Map) buildTagInfo.t(element);
				output.add(tag);
			}
		}
		return output;
	}
}
