package a.entity.gus06.swing.textarea.buildtagbrowser2.debug.displayinfos;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201212";}


	private Service onF9;
	private Service showData;


	public EntityImpl() throws Exception
	{
		onF9 = Outside.service(this,"gus06.swing.comp.cust3.execute.f9");
		showData = Outside.service(this,"gus06.swing.frame.show.data");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTree tree = (JTree) o[0];
		JTextComponent comp = (JTextComponent) o[1];
		
		E execute = new Execute(tree);
		onF9.p(new Object[]{comp,execute});
	}
	
	
	private class Execute implements E
	{
		private JTree tree;
		
		public Execute(JTree tree)
		{this.tree = tree;}
		
		public void e() throws Exception
		{
			List positions = (List) ((R)tree).r("positions");
			List tags = (List) ((R)tree).r("tags");
			List tails = (List) ((R)tree).r("tails");
			List paths = (List) ((R)tree).r("paths");
			String head = (String) ((R)tree).r("head");
			
			Map m = new HashMap();
			m.put("positions",positions);
			m.put("tags",tags);
			m.put("tails",tails);
			m.put("paths",paths);
			m.put("head",head);
			
			showData.p(m);
		}
	}
}