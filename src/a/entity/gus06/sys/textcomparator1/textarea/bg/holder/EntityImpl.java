package a.entity.gus06.sys.textcomparator1.textarea.bg.holder;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P, I, E, V, R {

	public String creationDate() {return "20190616";}


	private Service buildComp;
	private Service bgBuilder;

	private JTextArea comp;
	private Object bgHolder;
	

	public EntityImpl() throws Exception
	{
		buildComp = Outside.service(this,"gus06.swing.textarea.factory1");
		bgBuilder = Outside.service(this,"gus06.sys.textcomparator1.textarea.bg.builder");
		
		comp = (JTextArea) buildComp.i();
		bgHolder = bgBuilder.t(comp);
	}
	
	
	public Object i() throws Exception
	{return comp;}
	
	public void e() throws Exception
	{((E) bgHolder).e();}
	
	public void p(Object obj) throws Exception
	{((P) bgHolder).p(obj);}
	
	public void v(String key, Object obj) throws Exception
	{((V) bgHolder).v(key,obj);}
	
	public Object r(String key) throws Exception
	{return ((R)bgHolder).r(key);}
}