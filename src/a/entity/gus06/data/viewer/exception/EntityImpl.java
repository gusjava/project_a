package a.entity.gus06.data.viewer.exception;

import a.framework.*;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Service exceptionToString;

	private Exception data;

	private JTextArea area;
	private JScrollPane scroll;
	

	public EntityImpl() throws Exception
	{
		exceptionToString = Outside.service(this,"gus06.tostring.exception.ste");
		
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
		data = (Exception) obj;
		if(data==null) {area.setText("");return;}
		
		String s = (String) exceptionToString.t(data);
		area.setText(s);
		area.setCaretPosition(0);
	}
}