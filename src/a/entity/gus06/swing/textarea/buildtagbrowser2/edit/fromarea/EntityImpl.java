package a.entity.gus06.swing.textarea.buildtagbrowser2.edit.fromarea;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201217";}


	private Service onF12;

	public EntityImpl() throws Exception
	{
		onF12 = Outside.service(this,"gus06.swing.comp.cust3.execute.f12");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTree tree = (JTree) o[0];
		JTextComponent comp = (JTextComponent) o[1];
		
		E execute = new Execute(tree);
		onF12.p(new Object[]{comp,execute});
	}
	
	
	private class Execute implements E
	{
		private JTree tree;
		
		public Execute(JTree tree)
		{this.tree = tree;}
		
		public void e() throws Exception
		{((P)tree).p("shiftEditMode");}
	}
}