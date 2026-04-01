package a.entity.gus06.jdbc.mysql.perform.table.create.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}
	
	public static final String FIELD_ID = "id";
	public static final String CHAR32 = "CHAR(32)";
	public static final String TEXT = "TEXT";
	

	private Service create;
	private Service dialog;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.jdbc.mysql.perform.table.create");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String tableName = (String) dialog.t("Table name");
		if(tableName==null || tableName.equals("")) return false;
		
		String tableFields = (String) dialog.t("Table fields");
		if(tableFields==null || tableFields.equals("")) return false;
		
		
		String[] f = parseFields(tableFields);
		
		String path = dbName+"."+tableName;
		String[] col = buildCol(f);
		String[] type = buildType(f);
		String[] primary = new String[]{FIELD_ID};
		
		create.p(new Object[]{cx,path,col,type,primary,null,null});
		return true;
	}
	
	
	
	
	private String[] parseFields(String s)
	{
		if(s.contains(" ")) return s.split(" ");
		if(s.contains(",")) return s.split(",");
		if(s.contains(";")) return s.split(";");
		return new String[]{s};
	}
	
	
	private String[] buildCol(String[] ff)
	{
		int nb = ff.length;
		String[] col = new String[nb+1];
		col[0] = FIELD_ID;
		for(int i=0;i<nb;i++) col[i+1] = ff[i];
		return col;
	}
	
	
	private String[] buildType(String[] ff)
	{
		int nb = ff.length;
		String[] type = new String[nb+1];
		type[0] = CHAR32;
		for(int i=0;i<nb;i++) type[i+1] = TEXT;
		return type;
	}
}
