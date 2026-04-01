package a.entity.gus06.string.hiddenchars.asmap;

import a.framework.*;
import java.awt.Font;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190316";}


	private Service isVisible;

	public EntityImpl() throws Exception
	{
		isVisible = Outside.service(this,"gus06.graphics.draw.string.isvisible");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) o[0];
		Font font = (Font) o[1];
		
		Map map = new HashMap();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\t')
			{
				String c1 = ""+c;
				boolean found = isVisible.f(new Object[]{c1,font});
				if(!found) map.put(i,c1);
			}
		}
		return map;
	}
}
