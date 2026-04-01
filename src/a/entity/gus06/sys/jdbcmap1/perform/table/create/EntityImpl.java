package a.entity.gus06.sys.jdbcmap1.perform.table.create;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20150625";}
	
	public static final String FIELD_ID = "ID";
	public static final String CHAR32 = "CHAR(32)";
	public static final String TEXT = "TEXT";
	

	private Service create;
	private Service toArray;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.jdbc.mysql.perform.table.create");
		toArray = Outside.service(this,"gus06.find.stringarray");
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String[] fields = toArray(o[2]);
		
		String[] col = buildCol(fields);
		String[] type = buildType(fields);
		String[] primary = new String[]{FIELD_ID};
		
		create.p(new Object[]{cx,path,col,type,primary,null,null});
		return true;
	}
	
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj instanceof String) return ((String) obj).split("[ ,;]");
		return (String[]) toArray.t(obj);
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
