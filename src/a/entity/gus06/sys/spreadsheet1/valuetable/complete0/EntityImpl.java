package a.entity.gus06.sys.spreadsheet1.valuetable.complete0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260102";}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String[][] valueTable = (String[][]) o[0];
		boolean[][] editableTable = (boolean[][]) o[1];
		Map dataMap = (Map) o[2];
		
		if(valueTable==null) return;
		if(dataMap==null) return;
		
		int rowNb = valueTable.length;
		if(rowNb==0) return;
		
		int colNb = valueTable[0].length;
		
		for(int i=0;i<rowNb;i++)
		for(int j=0;j<colNb;j++)
		if(editableTable[i][j])
		{
			String key = i+","+j;
			valueTable[i][j] = get(dataMap,key);
		}
	}
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return "";
		return (String) map.get(key);
	}
}
