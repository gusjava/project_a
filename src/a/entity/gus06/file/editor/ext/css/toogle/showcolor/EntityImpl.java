package a.entity.gus06.file.editor.ext.css.toogle.showcolor;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import javax.swing.text.Highlighter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221206";}
	
	public static final String DISPLAY = "ACTION_showColors#Show colors";

	private Service toggleBuilder;
	private Service perform;
	private Service removeHigh;

	public EntityImpl() throws Exception
	{
		toggleBuilder = Outside.service(this,"gus06.swing.button.toggleaction.builder0");
		perform = Outside.service(this,"gus06.sys.autocomplete1.highlighter1.reg.colors");
		removeHigh = Outside.service(this,"gus06.swing.textcomp.highlight.remove.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		Holder holder = new Holder(comp);
		E executeOn = holder::on;
		E executeOff = holder::off;
		
		return toggleBuilder.t(new Object[]{DISPLAY,executeOn,executeOff});
	}
	
	
	private class Holder
	{
		private JTextComponent comp;
		private List painters;
		
		public Holder(JTextComponent comp)
		{this.comp = comp;}
		
		public void on() throws Exception
		{
			if(painters!=null) off();
			painters = (List) perform.t(comp);
		}
		
		public void off() throws Exception
		{
			if(painters==null) return;
			
			for(int i=0;i<painters.size();i++)
			removeHigh.p(new Object[]{comp,painters.get(i)});
			painters = null;
		}
	}
}