package a.entity.gus06.swing.comp.cust2.display;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20170806";}


	private Service performLabel;
	private Service performButton;
	private Service performFrame;
	
	
	public EntityImpl() throws Exception
	{
		performLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		performButton = Outside.service(this,"gus06.swing.button.cust2.display");
		performFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj instanceof JLabel)		{performLabel.v(key,obj);return;}
		if(obj instanceof AbstractButton)	{performButton.v(key,obj);return;}
		if(obj instanceof JFrame)		{performFrame.v(key,obj);return;}
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
