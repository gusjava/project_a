package a.entity.gus06.swing.panel.screen.image.copy;

import a.framework.*;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20180308";}


	private Service screen;
	private Service onKey;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		clipboard = Outside.service(this,"gus.x.clipboard.image");
		
		onKey.p(new Object[]{screen.i(),"ctrl c",new E(){
			public void e() throws Exception {copy();}
		}});
	}
	
	
	private void copy()
	{
		try
		{
			Object img = screen.g();
			clipboard.p(img);
		}
		catch(Exception e)
		{Outside.err(this,"copy()",e);}
	}


	
	
	public Object g() throws Exception
	{return screen.g();}
	
	public Object i() throws Exception
	{return screen.i();}
	
	public void p(Object obj) throws Exception
	{screen.p(obj);}
}
