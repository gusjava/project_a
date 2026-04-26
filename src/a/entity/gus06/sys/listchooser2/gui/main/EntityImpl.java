package a.entity.gus06.sys.listchooser2.gui.main;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JSplitPane;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, V, R {

	public String creationDate() {return "20220613";}
	
	
	private Service selector;
	private Service shiftPanel;
	private Service custSplit;
	private Object annexe;
	private JSplitPane split;


	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		selector = Outside.service(this,"*gus06.sys.listchooser2.gui.selector1");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		selector.addActionListener(this);
		refreshGui();
		
		split = new JSplitPane();
		custSplit.p(split);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return selector.g();}
	
	
	
	private void refreshGui() throws Exception
	{
		JComponent selectorComp = (JComponent) selector.i();
		if(annexe!=null)
		{
			JComponent annexeComp = (JComponent) ((I) annexe).i();
			
			split.setLeftComponent(selectorComp);
			split.setRightComponent(annexeComp);
			
			((P) annexe).p(selector.g());
			shiftPanel.p(split);
		}
		else
		{
			shiftPanel.p(selectorComp);
		}
		
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		selector.p(obj);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("annexe")) {setAnnexe(obj);return;}
		selector.v(key,obj);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("annexe")) return annexe;
		if(key.equals("selector")) return selector;
		if(key.equals("keys")) return new String[]{"annexe","selector"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void setAnnexe(Object annexe) throws Exception
	{
		this.annexe = annexe;
		refreshGui();
	}



	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		
		if(s.equals("typed_F1()")) {typed_F1();return;}
		if(s.equals("typed_F2()")) {typed_F2();return;}
		if(s.equals("typed_F3()")) {typed_F3();return;}
		if(s.equals("typed_F4()")) {typed_F4();return;}
		if(s.equals("typed_F5()")) {typed_F5();return;}
		if(s.equals("typed_F6()")) {typed_F6();return;}
		if(s.equals("typed_F7()")) {typed_F7();return;}
		if(s.equals("typed_F8()")) {typed_F8();return;}
		if(s.equals("typed_F9()")) {typed_F9();return;}
		if(s.equals("typed_F10()")) {typed_F10();return;}
		if(s.equals("typed_F11()")) {typed_F11();return;}
		if(s.equals("typed_F12()")) {typed_F12();return;}
		if(s.equals("typed_space()")) {typed_space();return;}
		if(s.equals("typed_delete()")) {typed_delete();return;}
		if(s.equals("typed_enter()")) {typed_enter();return;}
		if(s.equals("typed_escape()")) {typed_escape();return;}
		
		if(s.equals("selectionChanged()")) {selectionChanged();return;}
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			if(annexe!=null)
			((P) annexe).p(selector.g());
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	
	private void typed_F1()
	{send(this,"typed_F1()");}
	
	private void typed_F2()
	{send(this,"typed_F2()");}
	
	private void typed_F3()
	{send(this,"typed_F3()");}
	
	private void typed_F4()
	{send(this,"typed_F4()");}
	
	private void typed_F5()
	{send(this,"typed_F5()");}
	
	private void typed_F6()
	{send(this,"typed_F6()");}
	
	private void typed_F7()
	{send(this,"typed_F7()");}
	
	private void typed_F8()
	{send(this,"typed_F8()");}
	
	private void typed_F9()
	{send(this,"typed_F9()");}
	
	private void typed_F10()
	{send(this,"typed_F10()");}
	
	private void typed_F11()
	{send(this,"typed_F11()");}
	
	private void typed_F12()
	{send(this,"typed_F12()");}
	
	private void typed_space()
	{send(this,"typed_space()");}

	private void typed_delete()
	{send(this,"typed_delete()");}

	private void typed_enter()
	{send(this,"typed_enter()");}

	private void typed_escape()
	{send(this,"typed_escape()");}
}