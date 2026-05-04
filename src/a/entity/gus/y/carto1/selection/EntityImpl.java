package a.entity.gus.y.carto1.selection;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, V, R {

	public String creationDate() {return "20161202";}


	private Map rectMap;
	private Set selected;
	private Rectangle selectionArea;
	
	private int x1,y1;  // selection start
	private int x2,y2;  // selection end
	
	

	public EntityImpl() throws Exception
	{
		selected = new HashSet();
		selectionArea = new Rectangle();
	}



	public Object g() throws Exception
	{return selected;}



	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("rectMap")) {rectMap = (Map) obj;return;}
		if(key.equals("startArea")) {startArea((int[])obj);return;}
		if(key.equals("modifyArea")) {modifyArea((int[])obj);return;}
		if(key.equals("clearArea")) {clearArea();return;}
		
		throw new Exception("Unknown key: "+key);
	}


	public Object r(String key) throws Exception
	{
		if(key.equals("rectMap")) return rectMap;
		if(key.equals("selectionArea")) return selectionArea;
		
		if(key.equals("keys"))
			return new String[]{"rectMap","selectionArea"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void startArea(int[] n)
	{
		memStartPosition(n[0],n[1]);
		memEndPosition(n[0],n[1]);
		updateArea();
	}
	
	private void modifyArea(int[] n)
	{
		memEndPosition(n[0],n[1]);
		updateArea();
	}
	
	private void clearArea()
	{
		selectionArea.setBounds(-1,-1,0,0);
	}
	
	
	
	private void memStartPosition(int x,int y)
	{x1=x;y1=y;}
	
	private void memEndPosition(int x,int y)
	{x2=x;y2=y;}


	
	private void updateArea()
	{
		int xa = Math.min(x1,x2);
		int ya = Math.min(y1,y2);
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		selectionArea.setBounds(xa,ya,dx,dy);
		
		selected.clear();
		if(rectMap!=null)
		selected.addAll(group());
	}
	
	
	private Set group()
	{
		HashSet group = new HashSet();
		Iterator it = rectMap.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Rectangle label = (Rectangle)rectMap.get(name);
			if(label.intersects(selectionArea))
			group.add(name);
		}
		return group;
	}
}
