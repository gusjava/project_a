package a.entity.gus06.sys.webserver1.web2.site.pseudo.jdbc.insert.newpseudo;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141014";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map map = (Map) o[1];
		
		String g = get(map,"g");
		String p = get(map,"p");
		String q = get(map,"q");
		String y = get(map,"y");
		String owner = get(map,"owner");
		String sign = get(map,"sign");
		String ip = get(map,"ip");
		
		
		String sql = "INSERT INTO pseudo (ID,g,p,q,y,sign,ip) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement st = cx.prepareStatement(sql);
		
		st.setString(1,owner);
		st.setString(2,g);
		st.setString(3,p);
		st.setString(4,q);
		st.setString(5,y);
		st.setString(6,sign);
		st.setString(7,ip);
		
		int res = st.executeUpdate();
		st.close();
		
		if(res!=1) throw new Exception("Failed to insert public key for owner: "+owner);
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
