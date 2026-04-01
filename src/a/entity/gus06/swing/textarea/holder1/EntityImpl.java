package a.entity.gus06.swing.textarea.holder1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20180207";}


	private Service factory;
	private Service buildPanel;
	
	private JTextComponent comp;
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory1");
		buildPanel = Outside.service(this,"gus06.swing.textarea.buildpanel1");
		
		comp = (JTextComponent) factory.i();
		panel = (JPanel) buildPanel.t(comp);
	}
	
	
	public Object g() throws Exception
	{return comp.getText();}
	
	
	public void p(Object obj) throws Exception
	{comp.setText((String) obj);}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("keys")) return new String[]{"comp"};
		
		throw new Exception("Unknown key: "+key);
	}
}
