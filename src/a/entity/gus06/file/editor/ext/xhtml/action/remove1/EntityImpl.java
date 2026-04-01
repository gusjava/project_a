package a.entity.gus06.file.editor.ext.xhtml.action.remove1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170313";}
	
	public static final String DISPLAY = "TEXT_removeLine#Remove tag";


	private Service perform;
	private Service transformText;
	private Service buildAction;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.xhtml.remove1");
		transformText = Outside.service(this,"gus06.swing.textcomp.perform3.transformtext");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Holder holder = new Holder(comp);
		
		return buildAction.t(new Object[]{DISPLAY,holder});
	}
	
	
	private void perform(JTextComponent comp)
	{
		try
		{
			transformText.p(new Object[]{comp,perform});
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
	
	
	
	
	private class Holder implements E, Runnable
	{
		private JTextComponent comp;
		public Holder(JTextComponent comp)
		{this.comp = comp;}
		
		public void e() throws Exception
		{SwingUtilities.invokeLater(this);}
		
		public void run()
		{perform(comp);}
	}
}
