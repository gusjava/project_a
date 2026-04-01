package a.entity.gus06.swing.frame.show2.smart;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20220613";}


	private Service show;
	private Service build;


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		build = Outside.service(this,"gus06.swing.frame.show.smart.build");
	}
	
	
	private Object comp(Object obj) throws Exception
	{
		Object viewer = build.t(obj);
		((P)viewer).p(obj);
		return ((I)viewer).i();
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = comp(o[0]);
		return show.t(new Object[]{comp,o[1],o[2]});
	}
}