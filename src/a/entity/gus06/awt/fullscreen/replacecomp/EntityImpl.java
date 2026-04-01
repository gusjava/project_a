package a.entity.gus06.awt.fullscreen.replacecomp;

import a.framework.*;

public class EntityImpl implements Entity, P, E, T {

	public String creationDate() {return "20151015";}


	private Service compReplacer;
	private E holder;
	
	public EntityImpl() throws Exception
	{
		compReplacer = Outside.service(this,"gus06.swing.comp.build.compreplacer");
	}


	public void p(Object obj) throws Exception
	{holder = (E) compReplacer.t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{return compReplacer.t(obj);}
	
	
	
	public void e() throws Exception
	{
		if(holder==null) throw new Exception("holder is null");
		holder.e();
	}
}
