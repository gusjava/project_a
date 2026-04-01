package a.entity.gus06.data.editor.string.entityname;

import a.framework.*;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20220909";}


	private Service selector;
	private Service find;
	
	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		find = Outside.service(this,"gus06.app.jarfile.listing.entities");
		selector.p(find.g());
	}
	
	
	public Object i() throws Exception
	{return selector.i();}
	
	
	public Object g() throws Exception
	{return selector.g();}
	
	
	public void p(Object obj) throws Exception
	{selector.v("search",obj);}
	
	
	public Object r(String key) throws Exception
	{return selector.r(key);}
}