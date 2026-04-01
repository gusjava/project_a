package a.entity.gus06.dir.findnearest;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160806";}


	private Service findDistance;
	
	public EntityImpl() throws Exception
	{
		findDistance = Outside.service(this,"gus06.data.compare.string.comparator1.distance");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = (File) o[0];
		String name = (String) o[1];
		
		File[] ff = input.listFiles();
		if(ff==null) return null;
		
		File output = null;
		double dist = Double.MAX_VALUE;
		
		for(File f:ff)
		{
			String name0 = f.getName();
			Number d = (Number) findDistance.t(new String[]{name,name0});
			double dist0 = d.doubleValue();
			
			if(dist0<dist)
			{
				dist = dist0;
				output = f;
			}
		}
		return output;
	}
}
