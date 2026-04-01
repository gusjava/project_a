package a.entity.gus06.appli.gusclient1.project.deploy.buildvalidator;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150311";}

	
	private Service findListing;
	


	public EntityImpl() throws Exception
	{
		findListing = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing");
	}
	
	
	public Object g() throws Exception
	{
		Set set = (Set) findListing.g();
		return new Filter(set);
	}
	
	
	private class Filter implements F
	{
		private Set set;
		public Filter(Set set) {this.set = set;}
		
		public boolean f(Object obj) throws Exception
		{return set.contains(obj);}
	}
}
