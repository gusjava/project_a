package a.entity.gus06.string.split.sequence1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201013";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split("[,;]");
		for(int i=0;i<nn.length;i++) nn[i] = nn[i].trim();
		return nn;
	}
}
