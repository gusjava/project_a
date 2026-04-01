package a.entity.gus06.swing.textarea.buildpanel1.custcomp;

import a.framework.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150425";}


	private Service execute; //b
	private Service removeLine; //d
	private Service doubleLine; //e
	private Service search; //f
	private Service regex; //g
	private Service tool; //h
	private Service repeat; //k
	private Service wrapLine; //p
	private Service undo; //yz
	
	private Service enlarge;
	
	private Service nextHigh;
	private Service previousHigh;
	
	private Service increaseFont;
	private Service decreaseFont;
	


	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute");
		removeLine = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline");
		doubleLine = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_e.doubleline");
		search = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_f.search");
		regex = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_g.regex");
		tool = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool");
		repeat = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_k.repeat");
		wrapLine = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_p.wrapline");
		undo = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		
		enlarge = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection");
		
		nextHigh = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_right.select.forward");
		previousHigh = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_left.select.back");
		
		increaseFont = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_plus.increasefontsize");
		decreaseFont = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_minus.decreasefontsize");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTextArea comp = (JTextArea) obj;
		
		execute.p(comp);
		removeLine.p(comp);
		doubleLine.p(comp);
		search.p(comp);
		regex.p(comp);
		tool.p(comp);
		repeat.p(comp);
		wrapLine.p(comp);
		undo.p(comp);
		enlarge.p(comp);
		nextHigh.p(comp);
		previousHigh.p(comp);
		increaseFont.p(comp);
		decreaseFont.p(comp);
	}
}