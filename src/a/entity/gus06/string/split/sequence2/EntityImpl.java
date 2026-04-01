package a.entity.gus06.string.split.sequence2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201104";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		s = s.trim().replaceAll("[\n\t ]+"," ");
		
		String[] nn = s.split("[,;&]");
		for(int i=0;i<nn.length;i++) nn[i] = nn[i].trim();
		return nn;
	}
}