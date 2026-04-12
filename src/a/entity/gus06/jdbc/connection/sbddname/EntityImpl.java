package a.entity.gus06.jdbc.connection.sbddname;

import a.framework.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20260110";}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(!(obj instanceof Connection))
			throw new Exception("Invalid data type: "+obj.getClass().getName());

		Connection cx = (Connection) obj;
		DatabaseMetaData meta = cx.getMetaData();

		String url = meta.getURL();
		if(url!=null)
		{
			url = url.toLowerCase();

			if(url.startsWith("jdbc:mysql:")) return "mysql";
			if(url.startsWith("jdbc:mariadb:")) return "mariadb";
			if(url.startsWith("jdbc:h2:")) return "h2";
			if(url.startsWith("jdbc:sqlite:")) return "sqlite";
			if(url.startsWith("jdbc:postgresql:")) return "postgresql";
			if(url.startsWith("jdbc:oracle:")) return "oracle";
		}

		String name = meta.getDatabaseProductName();
		if(name!=null)
		{
			name = name.toLowerCase();

			if(name.contains("mysql")) return "mysql";
			if(name.contains("mariadb")) return "mariadb";
			if(name.contains("h2")) return "h2";
			if(name.contains("sqlite")) return "sqlite";
			if(name.contains("postgresql")) return "postgresql";
			if(name.contains("oracle")) return "oracle";
		}

		throw new Exception("Unsupported SBDD name: "+meta.getDatabaseProductName());
	}
}