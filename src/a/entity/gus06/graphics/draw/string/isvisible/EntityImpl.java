package a.entity.gus06.graphics.draw.string.isvisible;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190315";}


	private Service findDim;

	public EntityImpl() throws Exception
	{
		findDim = Outside.service(this,"gus06.graphics.draw.string.finddimension1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		int[] dim = (int[]) findDim.t(obj);
		return dim[0]>0 && dim[1]>0;
	}
}
