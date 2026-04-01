package a.entity.gus06.data.viewer.tostring;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20230401";}


	private Object data;

	private JTextArea area;
	

	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return area;}
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		if(data==null) {area.setText("");return;}
		area.setText(data.toString());
	}
}