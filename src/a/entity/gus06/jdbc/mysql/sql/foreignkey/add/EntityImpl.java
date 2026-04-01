package a.entity.gus06.jdbc.mysql.sql.foreignkey.add;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170321";}

	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String fkName = (String) o[1];
		String column1 = (String) o[2];
		String refTable = (String) o[3];
		String column2 = (String) o[4];
		
		return "ALTER TABLE "+format(path)+" ADD CONSTRAINT "+fkName+" FOREIGN KEY ("+format(column1)+") REFERENCES "+format(refTable)+"("+format(column2)+")";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}