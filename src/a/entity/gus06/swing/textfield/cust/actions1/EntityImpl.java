package a.entity.gus06.swing.textfield.cust.actions1;

import a.framework.*;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220601";}


	private Service custCtrlC;
	private Service custCtrlV;
	private Service custCtrlX;
	private Service custCtrlQ;
	private Service buildUndoManager;

	public EntityImpl() throws Exception
	{
		custCtrlC = Outside.service(this,"gus06.swing.textfield.cust.action.ctrl_c.copy");
		custCtrlV = Outside.service(this,"gus06.swing.textfield.cust.action.ctrl_v.paste");
		custCtrlX = Outside.service(this,"gus06.swing.textfield.cust.action.ctrl_x.cut");
		custCtrlQ = Outside.service(this,"gus06.swing.textfield.cust.action.ctrl_q.keepselection");
		buildUndoManager = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextField field = (JTextField) obj;
		
		custCtrlC.p(field);
		custCtrlV.p(field);
		custCtrlX.p(field);
		custCtrlQ.p(field);
		buildUndoManager.p(field);
	}
}