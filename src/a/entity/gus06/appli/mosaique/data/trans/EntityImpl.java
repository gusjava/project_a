package a.entity.gus06.appli.mosaique.data.trans;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141115";}


	private Service bestFit;

	public EntityImpl() throws Exception
	{bestFit = Outside.service(this,"gus06.appli.mosaique.processor.bestfit");}
	
	
	public Object g() throws Exception
	{return bestFit;}
}
