package a.entity.gus06.map.deep.get;

import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20160223";}


	private Service buildPath;
	private Service nextData;

	public EntityImpl() throws Exception
	{
		buildPath = Outside.service(this,"gus06.map.deep.buildpath");
		nextData = Outside.service(this,"gus06.map.deep.nextdata0");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String[] path = buildPath(o[1]);
		
		for(int i=0;i<path.length;i++)
		data = nextData(data,path[i]);
		
		return data;
	}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	private String[] buildPath(Object key) throws Exception
	{return (String[]) buildPath.t(key);}
	
	
	private Object nextData(Object data, String key) throws Exception
	{return nextData.t(new Object[]{data,key});}
}