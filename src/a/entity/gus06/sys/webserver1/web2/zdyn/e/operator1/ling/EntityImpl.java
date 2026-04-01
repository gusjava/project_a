package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator1.ling;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141003";}


	private Service findLang;
	
	public EntityImpl() throws Exception
	{findLang = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.language.find");}

	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		String[] n = info.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:"?"+key+"?";
		
		Map lingMap = (Map) mr.r("data config ling");
		String lang = (String) findLang.t(mr);
		String lingKey = lang+"."+key;
		
		if(!lingMap.containsKey(lingKey)) return dValue;
		return lingMap.get(lingKey);
	}
}
