package a.entity.gus06.appli.vindinium.data.analyze.positionanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}


	private List[] paths;

	public EntityImpl() throws Exception
	{e();}

	
	public void e() throws Exception
	{
		paths = new List[4];
		for(int i=0;i<4;i++) paths[i] = new ArrayList();
	}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		Map data = (Map) obj;
		int[][] _h_pos = (int[][]) data.get(DATA_H_._H_POS);
		int[][] pos0 = new int[4][2];
		boolean[] immobile = new boolean[4];

		for(int i=0;i<4;i++)
		{
			List l = paths[i];
			pos0[i] = l.isEmpty()?null:(int[])l.get(l.size()-1);
			l.add(_h_pos[i]);
			
			immobile[i] = isImmobile(l);
		}

		data.put(DATA_H_._H_PATH,paths);
		data.put(DATA_H_._H_POS0,pos0);
		data.put(DATA_H_._H_IMMOBILE,immobile);
	}
	
	
	private boolean isImmobile(List l)
	{
		if(l.size()<3) return false;
		
		int[] p1 = (int[]) l.get(l.size()-3);
		int[] p2 = (int[]) l.get(l.size()-2);
		int[] p3 = (int[]) l.get(l.size()-1);
		
		return equals(p1,p2) && equals(p2,p3);
	}
	
	private boolean equals(int[] p1, int[] p2)
	{return p1[0]==p2[0] && p1[1]==p2[1];}
}
