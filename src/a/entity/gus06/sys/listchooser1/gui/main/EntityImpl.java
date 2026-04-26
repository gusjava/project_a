package a.entity.gus06.sys.listchooser1.gui.main;

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

	public String creationDate() {return "20161209";}
	
	
	private Service selector;
	private Service shiftPanel;
	private Service custSplit;
	private Service clipboard;
	
	private JSplitPane split;
	private Object annexe;
	
	private P handleF1;
	private P handleF2;
	private P handleF3;
	private P handleF4;
	private P handleF5;
	private P handleF6;
	private P handleF7;
	private P handleF8;
	private P handleF9;
	private P handleF10;
	private P handleF11;
	private P handleF12;
	
	private P handleSpace;
	private P handleDelete;
	private P handleEnter;
	private P handleEscape;
	
	private P handleCtrlC;
	private P handleCtrlV;
	private P handleCtrlX;


	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		selector = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		clipboard = Outside.service(this,"gus06.clipboard.access");
		
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
		if(key.equals("select")) {selector.v("select",obj);return;}
		if(key.equals("search")) {selector.v("search",obj);return;}
		if(key.equals("mode")) {selector.v("mode",obj);return;}
		if(key.equals("filter")) {selector.v("filter",obj);return;}
		
		if(key.equals("annexe")) {setAnnexe(obj);return;}
		if(key.equals("ctrl_c")) {setCtrlC(obj);return;}
		
		if(key.equals("f1")) {this.handleF1 = (P)obj;return;}
		if(key.equals("f2")) {this.handleF2 = (P)obj;return;}
		if(key.equals("f3")) {this.handleF3 = (P)obj;return;}
		if(key.equals("f4")) {this.handleF4 = (P)obj;return;}
		if(key.equals("f5")) {this.handleF5 = (P)obj;return;}
		if(key.equals("f6")) {this.handleF6 = (P)obj;return;}
		if(key.equals("f7")) {this.handleF7 = (P)obj;return;}
		if(key.equals("f8")) {this.handleF8 = (P)obj;return;}
		if(key.equals("f9")) {this.handleF9 = (P)obj;return;}
		if(key.equals("f10")) {this.handleF10 = (P)obj;return;}
		if(key.equals("f11")) {this.handleF11 = (P)obj;return;}
		if(key.equals("f12")) {this.handleF12 = (P)obj;return;}
		
		if(key.equals("space")) {this.handleSpace = (P)obj;return;}
		if(key.equals("delete")) {this.handleDelete = (P)obj;return;}
		if(key.equals("enter")) {this.handleEnter = (P)obj;return;}
		if(key.equals("escape")) {this.handleEscape = (P)obj;return;}
		
		if(key.equals("ctrl_v")) {this.handleCtrlV = (P)obj;return;}
		if(key.equals("ctrl_x")) {this.handleCtrlX = (P)obj;return;}
		
		throw new Exception("Unknown key: "+key);
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
	
	private void setCtrlC(Object ctrlC) throws Exception
	{
		if(ctrlC.equals("data"))
			this.handleCtrlC = clipboard;
		else if(ctrlC instanceof P)
			this.handleCtrlC = (P) ctrlC;
		else if(ctrlC instanceof T)
			this.handleCtrlC = new HandleCtrlCT((T) ctrlC);
		else throw new Exception("Invalid ctrlC type: "+ctrlC.getClass().getName());
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
		
		if(s.equals("typed_ctrl_c()")) {typed_ctrl_c();return;}
		if(s.equals("typed_ctrl_v()")) {typed_ctrl_v();return;}
		if(s.equals("typed_ctrl_x()")) {typed_ctrl_x();return;}
		
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
	
	
	
	private void handle(P handler)
	{
		try
		{
			if(handler==null) return;
			Object selected = selector.g();
			if(selected!=null) handler.p(selected);
		}
		catch(Exception e)
		{Outside.err(this,"handle(P)",e);}
	}
	
	
	
	
	private void typed_F1()
	{
		handle(handleF1);
		send(this,"typed_F1()");
	}
	
	private void typed_F2()
	{
		handle(handleF2);
		send(this,"typed_F2()");
	}
	
	private void typed_F3()
	{
		handle(handleF3);
		send(this,"typed_F3()");
	}
	
	private void typed_F4()
	{
		handle(handleF4);
		send(this,"typed_F4()");
	}
	
	private void typed_F5()
	{
		handle(handleF5);
		send(this,"typed_F5()");
	}
	
	private void typed_F6()
	{
		handle(handleF6);
		send(this,"typed_F6()");
	}
	
	private void typed_F7()
	{
		handle(handleF7);
		send(this,"typed_F7()");
	}
	
	private void typed_F8()
	{
		handle(handleF8);
		send(this,"typed_F8()");
	}
	
	private void typed_F9()
	{
		handle(handleF9);
		send(this,"typed_F9()");
	}
	
	private void typed_F10()
	{
		handle(handleF10);
		send(this,"typed_F10()");
	}
	
	private void typed_F11()
	{
		handle(handleF11);
		send(this,"typed_F11()");
	}
	
	private void typed_F12()
	{
		handle(handleF12);
		send(this,"typed_F12()");
	}
	
	private void typed_space()
	{
		handle(handleSpace);
		send(this,"typed_space()");
	}

	private void typed_delete()
	{
		handle(handleDelete);
		send(this,"typed_delete()");
	}

	private void typed_enter()
	{
		handle(handleEnter);
		send(this,"typed_enter()");
	}

	private void typed_escape()
	{
		handle(handleEscape);
		send(this,"typed_escape()");
	}

	private void typed_ctrl_c()
	{
		handle(handleCtrlC);
		send(this,"typed_ctrl_c()");
	}

	private void typed_ctrl_x()
	{
		handle(handleCtrlX);
		send(this,"typed_ctrl_x()");
	}

	private void typed_ctrl_v()
	{
		handle(handleCtrlV);
		send(this,"typed_ctrl_v()");
	}
	
	
	
	private class HandleCtrlCT implements P
	{
		private T t;
		public HandleCtrlCT(T t)
		{this.t = t;}
		
		public void p(Object obj) throws Exception
		{
			Object data = t.t(obj);
			clipboard.p(data);
		}
	}
}