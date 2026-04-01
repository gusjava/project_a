package a.entity.gus06.data.ebookname.list.findbest;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250608";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.isEmpty()) return null;
		if(list.size()==1) return list.get(0);
		
		String bestName = null;
		int bestScore = 0;
		
		for(int i=0;i<list.size();i++)
		{
			String name = (String) list.get(i);
			int score = buildScore(list, name);
			if(score>bestScore)
			{
				bestScore = score;
				bestName = name;
			}
		}
		return bestName;
	}
	
	private int buildScore(List list, String name)
	{
		for(int i=0;i<list.size();i++)
		{
			String otherName = (String) list.get(i);
			if(!otherName.equals(name))
			{
				if(name.equals(otherName+" (0)")) return 0;
				if(name.equals(otherName+" (1)")) return 0;
			}	
		}
		return 1;
	}
}