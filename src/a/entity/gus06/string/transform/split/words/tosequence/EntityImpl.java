package a.entity.gus06.string.transform.split.words.tosequence;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}

	private Service split;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.words1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] n = (String[]) split.t(obj);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		if(!n[i].equals("")) b.append(n[i]+";");
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}