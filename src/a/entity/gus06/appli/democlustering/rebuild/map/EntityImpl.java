package a.entity.gus06.appli.democlustering.rebuild.map;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.awt.Color;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170108";}
	
	public static final Color[] COLORS = new Color[]{
		Color.GRAY,
		Color.BLUE,
		Color.ORANGE,
		Color.YELLOW,
		Color.PINK,
		Color.GREEN,
		Color.RED
	};

	
	
	public Object t(Object obj) throws Exception
	{
		List[] o = (List[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List points = o[0];
		List sets = o[1];
		
		Map map = new HashMap();
		
		for(int i=0;i<sets.size();i++)
		{
			Set set = (Set) sets.get(i);
			Color color = COLORS[i];
			
			Iterator it = set.iterator();
			while(it.hasNext())
			{
				int n = ((Integer) it.next()).intValue();
				Object point = points.get(n);
				map.put(point,color);
			}
		}
		
		return map;
	}
}
