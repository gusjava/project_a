package a.entity.gus06.jdbc.mysql.sql.db.select.all.where.user;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}



	private Service format;

	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.sequence");}



	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String user = o[0];
		String host = o[1];
		
		return "SELECT * FROM mysql.db WHERE User="+format(user)+" AND Host="+format(host);
	}

	private String format(String value) throws Exception
	{return (String) format.t(value);}
}
