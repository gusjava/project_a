package a.entity.gus06.data.viewer.string.src.java;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20140809";}


//	private Service highSyntax;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
//		highSyntax = Outside.service(this,"gus06.swing.textpane.cust.syntax.java.ostermiller");
		viewer = Outside.service(this,"*gus06.data.viewer.string.textarea.editor1");
	
		JTextComponent comp = (JTextComponent) viewer.r("comp");
		
//		highSyntax.p(comp);
	}
	
	
		
	public Object g() throws Exception
	{return viewer.g();}
	
	
	public Object i() throws Exception
	{return viewer.i();}
	
	public void p(Object obj) throws Exception
	{viewer.p(obj);}
	
	
	public Object r(String key) throws Exception
	{return viewer.r(key);}
}