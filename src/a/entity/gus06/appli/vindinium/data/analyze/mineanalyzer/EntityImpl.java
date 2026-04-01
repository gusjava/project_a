package a.entity.gus06.appli.vindinium.data.analyze.mineanalyzer;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}


	private int[] pmine;
	private int[] mine0;

	public EntityImpl() throws Exception
	{e();}
	
	
	public void e() throws Exception
	{
		mine0 = new int[]{0,0,0,0};
		pmine = new int[]{0,0,0,0};
	}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		Map data = (Map) obj;
		
		int[] mine = (int[]) data.get(DATA_H_._H_MINE);
		for(int i=0;i<mine.length;i++)
		{
			if(mine[i] == mine0[i]+1) pmine[i]++;
			mine0[i] = mine[i];
		}
		data.put(DATA_H_._H_PMINE,pmine);
	}
}
