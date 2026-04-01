package a.entity.gus06.sys.spreadsheet1.build.editabletable;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260102";}
	
	public static final String CELL_EDITABLE = "cell_editable";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[] dim = (int[]) o[0];
		Map map = (Map) o[1];
		
		if(map==null) return null;
		
		int rowNb = dim[0];
		int colNb = dim[1];
		
		boolean[][] editableTable = new boolean[rowNb][colNb];
		for(int i=0;i<rowNb;i++)
		for(int j=0;j<colNb;j++)
		editableTable[i][j] = isCellEditable(map, i,j);
		
		return editableTable;
	}
	
	private boolean isCellEditable(Map map, int x, int y)
	{
		String keyXY = keyXY(CELL_EDITABLE, x, y);
		if(has(map, keyXY)) return getBoolean(map, keyXY);
		
		String keyX = keyX(CELL_EDITABLE, x);
		String keyY = keyY(CELL_EDITABLE, y);
		if(has(map, keyX) && has(map, keyY)) return getBoolean(map, keyX) && getBoolean(map, keyY);
		if(has(map, keyX)) return getBoolean(map, keyX);
		if(has(map, keyY)) return getBoolean(map, keyY);
		
		String keyAll = keyAll(CELL_EDITABLE);
		if(has(map, keyAll)) return getBoolean(map, keyAll);
		return false;
	}
	
	private String keyXY(String base, int x, int y)
	{return base+"["+x+","+y+"]";}
	
	private String keyX(String base, int x)
	{return base+"["+x+",*]";}
	
	private String keyY(String base, int y)
	{return base+"[*,"+y+"]";}
	
	private String keyAll(String base)
	{return base+"[*,*]";}
	
	
	private boolean has(Map map, String key)
	{return map!=null && map.containsKey(key);}
	
	private String getString(Map map, String key)
	{return has(map, key) ? (String) map.get(key) : null;}
	
	private boolean getBoolean(Map map, String key)
	{return getBoolean(map, key, false);}
	
	private boolean getBoolean(Map map, String key, boolean defaultValue)
	{
		String val = getString(map, key);
		return val!=null ? Boolean.parseBoolean(val) : defaultValue;
	}
}
