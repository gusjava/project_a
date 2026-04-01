package a.entity.gus06.y.swingactions1.custcomp1;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251116";}

	private Service ctrlC;
	private Service ctrlD;
	private Service ctrlE;
	private Service ctrlF;
	private Service ctrlH;
	private Service ctrlT;
	private Service ctrlW;
	private Service ctrlX;
	private Service ctrlSpace;

	private Service ctrlDown;
	private Service ctrlUp;
	private Service ctrlRight;
	private Service ctrlLeft;

	private Service altDown;
	private Service altUp;
	private Service altRight;
	private Service altLeft;
	private Service altC;
	
	private Service ctrlShiftW;

	public EntityImpl() throws Exception
	{
		ctrlC = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_c.copy");
		ctrlD = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline");
		ctrlE = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_e.doubleline");
		ctrlF = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_f.search");
		ctrlH = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool");
		ctrlT = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_t.truncate");
		ctrlW = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_w.quickreplace");
		ctrlX = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_x.cut");
		ctrlSpace = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection");
		
		ctrlDown = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_down.select.after");
		ctrlUp = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_up.select.before");
		ctrlRight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_right.select.forward");
		ctrlLeft = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_left.select.back");
		
		altDown = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_down.gotodown");
		altUp = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_up.gotoup");
		altRight = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_right.gotoright");
		altLeft = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft");
		altC = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_c.showoff");
		
		ctrlShiftW = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_w.smartreplace");
	}
	
	public void p(Object obj) throws Exception
	{
		ctrlC.p(obj);
		ctrlD.p(obj);
		ctrlE.p(obj);
		ctrlF.p(obj);
		ctrlH.p(obj);
		ctrlT.p(obj);
		ctrlW.p(obj);
		ctrlX.p(obj);
		ctrlSpace.p(obj);

		ctrlDown.p(obj);
		ctrlUp.p(obj);
		ctrlRight.p(obj);
		ctrlLeft.p(obj);

		altDown.p(obj);
		altUp.p(obj);
		altRight.p(obj);
		altLeft.p(obj);
		altC.p(obj);
		
		ctrlShiftW.p(obj);
	}
}