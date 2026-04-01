package a.entity.gus06.swing.textarea.holder2;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, R {

	public String creationDate() {return "20190307";}


	private Service factory;
	private Service undo;
	private Service delayed;
	
	private JTextComponent comp;
	private JScrollPane scroll;


	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory1");
		undo = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		delayed = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		
		comp = (JTextComponent) factory.i();
		comp.setMargin(new Insets(3,3,3,3));
		
		S sup = (S) delayed.t(comp);
		scroll = new JScrollPane(comp);
		
		undo.p(comp);
		
		sup.addActionListener(this);
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


	public void actionPerformed(ActionEvent e)
	{modified();}
	
	
	private void modified()
	{send(this,"modified()");}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("keys")) return new String[]{"comp"};
		
		throw new Exception("Unknown key: "+key);
	}
}
