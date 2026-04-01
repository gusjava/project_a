package a.entity.gus06.jdbc.resultset.next.todouble;

import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231031";}



	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		if(!rs.next()) {rs.close();return null;}
		
		double res = rs.getDouble(1);
		rs.close();
		return Double.valueOf(res);
	}
}