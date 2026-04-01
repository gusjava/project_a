package a.entity.gus06.jdbc.mysql.sql.constraint.unique.add;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220601";}

	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String cName = (String) o[1];
		String column = (String) o[2]; //TODO g�n�raliser � n colonnes
		
		return "ALTER TABLE "+format(path)+" ADD CONSTRAINT "+cName+" UNIQUE ("+format(column)+")";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}