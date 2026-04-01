package a.entity.gus06.string.transformfinder.fromsequence;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}


	private Service fromKeyword;
	private Service pipe;


	public EntityImpl() throws Exception
	{
		fromKeyword = Outside.service(this,"gus06.string.transformfinder.fromkeyword");
		pipe = Outside.service(this,"gus06.feature.op.pipe.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] n = s.split(";");
		T[] t = new T[n.length];
		
		for(int i=0;i<n.length;i++)
		t[i] = (T) fromKeyword.t(n[i]);
		
		return pipe.t(t);
	}
}
