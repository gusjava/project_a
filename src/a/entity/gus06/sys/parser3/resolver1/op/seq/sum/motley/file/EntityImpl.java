package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161109";}


	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		File file = (File) oo[0];
		
		String path = buildPath(oo);
		return new File(file.getAbsolutePath()+path);
	}
	
	
	private String buildPath(Object[] oo) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=1;i<oo.length;i++)
		b.append(toString(oo[i]));
		return b.toString();
	}
	
	private String toString(Object o) throws Exception
	{
		if(o==null) return "null";
		
		String s = o.toString();
		String h = Integer.toHexString(o.hashCode());
		
		if(s.endsWith("@"+h)) throw new Exception("Object not compatible with String: "+o);
		return s;
	}
}
