package a.entity.gus.y.swingactions1.custcomp1;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	private Service ctrlC;
	private Service ctrlD;
	private Service ctrlE;
	private Service ctrlF;
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

	public EntityImpl() throws Exception {
		ctrlC = Outside.service(this,"gus.y.swingactions1.ctrl_c.copy");
		ctrlD = Outside.service(this,"gus.y.swingactions1.ctrl_d.removeline");
		ctrlE = Outside.service(this,"gus.y.swingactions1.ctrl_e.doubleline");
		ctrlF = Outside.service(this,"gus.y.swingactions1.ctrl_f.search");
		ctrlT = Outside.service(this,"gus.y.swingactions1.ctrl_t.truncate");
		ctrlW = Outside.service(this,"gus.y.quickreplace1.t1.action.ctrl_w");
		ctrlX = Outside.service(this,"gus.y.swingactions1.ctrl_x.cut");
		ctrlSpace = Outside.service(this,"gus.y.swingactions1.ctrl_space.enlargeselection");
		
		ctrlDown = Outside.service(this,"gus.y.swingactions1.ctrl_down.select.after");
		ctrlUp = Outside.service(this,"gus.y.swingactions1.ctrl_up.select.before");
		ctrlRight = Outside.service(this,"gus.y.swingactions1.ctrl_right.select.forward");
		ctrlLeft = Outside.service(this,"gus.y.swingactions1.ctrl_left.select.back");
		
		altDown = Outside.service(this,"gus.y.swingactions1.alt_down.gotodown");
		altUp = Outside.service(this,"gus.y.swingactions1.alt_up.gotoup");
		altRight = Outside.service(this,"gus.y.swingactions1.alt_right.gotoright");
		altLeft = Outside.service(this,"gus.y.swingactions1.alt_left.gotoleft");
		altC = Outside.service(this,"gus.y.swingactions1.alt_c.viewselect");
		
		ctrlShiftW = Outside.service(this,"gus.y.quickreplace1.t2.action.ctrl_shift_w");
	}
	
	public void p(Object obj) throws Exception {
		ctrlC.p(obj);
		ctrlD.p(obj);
		ctrlE.p(obj);
		ctrlF.p(obj);
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
