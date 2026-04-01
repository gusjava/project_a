package a.entity.gus06.string.hiddenchars.asbool;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190316";}


	private Service isVisible;

	public EntityImpl() throws Exception
	{
		isVisible = Outside.service(this,"gus06.graphics.draw.string.isvisible");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) o[0];
		Font font = (Font) o[1];
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\t')
			{
				boolean found = isVisible.f(new Object[]{""+c,font});
				if(!found) return true;
			}
		}
		return false;
	}
}
