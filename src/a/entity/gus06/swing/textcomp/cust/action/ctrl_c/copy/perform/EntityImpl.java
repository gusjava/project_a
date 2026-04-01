package a.entity.gus06.swing.textcomp.cust.action.ctrl_c.copy.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160425";}

	public static final String KEY_HANDLER = "ctrl_c_handler";


	private Service enlargeSelection;
	private Service clipboardAccess;
	private Service applyToComp;

	public EntityImpl() throws Exception
	{
		enlargeSelection = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform");
		clipboardAccess = Outside.service(this,"gus06.clipboard.access.string");
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
	}
	
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		String selection = comp.getSelectedText();
		if(selection!=null && !selection.equals(""))
		{
			String content = (String) clipboardAccess.g();
			if(content!=null && content.equals(selection)) enlargeSelection.p(comp);
			
			perform2(comp);
			return;
		}
		
		enlargeSelection.p(comp);
		perform2(comp);
	}
	
	private void perform2(JTextComponent comp) throws Exception
	{
		String selection = comp.getSelectedText();
		boolean applied = applyToComp.f(new Object[]{comp,selection,KEY_HANDLER});
		if(!applied) clipboardAccess.p(selection);
	}
}