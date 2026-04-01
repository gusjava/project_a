package a.entity.gus06.appli.mosaique.data.dist;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141122";}


	private Service distance;

	public EntityImpl() throws Exception
	{
		distance = Outside.service(this,"gus06.awt.bufferedimage.distance.grid_3_3");
	}
	
	
	public Object g() throws Exception
	{
		return distance;
	}
	
	
}
