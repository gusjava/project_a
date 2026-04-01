package a.entity.gus06.string.html.table.todata2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190713";}


	private Service findData;
	private Service toText;


	public EntityImpl() throws Exception
	{
		findData = Outside.service(this,"gus06.string.html.table.todata1");
		toText = Outside.service(this,"gus06.string.html.tag.remove");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List data = (List) findData.t(obj);
		List data1 = new ArrayList();
		
		for(int i=0;i<data.size();i++)
		{
			List row = (List) data.get(i);
			List row1 = new ArrayList();
			
			data1.add(row1);
			
			for(int j=0;j<row.size();j++)
			{
				String cell = (String) row.get(j);
				row1.add(toText.t(cell));
			}
		}
		return data1;
	}
}
