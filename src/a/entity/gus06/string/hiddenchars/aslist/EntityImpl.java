package a.entity.gus06.string.hiddenchars.aslist;

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
		
		List list = new ArrayList();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\t')
			{
				boolean found = isVisible.f(new Object[]{""+c,font});
				if(!found) list.add(i);
			}
		}
		return list;
	}
}
