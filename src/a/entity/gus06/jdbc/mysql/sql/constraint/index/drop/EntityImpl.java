package a.entity.gus06.jdbc.mysql.sql.constraint.index.drop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220601";}

	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String cName = (String) o[1];
		
		return "ALTER TABLE "+format(path)+" DROP INDEX "+cName;
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}