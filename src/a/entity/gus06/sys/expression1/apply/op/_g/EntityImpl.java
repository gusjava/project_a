package a.entity.gus06.sys.expression1.apply.op._g;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof G) return ((G) obj).g();
		if(obj instanceof G[]) return apply((G[]) obj);
		if(obj instanceof G[][]) return apply((G[][]) obj);
		if(obj instanceof List) return apply((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object[] apply(G[] g) throws Exception
	{
		Object[] r = new Object[g.length];
		for(int i=0;i<g.length;i++) r[i] = g[i].g();
		return r;
	}
	
	private List apply(List g) throws Exception
	{
		List r = new ArrayList();
		for(int i=0;i<g.size();i++) r.add(((G) g.get(i)).g());
		return r;
	}
	
	private Object[][] apply(G[][] g) throws Exception
	{
		int x = g.length;
		int y = x>0 ? g[0].length : 0;
		
		Object[][] r = new Object[x][y];
		for(int i=0;i<x;i++) for(int j=0;j<y;j++) r[i][j] = g[i][j].g();
		return r;
	}
}
