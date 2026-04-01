package a.entity.gus06.data.viewer.string.textarea.console1.black;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;


public class EntityImpl implements Entity, I, G, P {

	public String creationDate() {return "20160331";}


	private Service custArea;

	private JTextArea area;

	public EntityImpl() throws Exception
	{
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");

		area = new JTextArea();
		custArea.p(area);
	}
	
	
	public Object g() throws Exception
	{return area.getText();}
	
	
	public Object i() throws Exception
	{return area;}
	
	
	public void p(Object obj) throws Exception
	{area.setText(obj==null?"":(String) obj);}
}
