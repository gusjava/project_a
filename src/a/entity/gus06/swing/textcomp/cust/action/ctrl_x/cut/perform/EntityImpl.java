package a.entity.gus06.swing.textcomp.cust.action.ctrl_x.cut.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160427";}

	public static final String KEY_HANDLER = "ctrl_x_handler";


	private Service selectP0;
	private Service applyToComp;

	public EntityImpl() throws Exception
	{
		selectP0 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p0");
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
	}
	
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(!hasSelection(comp)) selectP0.p(comp);
		
		boolean applied = applyToComp.f(new Object[]{comp,null,KEY_HANDLER});
		if(!applied) comp.cut();
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}