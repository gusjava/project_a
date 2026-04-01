package a.entity.gus06.jdbc.mysql.perform.rows.delete;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161020";}


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		String sql = "DELETE FROM "+format(path);
		
		PreparedStatement st = cx.prepareStatement(sql);
		st.executeUpdate();
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
