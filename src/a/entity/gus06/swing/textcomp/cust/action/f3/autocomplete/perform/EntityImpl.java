package a.entity.gus06.swing.textcomp.cust.action.f3.autocomplete.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231030";}

	public static final String KEY_HANDLER = "F3_handler";

	private Service applyToComp;
	
	public EntityImpl() throws Exception
	{
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
	}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	private void perform(JTextComponent comp) throws Exception
	{
		boolean applied = applyToComp.f(new Object[]{comp,null,KEY_HANDLER});
		if(applied) return;
		
		// empty until now
	}
}