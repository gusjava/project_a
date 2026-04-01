package a.entity.gus06.appli.convertisseurgus05.holder.selected;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20150525";}


	private String name;
	
	public Object g() throws Exception
	{return name;}
	
	
	public void p(Object obj) throws Exception
	{
		name = (String) obj;
		modified();
	}
	
	
	private void modified()
	{send(this,"modified()");}
}
