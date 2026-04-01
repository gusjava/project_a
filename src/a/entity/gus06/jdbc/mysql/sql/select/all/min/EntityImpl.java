package a.entity.gus06.jdbc.mysql.sql.select.all.min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231112";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String colName = o[1];
		
		if(colName==null) return "SELECT * FROM "+format(path);
		if(colName.equals("")) throw new Exception("Invalid empty colName");
		
		String path_ = format(path);
		String colName_ = format(colName);
		String subQuery = "SELECT MIN("+colName_+") FROM "+path_;
		
		return "SELECT * FROM "+path_+" WHERE "+colName_+"=("+subQuery+")";
	}
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}