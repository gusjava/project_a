package a.entity.gus06.map.string.editor1.perform.ctrl_b;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240620";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_LOGIN = "login";
	public static final String KEY_PWD = "pwd";


	private Service browseLine;
	private Service buildSubMaps;
	private Service browseUrl;
	
	public EntityImpl() throws Exception
	{
		browseLine = Outside.service(this,"gus06.string.lines.browse");
		buildSubMaps = Outside.service(this,"gus06.map.string.submaps.l2");
		browseUrl = Outside.service(this,"gus06.string.lines.browse.openurl.withloginpwd");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Map sMap = (Map) o[1];
		
		Map sMapL2 = (Map) buildSubMaps.t(sMap);
		
		Iterator it = sMapL2.keySet().iterator();
		while(it.hasNext())
		{
			String k1 = (String) it.next();
			Map m1 = (Map) sMapL2.get(k1);
			
			if(m1.containsKey(KEY_URL))
			{
				String url = (String) m1.get(KEY_URL);
				String login = get(m1, KEY_LOGIN);
				String pwd = get(m1, KEY_PWD);
				
				if(login==null) login = get(map, k1+"."+KEY_LOGIN);
				if(pwd==null) pwd = get(map, k1+"."+KEY_PWD);
				
				if(login!=null && pwd!=null)
				{
					browseUrl.p(new String[]{url, login, pwd});
					return;
				}
			}
		}
		
		List values = new ArrayList(sMap.values());
		browseLine.p(values);
	}
	
	private String get(Map m, String key)
	{return m.containsKey(key) ? (String) m.get(key) : null;}
}