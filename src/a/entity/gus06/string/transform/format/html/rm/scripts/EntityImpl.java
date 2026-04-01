package a.entity.gus06.string.transform.format.html.rm.scripts;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170114";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.replaceAll("(?si)<script[^>]*>.*?</script>"," ");
	}
}
