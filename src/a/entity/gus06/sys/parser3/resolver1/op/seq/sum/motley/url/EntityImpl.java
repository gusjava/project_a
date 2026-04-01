package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.url;

import a.framework.*;
import java.net.URL;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190729";}


	private Service putParams;

	public EntityImpl() throws Exception
	{
		putParams = Outside.service(this,"gus06.url.params.put");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		URL url = (URL) oo[0];
		
		if(oo.length==2 && oo[1] instanceof Map)
		return putParams.t(oo);
		
		String path = buildPath(oo);
		return rebuildURL(url,path);
	}
	
	
	private URL rebuildURL(URL url, String path) throws Exception
	{
		if(path.equals("/") || path.equals("")) return url;
		
		String root = url.toString();
		if(!root.endsWith("/")) root = root+"/";
		if(path.startsWith("/")) path = path.substring(1);
		
		return new URL(root+path);
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
