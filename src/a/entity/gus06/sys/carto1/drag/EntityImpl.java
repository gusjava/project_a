package a.entity.gus06.sys.carto1.drag;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, G, V, R {

	public String creationDate() {return "20161202";}


	private Map rectMap;
	private Set dragSet;
	
	private int x0,y0;  // previous mouse position
	
	private void memMousePosition(int x,int y)
	{x0=x;y0=y;}
	
	
	public EntityImpl() throws Exception
	{dragSet = new HashSet();}

	
	public Object g() throws Exception
	{return dragSet;}


	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("rectMap")) {rectMap = (Map) obj;return;}
		if(key.equals("start")) {start((int[])obj);return;}
		if(key.equals("move")) {move((int[])obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}


	public Object r(String key) throws Exception
	{
		if(key.equals("rectMap")) return rectMap;
		
		if(key.equals("keys"))
			return new String[]{"rectMap"};
		throw new Exception("Unknown key: "+key);
	}
	

	public void start(int[] n)
	{
		memMousePosition(n[0],n[1]);
	}
	
	
	public void move(int[] n)
	{
		Iterator it = dragSet.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			Rectangle label = (Rectangle) rectMap.get(element);
			label.translate(n[0]-x0,n[1]-y0);
		}
		memMousePosition(n[0],n[1]);
	}
}
