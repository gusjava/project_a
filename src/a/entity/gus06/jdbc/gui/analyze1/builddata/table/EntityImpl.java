package a.entity.gus06.jdbc.gui.analyze1.builddata.table;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230225";}
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";
	
	public static final String COL1_DB = "TABLE_SCHEMA";
	public static final String COL1_TABLE = "TABLE_NAME";
	public static final String COL1_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL1_COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COL1_COLUMN_KEY = "COLUMN_KEY";
	public static final String COL1_IS_NULLABLE = "IS_NULLABLE";
	public static final String COL1_EXTRA = "EXTRA";
	
	public static final String COL1_TYPE = "TYPE";
	public static final String COL1_AUTOINCR = "AUTOINCR";
	public static final String COL1_NN = "NN";
	public static final String COL1_PK = "PK";
	public static final String COL1_UK = "UK";
	public static final String COL1_FK = "FK";
	public static final String COL1_FK0 = "FK0";
	public static final String COL1_FK0E = "FK0E";


	private Service colTypeToDataType;

	public EntityImpl() throws Exception
	{
		colTypeToDataType = Outside.service(this,"gus06.jdbc.gui.analyze1.tool.coltypetodatatype");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((String) obj);}
	
	
	
	private class Holder implements R, V, P
	{
		private String tableName;
		private String engine;
		
		private Map mapCol = new HashMap();
		private Map mapUk = new HashMap();
		private Map mapFk = new HashMap();
		private Map mapFk0 = new HashMap();
		private Map mapFk0e = new HashMap();
		
		private List listPk = new ArrayList();
		private List listUk = new ArrayList();
		private List listFk = new ArrayList();
		private List listRefFk = new ArrayList();
		
		private Integer nbRow = 0;
		
		private int nbAutoInc = 0;
		private int nbN = 0;
		private int nbNN = 0;
		
		private int nbBoolean = 0;
		private int nbInteger = 0;
		private int nbDouble = 0;
		private int nbLong = 0;
		private int nbDate = 0;
		private int nbString = 0;
		private int nbLString = 0;
		
		
		
		public Holder(String tableName)
		{this.tableName = tableName;}
		
		public void p(Object obj) throws Exception
		{
			Map map = (Map) obj;
			
			String colName = (String) map.get(COL1_COLUMN_NAME);
			String colType = (String) map.get(COL1_COLUMN_TYPE);
			String colKey = (String) map.get(COL1_COLUMN_KEY);
			String nullable = (String) map.get(COL1_IS_NULLABLE);
			String extra = (String) map.get(COL1_EXTRA);
			
			if(mapCol.containsKey(colName)) throw new Exception("Column found many times: "+tableName+"@"+colName);
			mapCol.put(colName, map);
			
			// PRIMARY
			
			if(colKey.equals("PRI"))
			{
				map.put(COL1_PK,"YES");
				listPk.add(colName);
			}
			
			// AUTO INC
			
			if(extra.equals("auto_increment"))
			{
				nbAutoInc++;
				map.put(COL1_AUTOINCR,"YES");
			}
			
			// NULLABLE / NOT NULLABLE
			
			if(nullable.equals("YES"))
			{
				nbN++;
			}
			else if(nullable.equals("NO"))
			{
				nbNN++;
				map.put(COL1_NN,"YES");
			}
			else throw new Exception("Unsupported nullable value: "+nullable);
			
			
			
			// COLUMN TYPES
			
			if(colType==null) throw new Exception("Null colType detected");
			String type = (String) colTypeToDataType.t(colType);
			
			map.put(COL1_TYPE, type);
			
			switch(type)
			{
				case TYPE_BOOLEAN:nbBoolean++;break;
				case TYPE_INTEGER:nbInteger++;break;
				case TYPE_LONG:nbLong++;break;
				case TYPE_DOUBLE:nbDouble++;break;
				case TYPE_DATE:nbDate++;break;
				case TYPE_STRING:nbString++;break;
				case TYPE_LSTRING:nbLString++;break;
				default: throw new Exception("Unsupported data type: "+type+" for "+tableName+"@"+colName);
			}
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("engine")) {engine = (String) obj;return;}
			if(key.equals("nbRow")) {nbRow = (Integer) obj;return;}
			if(key.equals("addUk")) {addUk((String[]) obj);return;}
			if(key.equals("addFk")) {addFk((String[]) obj);return;}
			if(key.equals("addRefFk")) {addRefFk((String[]) obj);return;}
			if(key.equals("analyze")) {analyze((Map) obj);return;}
			if(key.equals("setFk0e")) {setFk0e((Object[]) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("engine")) return engine;
			if(key.equals("mapCol")) return mapCol;
			if(key.equals("mapUk")) return mapUk;
			if(key.equals("mapFk")) return mapFk;
			if(key.equals("mapFk0")) return mapFk0;
			if(key.equals("mapFk0e")) return mapFk0e;
			
			if(key.equals("listPk")) return listPk;
			if(key.equals("listUk")) return listUk;
			if(key.equals("listFk")) return listFk;
			if(key.equals("listRefFk")) return listRefFk;
			
			if(key.equals("nbCol")) return mapCol.size();
			if(key.equals("nbRow")) return nbRow;
			if(key.equals("nbPk")) return listPk.size();
			if(key.equals("nbUk")) return listUk.size();
			if(key.equals("nbFk")) return listFk.size();
			if(key.equals("nbFk0")) return mapFk0.size();
			if(key.equals("nbFk0e")) return mapFk0e.size();
			if(key.equals("nbRefFk")) return listRefFk.size();
			if(key.equals("nbAutoInc")) return nbAutoInc;
			if(key.equals("nbN")) return nbN;
			if(key.equals("nbNN")) return nbNN;
			
			if(key.equals("nbBoolean")) return nbBoolean;
			if(key.equals("nbInteger")) return nbInteger;
			if(key.equals("nbLong")) return nbLong;
			if(key.equals("nbDate")) return nbDate;
			if(key.equals("nbDouble")) return nbDouble;
			if(key.equals("nbString")) return nbString;
			if(key.equals("nbLString")) return nbLString;
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void addUk(String[] infos)
		{
			String colName = infos[0];
			String constraintName = infos[1];
			
			if(!mapUk.containsKey(constraintName)) mapUk.put(constraintName, new ArrayList());
			((List) mapUk.get(constraintName)).add(colName);
			
			((Map) mapCol.get(colName)).put(COL1_UK, constraintName);
			listUk.add(infos);
		}
		
		private void addFk(String[] infos)
		{
			String colName = infos[0];
			String refTableName = infos[1];
			String refColName = infos[2];
			
			listFk.add(infos);
			mapFk.put(colName, new String[]{refTableName, refColName});
			((Map) mapCol.get(colName)).put(COL1_FK, new String[]{refTableName, refColName});
		}
		
		private void addRefFk(String[] infos)
		{
			listRefFk.add(infos);
		}
		
		public void analyze(Map mapTablesPK)
		{
			Iterator it = mapCol.keySet().iterator();
			while(it.hasNext())
			{
				String colName = (String) it.next();
				Map colInfo = (Map) mapCol.get(colName);
			
				String colType = (String) colInfo.get(COL1_COLUMN_TYPE);
				String colKey = (String) colInfo.get(COL1_COLUMN_KEY);
				
				if(isTypeLong(colType) 
					&& colName.endsWith("_id") 
					&& !mapFk.containsKey(colName) 
					&& !listPk.contains(colName))
				{
					String[] refTable0 = findRefTable0(colName, mapTablesPK);
					if(refTable0!=null)
					{
						mapFk0.put(colName, refTable0);
						((Map) mapCol.get(colName)).put(COL1_FK0, refTable0);
					}
				}
			}
		}
		
		
		private void setFk0e(Object[] infos)
		{
			String colName = (String) infos[0];
			Integer count = (Integer) infos[1];
			
			((Map) mapCol.get(colName)).put(COL1_FK0E, count);
			mapFk0e.put(colName,count);
		}
		
		
		
		
		private String[] findRefTable0(String colName, Map mapTablesPK)
		{
			String colName0 = colName.substring(0,colName.length()-3);
			if(mapTablesPK.containsKey(colName0))
			{
				List pks = (List) mapTablesPK.get(colName0);
				if(pks.size()==1) return new String[]{colName0,(String) pks.get(0)};
				return null;
			}
			
			if(colName0.equals("creator") 
				|| colName0.equals("updater") 
				|| colName0.equals("deleter") 
				|| colName0.equals("author"))
			{
				if(mapTablesPK.containsKey("user"))
				{
					List pks = (List) mapTablesPK.get("user");
					if(pks.size()==1) return new String[]{"user",(String) pks.get(0)};
				}
			}
			return null;
		}
		
		
		
		private boolean isTypeBoolean(String colType)
		{return isOfTypes(colType,"tinyint");}
		
		private boolean isTypeInteger(String colType)
		{return isOfTypes(colType,"int","smallint");}
		
		private boolean isTypeLong(String colType)
		{return isOfTypes(colType,"bigint");}
		
		private boolean isTypeDouble(String colType)
		{return isOfTypes(colType,"decimal","float","double");}
		
		private boolean isTypeDate(String colType)
		{return isOfTypes(colType,"date","datetime","timestamp");}
		
		private boolean isTypeLString(String colType)
		{return isOfTypes(colType,"mediumtext","longtext","text");}
		
		private boolean isTypeString(String colType)
		{return isOfTypes(colType,"varchar","char");}
		
		
		
		private boolean isOfTypes(String colType, String... types)
		{
			for(String type : types) if(isOfType(colType,type)) return true;
			return false;
		}
		
		private boolean isOfType(String colType, String type)
		{return colType.equals(type) || colType.startsWith(type+"(");}
		
	}
}