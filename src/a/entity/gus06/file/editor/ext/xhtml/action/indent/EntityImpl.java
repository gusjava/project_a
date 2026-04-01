package a.entity.gus06.file.editor.ext.xhtml.action.indent;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170228";}
	
	public static final String DISPLAY = "TEXT_indent#Indent code";


	private Service transform1;
	private Service transform2;
	private Service transformText;
	private Service buildAction;


	public EntityImpl() throws Exception
	{
		transform1 = Outside.service(this,"gus06.string.transform.xhtml.indent");
		transform2 = Outside.service(this,"gus06.string.transform.xhtml.indent.space");
		transformText = Outside.service(this,"gus06.swing.textcomp.perform3.transformtext");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Holder holder = new Holder(comp);
		
		return buildAction.t(new Object[]{DISPLAY,holder});
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
	
	
	private void perform(JTextComponent comp)
	{
		try
		{
			boolean containsTab = comp.getText().contains("\t");
			T transform = containsTab ? transform1 : transform2;
			transformText.p(new Object[]{comp,transform});
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
}