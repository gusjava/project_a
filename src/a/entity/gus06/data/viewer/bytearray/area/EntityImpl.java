package a.entity.gus06.data.viewer.bytearray.area;

import a.framework.*;
import javax.swing.*;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20191007";}


	private Service toString;


	private byte[] data;

	private JTextArea area;
	private JScrollPane scroll;
	

	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.bytetohexa.display");
		
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
		data = (byte[]) obj;
		if(data==null) {area.setText("");return;}
		
		String display = (String) toString.t(data);
		area.setText("array length: "+data.length+"\n"+display);
		area.setCaretPosition(0);
	}
}
