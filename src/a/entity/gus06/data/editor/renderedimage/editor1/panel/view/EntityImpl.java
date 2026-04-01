package a.entity.gus06.data.editor.renderedimage.editor1.panel.view;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20250228";}


	private Service screen;
	private Object image;
	
	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image.copy");
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return screen.i();}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		screen.p(image);
	}
	
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}