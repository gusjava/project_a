package a.entity.gus06.data.viewer.h;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20220619";}


	private Service screen;

	private H data;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.sys.function1.screen");
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return screen.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (H) obj;
		screen.p(data);
	}
}
