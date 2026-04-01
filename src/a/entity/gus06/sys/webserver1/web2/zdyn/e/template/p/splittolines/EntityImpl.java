package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.p.splittolines;

import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}

	
	
	public Object t(Object obj) throws Exception
	{
		String content = (String) obj;
		String[] n = content.split("\n");
		ArrayList list = new ArrayList();
		
		for(String line:n)
		{
			if(line.startsWith(":")) continue;
			
			line = line.trim();
			StringBuffer buff = new StringBuffer();
			int level = 0;
			
			for(int i=0;i<line.length();i++)
			{
				char c = line.charAt(i);
				if(c=='{')
				{
					if(level==0)
					{
						if(buff.length()>0) list.add(buff.toString());
						buff = new StringBuffer();
					}
					
					buff.append(c);
					level++;
				}
				else if(c=='}')
				{
					buff.append(c);
					level--;
					
					if(level==0)
					{
						if(buff.length()>0) list.add(buff.toString());
						buff = new StringBuffer();
					}
				}
				else buff.append(c);
			}
			
			if(buff.length()>0) list.add(buff.toString());
		}
		return list;
	}
}
