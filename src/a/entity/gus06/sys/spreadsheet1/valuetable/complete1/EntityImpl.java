package a.entity.gus06.sys.spreadsheet1.valuetable.complete1;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260102";}
	
	public static final String CELL_VALUE = "cell_value";
	public static final String CELL_VALUE_SCRIPT = "cell_value_script";

	private Service buildG;
	
	public EntityImpl() throws Exception
	{
		buildG = Outside.service(this,"gus06.sys.script1.build2.g");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[][] valueTable = (String[][]) o[0];
		Map map = (Map) o[1];
		
		Holder holder = new Holder(valueTable, map);
		return holder.errMsg;
	}
	
	
	private class Holder implements T
	{
		private int rowNb;
		private int colNb;
		private Map map;
		private String[][] valueTable;
		private String errMsg;
		
		private boolean[][] initialized;
		
		public Holder(String[][] valueTable, Map map) throws Exception
		{
			this.valueTable = valueTable;
			this.map = map;
			
			rowNb = valueTable.length;
			colNb = valueTable[0].length;
			
			initialized = new boolean[rowNb][colNb];
			
			for(int i=0;i<rowNb;i++)
			for(int j=0;j<colNb;j++)
			initialized[i][j] = valueTable[i][j]!=null;
			
			for(int i=0;i<rowNb;i++)
			for(int j=0;j<colNb;j++)
			{
				if(valueTable[i][j]==null) 
				valueTable[i][j] = compute(i, j);
			}
		}
	
		private boolean has(String key)
		{return map.containsKey(key);}
		
		private String getString(String key)
		{return has(key) ? (String) map.get(key) : null;}
		
		
		public Object t(Object obj) throws Exception
		{
			final int x = Integer.parseInt(""+obj);
			return new T() {
				public Object t(Object obj) throws Exception
				{
					final int y = Integer.parseInt(""+obj);
					return retrieve(x,y);
				}
			}; 
		}
		
		private String retrieve(int x, int y) throws Exception
		{
			if(valueTable[x][y]==null) valueTable[x][y] = compute(x, y);
			return valueTable[x][y];
		}
		
		private String compute(int x, int y) throws Exception
		{
			if(initialized[x][y])
			{
				errMsg = "Attempt to initialized cell twice: ["+x+","+y+"]";
				return null;
			}
			initialized[x][y] = true; //en cours d'initialisation
			
			String keyXY = keyXY(CELL_VALUE_SCRIPT, x, y);
			if(has(keyXY)) return applyScript(keyXY, x, y);
			keyXY = keyXY(CELL_VALUE, x, y);
			if(has(keyXY)) return getString(keyXY);
			
			String keyX = keyX(CELL_VALUE_SCRIPT, x);
			if(has(keyX)) return applyScript(keyX, x, y);
			keyX = keyX(CELL_VALUE, x);
			if(has(keyX)) return getString(keyX);
			
			String keyY = keyY(CELL_VALUE_SCRIPT, y);
			if(has(keyY)) return applyScript(keyY, x, y);
			keyY = keyY(CELL_VALUE, y);
			if(has(keyY)) return getString(keyY);
			
			String keyAll = keyAll(CELL_VALUE_SCRIPT);
			if(has(keyAll)) return applyScript(keyAll, x, y);
			keyAll = keyAll(CELL_VALUE);
			if(has(keyAll)) return getString(keyAll);
			
			return "";
		}
	
		private String applyScript(String key, int x, int y) throws Exception
		{
			String script = getString(key);
			
			Map data = new HashMap();
			data.put("x",Integer.valueOf(x));
			data.put("y",Integer.valueOf(y));
			data.put("rowNb",Integer.valueOf(rowNb));
			data.put("colNb",Integer.valueOf(colNb));
			data.put("map",map);
			data.put("values",this);
			
			G g = (G) buildG.t(new Object[]{script,data});
			return ""+g.g();
		}
	}
	
	private String keyXY(String base, int x, int y)
	{return base+"["+x+","+y+"]";}
	
	private String keyX(String base, int x)
	{return base+"["+x+",*]";}
	
	private String keyY(String base, int y)
	{return base+"[*,"+y+"]";}
	
	private String keyAll(String base)
	{return base+"[*,*]";}
}
