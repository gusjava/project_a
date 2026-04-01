package a.entity.gus06.sys.editor16x16.select.enlarge.rect;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250415";}

	public final static int NB = 16;

	
	public boolean f(Object obj) throws Exception
	{
		Set selection = (Set) obj;
		
		int xMin = 16;
		int yMin = 16;
		
		int xMax = -1;
		int yMax = -1;
		
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		if(selection.contains(i+"-"+j))
		{
			if(xMin>i) xMin = i;
			if(xMax<i) xMax = i;
			
			if(yMin>j) yMin = j;
			if(yMax<j) yMax = j;
		}
		int size = selection.size();
		int rectW = xMax-xMin+1;
		int rectH = yMax-yMin+1;
		int rectArea = rectW*rectH;
		boolean rectFull = rectArea==size;
		
		if(rectFull) return false;
		
		Set newSelection = new HashSet();
		for(int i=xMin;i<=xMax;i++)
		for(int j=yMin;j<=yMax;j++)
		{
			newSelection.add(i+"-"+j);
		}
		selection.clear();
		selection.addAll(newSelection);
		return true;
	}
}