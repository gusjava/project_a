package a.entity.gus06.sys.parser1.process.regroup;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}


	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	private class T1 implements T
	{
		private String start;
		private String end;
		
		public T1(String info)
		{
			start = info.substring(0,1);
			end = info.substring(1,2);
		}
		
		public Object t(Object obj) throws Exception
		{
			List list = (List) obj;
			
			List list1 = new ArrayList();
			int step = 0;
			String token = null;
		
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
					if(!elem.equals(start)) throw new Exception("Invalid syntax (opening character "+elem+" instead of "+start+")");
					step = 2;
				}
				else if(step==2)
				{
					list1.add(elem);
					step = 3;
				}
				else if(step==3)
				{
					if(!elem.equals(end)) throw new Exception("Invalid syntax (closing character "+elem+" instead of "+end+")");
					step = 0;
				}
			}
		
			if(list1.size()%2==0) throw new Exception("Invalid token number:"+list1.size()+" (should be odd)");
			return list1;
		}
	}
}
