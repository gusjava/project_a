package a.entity.gus06.jdbc.connection.builder.completeurl;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200421";}

	private Service putParams;

	public EntityImpl() throws Exception
	{
		putParams = Outside.service(this,"gus06.url.string.params.put");
	}
	
	public Object t(Object obj) throws Exception
	{
		String url = (String) obj;
		if(url.startsWith("jdbc:mysql:")) 
			return completeUrlForMysql(url);
		return url;
	}
	
	private String completeUrlForMysql(String url) throws Exception
	{
		//patch pour MYSQL8
		
		if(url.endsWith("]")) return url.substring(0,url.length()-1);
		
		Map newParams = new HashMap();
		newParams.put("useUnicode","true");
		newParams.put("useJDBCCompliantTimezoneShift","true");
		newParams.put("useLegacyDatetimeCode","false");
		newParams.put("serverTimezone","UTC");
		
		return (String) putParams.t(new Object[]{url,newParams});
	}
}
