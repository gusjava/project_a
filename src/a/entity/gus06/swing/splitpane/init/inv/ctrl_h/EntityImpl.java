package a.entity.gus06.swing.splitpane.init.inv.ctrl_h;

import a.framework.*;
import javax.swing.JSplitPane;
import java.util.List;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20221024";}

	public static final String KEY = "ctrl h";

	private Service perform;
	private Service onKey;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.splitpane.inv");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length<2) throw new Exception("Wrong data number: "+o.length);
		
		JSplitPane split = (JSplitPane) o[0];
		Holder holder = new Holder(split);
		
		for(int i=1;i<o.length;i++) handle(o[i],holder);
	}
	
	private void handle(Object obj, E holder) throws Exception
	{
		if(obj instanceof List) 
			handleList((List) obj, holder);
		else if(obj instanceof JComponent) 
			handleComp((JComponent) obj, holder);
		else if(obj instanceof I) 
			handleComp((JComponent) ((I)obj).i(), holder);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void handleList(List list, E holder) throws Exception
	{
		for(int i=0;i<list.size();i++)
		handleComp((JComponent) list.get(i), holder);
	}
	
	private void handleComp(JComponent comp, E holder) throws Exception
	{
		onKey.p(new Object[]{comp,KEY,holder});
	}
	
	private class Holder implements E
	{
		private JSplitPane split;
		public Holder(JSplitPane split) {this.split = split;}
		
		public void e() throws Exception
		{perform.p(split);}
	}
}