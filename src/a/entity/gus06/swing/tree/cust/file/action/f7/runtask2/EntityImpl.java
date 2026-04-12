package a.entity.gus06.swing.tree.cust.file.action.f7.runtask2;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;
import javax.swing.JTree;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20251212";}

	public static final String DEFAULT_KEY = "shift F7";
	
	
	private Service perform;
	private Service manageKeyStroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.tree.perform.file.runtask2");
		manageKeyStroke = Outside.service(this,"gus06.sys.keystroke1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JTree) obj);}



	private class Holder extends AbstractAction implements E
	{
		private JTree comp;
		public Holder(JTree comp) throws Exception
		{
			this.comp = comp;
			manageKeyStroke.p(new Object[]{id(),DEFAULT_KEY,comp,this});
		}
		public void actionPerformed(ActionEvent e)
		{perform(comp);}
		
		public void e() throws Exception
		{perform(comp);}
	}
	
	
	private void perform(JTree comp)
	{
		try{perform.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(JTree)",e);}
	}
	
	private String id()
	{return getClass().getName();}
}