package a.entity.gus06.string.hiddenchars.asstring;

import a.framework.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190315";}


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
		
		StringBuilder b = new StringBuilder();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\t')
			{
				boolean found = isVisible.f(new Object[]{""+c,font});
				if(!found)
				{
					if(b.length()>0) b.append(";");
					b.append(i);
				}
			}
		}
		return b.toString();
	}
}
