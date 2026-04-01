package a.entity.gus06.swing.textarea.holder2.readonly;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20201129";}


	private Service factory;
	
	private JTextComponent comp;
	private JScrollPane scroll;


	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory1");
		
		comp = (JTextComponent) factory.i();
		comp.setEditable(false);
		comp.setMargin(new Insets(3,3,3,3));
		scroll = new JScrollPane(comp);
	}
	
	
	public Object g() throws Exception
	{return comp.getText();}
	
	
	public void p(Object obj) throws Exception
	{
		comp.setText((String) obj);
		comp.setCaretPosition(0);
	}
	
	
	public Object i() throws Exception
	{return scroll;}

	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("keys")) return new String[]{"comp"};
		
		throw new Exception("Unknown key: "+key);
	}
}