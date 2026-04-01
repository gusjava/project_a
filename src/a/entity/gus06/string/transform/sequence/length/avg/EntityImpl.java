package a.entity.gus06.string.transform.sequence.length.avg;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int sum = 0;
		for(String n:nn) sum += n.length();
		int count = nn.length;
		
		double avg = (double)sum/(double)count;
		return ""+avg;
	}
}
