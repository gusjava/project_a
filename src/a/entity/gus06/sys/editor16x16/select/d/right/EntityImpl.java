package a.entity.gus06.sys.editor16x16.select.d.right;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250315";}

	public final static int NB = 16;
	
	
	public void p(Object obj) throws Exception
	{
		Set selection = (Set) obj;
		if(selection.isEmpty()) return;
		Set newSelection = new HashSet();
		
		for(int i=0;i<NB;i++)
		for(int j=NB-1;j>=0;j--)
		{
			String key = i+"-"+(j-1);
			if(selection.contains(key))
			{
				newSelection.add(i+"-"+j);
			}
		}
		selection.clear();
		selection.addAll(newSelection);
	}
}
