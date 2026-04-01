package a.entity.gus06.data.editor.string.textfield.editor1;

import a.framework.*;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl extends S1 implements Entity, I, G, P, V, ActionListener {

	public String creationDate() {return "20140724";}


	private Service textChanged;
	private Service actionClear;
	private Service actionEnter;
	private Service custField;

	private JTextField field;
	private E onCleared;
	private E onEntered;

	public EntityImpl() throws Exception
	{
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		actionClear = Outside.service(this,"gus06.swing.textcomp.cust.action.escap.clear2");
		actionEnter = Outside.service(this,"gus06.swing.textfield.cust.action.enter");
		custField = Outside.service(this,"gus06.swing.textfield.cust.actions1");

		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));

		E cleared = this::performOnCleared;
		actionClear.p(new Object[]{field, cleared});
		
		E entered = this::performOnEntered;
		actionEnter.p(new Object[]{field, entered});
		
		custField.p(field);

		S sup = (S) textChanged.t(field);
		sup.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return field.getText();}
	
	
	public Object i() throws Exception
	{return field;}
	
	
	public void p(Object obj) throws Exception
	{field.setText(obj==null?"":(String) obj);}


	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("onCleared")) {onCleared = (E) obj;return;}
		if(key.equals("onEntered")) {onEntered = (E) obj;return;}
		throw new Exception("Unknown key: "+key);
	}


	public void actionPerformed(ActionEvent e)
	{changed();}


	private void changed()
	{send(this,"changed()");}


	private void cleared()
	{send(this,"cleared()");}


	private void entered()
	{send(this,"entered()");}
	
	
	private void performOnCleared()
	{
		try
		{
			if(onCleared!=null) onCleared.e();
			cleared();
		}
		catch(Exception e)
		{Outside.err(this,"performOnCleared()",e);}
	}
	
	private void performOnEntered()
	{
		try
		{
			if(onEntered!=null) onEntered.e();
			entered();
		}
		catch(Exception e)
		{Outside.err(this,"performOnEntered()",e);}
	}
}