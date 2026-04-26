package a.entity.gus06.swing.textcomp.build.action.copyall;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201120";}

	public static final String DISPLAY = "UTIL_clipboard#Copy all";
	
	
	private Service buildAction;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		perform = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public Object t(Object obj) throws Exception
	{
		Execute exe = new Execute((JTextComponent) obj);
		return buildAction.t(new Object[]{DISPLAY,exe});
	}

	private class Execute implements E
	{
		private JTextComponent comp;
		public Execute(JTextComponent comp) {this.comp = comp;}
		
		public void e() throws Exception
		{perform.p(comp.getText());}
	}
}