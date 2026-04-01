package a.entity.gus06.sys.xhtmlparser1.parser.regroup;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170226";}
	
	public static final String START = "<";
	public static final String END = ">";


	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		
		List list1 = new ArrayList();
		int step = 0;
		String token = null;
	
		int pos = 0;
		for(int i=0;i<list.size();i++)
		{
			String elem = (String) list.get(i);
			
			if(step==0)
			{
				list1.add(elem);
				step = 1;
			}
			else if(step==1)
			{
				if(!elem.equals(START)) throw new Exception("Invalid syntax at pos "+pos+" (opening character "+elem+" instead of "+START+")");
				step = 2;
			}
			else if(step==2)
			{
				list1.add(elem);
				step = 3;
			}
			else if(step==3)
			{
				if(!elem.equals(END)) throw new Exception("Invalid syntax at pos "+pos+" (closing character "+elem+" instead of "+END+")");
				step = 0;
			}
			pos += elem.length();
		}
	
		if(list1.size()%2==0) throw new Exception("Invalid token number:"+list1.size()+" (should be odd)");
		return list1;
	}
}