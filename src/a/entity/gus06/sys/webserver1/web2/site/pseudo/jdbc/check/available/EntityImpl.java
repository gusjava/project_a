package a.entity.gus06.sys.webserver1.web2.site.pseudo.jdbc.check.available;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141014";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String pseudo = (String) o[1];
		
		String sql = "SELECT COUNT(*) FROM pseudo WHERE ID=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1,pseudo);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
    		String count = rs.getString(1);
    		st.close();
		return count.equals("0");
	}
}
