package a.entity.gus06.sys.webserver1.web2.site.pseudo.jdbc.select.pseudo;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141016";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String id = (String) o[1];
		
		String sql = "SELECT * FROM pseudo WHERE ID=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1,id);
		
		ResultSet rs = st.executeQuery();
		if(!rs.next()) return null;
		
		Map map = new HashMap();
		
		map.put("g",rs.getString(2));
		map.put("p",rs.getString(3));
		map.put("q",rs.getString(4));
		map.put("y",rs.getString(5));
		
		st.close();
		return map;
	}
}
