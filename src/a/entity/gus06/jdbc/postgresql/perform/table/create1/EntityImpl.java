package a.entity.gus06.jdbc.postgresql.perform.table.create1;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260107";}

	private Service createTable;

	public EntityImpl() throws Exception
	{
		createTable = Outside.service(this,"gus06.jdbc.postgresql.perform.table.create");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3 && o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) o[2];
		Object options = o.length==4 ? o[3] : null;
		
		List fields = new ArrayList(map.keySet());
		Collections.sort(fields);
		int nb = fields.size();
		
		List pkList = new ArrayList();
		List unList = new ArrayList();
		
		String[] colName = new String[nb];
		String[] colDef = new String[nb];
		
		for(int i=0;i<nb;i++)
		{
			String key = (String) fields.get(i);
			String value = (String) map.get(key);
			
			colName[i] = key;
			colDef[i] = buildColDef(key,value,pkList,unList);
		}
		
		String[] primary = new String[pkList.size()];
		pkList.toArray(primary);
		
		String[] uniques = new String[unList.size()];
		unList.toArray(uniques);
		
		createTable.p(new Object[]{cx,path,colName,colDef,primary,uniques,options});
	}
	
	private String buildColDef(String key, String value, List pkList, List unList)
	{
		if(value.startsWith("~")) return value.substring(1);
			
		String[] n = value.split("[ \t]+");
		
		String type = n[0];
		String infos = n.length>1?n[1]:"";
		String dvalue = n.length>2?n[2]:null;
		
		StringBuffer b = new StringBuffer(type);
		if(infos.contains("0")) b.append(" NULL");
		else if(infos.contains("1")) b.append(" NOT NULL");
		
		if(isValidDValue(dvalue)) handleDValue(b,type,dvalue);
		if(infos.contains("+")) b.append(" auto_increment");
		
		if(infos.contains("*")) pkList.add(key);
		if(infos.contains("!")) unList.add(key);
		
		return b.toString();
	}
	
	private boolean isValidDValue(String dvalue)
	{
		if(dvalue==null) return false;
		if(dvalue.startsWith("#")) return false;
		if(dvalue.startsWith("->")) return false;
		return true;
	}
	
	private void handleDValue(StringBuffer b, String type, String dvalue)
	{
		if(type.toLowerCase().equals("datetime")) handleDDateTime(b,dvalue);
		else b.append(" default "+dvalue);
	}
	
	private void handleDDateTime(StringBuffer b, String dvalue)
	{
		if(dvalue.equals("c1"))
			b.append(" default CURRENT_TIMESTAMP");
		else b.append(" default "+dvalue);
	}
}
