package a.entity.gus06.swing.textcomp.cust.action.ctrl_b.execute.perform3;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220209";}


	private Service queueForPaste;
	
	
	public EntityImpl() throws Exception
	{
		queueForPaste = Outside.service(this,"gus06.sys.clipboard1.queueforpaste3");
	}
	
	public void p(Object obj) throws Exception
	{
		String text = (String) obj;
		
		String[] lines = text.split("\n");
		List list = new ArrayList();
		for(int i=1;i<lines.length;i++) list.add(lines[i]);
		queueForPaste.p(list);
	}
}