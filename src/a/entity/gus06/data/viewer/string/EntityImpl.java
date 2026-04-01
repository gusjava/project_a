package a.entity.gus06.data.viewer.string;

import a.framework.*;
import javax.swing.*;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20140731";}


	private String data;

	private JTextArea area;
	private JScrollPane scroll;
	

	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		scroll = new JScrollPane(area);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (String) obj;
		if(data==null) {area.setText("");return;}
		
		area.setText(data);
		area.setCaretPosition(0);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return area;
		if(key.equals("keys")) return new String[]{"comp"};
		throw new Exception("Unknown key: "+key);
	}
}
