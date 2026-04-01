package a.entity.gus06.string.html.table.todata1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190519";}


	private Service findTable;
	private Service inside;
	private Service findTbody;
	private Service findTr;
	private Service findTd;


	public EntityImpl() throws Exception
	{
		findTable = Outside.service(this,"gus06.string.extract.html.block.type.table.f");
		inside = Outside.service(this,"gus06.string.transform.html.block.inside");
		findTbody = Outside.service(this,"gus06.string.extract.html.block.type.tbody.f");
		findTr = Outside.service(this,"gus06.string.extract.html.block.type.tr.a");
		findTd = Outside.service(this,"gus06.string.extract.html.block.type.td.a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List rows = new ArrayList();
		
		String table = (String) findTable.t(s);
		String tbody = (String) findTbody.t(table);
		if(tbody!=null) table = tbody;
		
		table = table.replace("<th","<td").replace("</th>","</td>");
		List listTr = (List) findTr.t(table);
		
		for(int i=0;i<listTr.size();i++)
		{
			String tr = (String) listTr.get(i);
			List listTd = (List) findTd.t(tr);
			
			List row = new ArrayList();
			rows.add(row);
			
			for(int j=0;j<listTd.size();j++)
			{
				String td = (String) listTd.get(j);
				String tdContent = (String) inside.t(td);
				row.add(tdContent);
			}
		}
		return rows;
	}
}
