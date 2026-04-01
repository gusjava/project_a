package a.entity.gus06.data.editor.string.textarea.console1.black;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;


public class EntityImpl extends S1 implements Entity, I, G, P, ActionListener {

	public String creationDate() {return "20160331";}


	private Service textChanged;
	private Service actionClear;
	private Service custArea;

	private JTextArea area;

	public EntityImpl() throws Exception
	{
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		actionClear = Outside.service(this,"gus06.swing.textcomp.cust.action.escap.clear");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");

		area = new JTextArea();
		custArea.p(area);
		
		actionClear.p(area);

		S sup = (S) textChanged.t(area);
		sup.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return area.getText();}
	
	
	public Object i() throws Exception
	{return area;}
	
	
	public void p(Object obj) throws Exception
	{area.setText(obj==null?"":(String) obj);}



	public void actionPerformed(ActionEvent e)
	{changed();}


	private void changed()
	{send(this,"changed()");}
}
