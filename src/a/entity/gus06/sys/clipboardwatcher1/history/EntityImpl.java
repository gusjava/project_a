package a.entity.gus06.sys.clipboardwatcher1.history;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, G, ActionListener {

	public String creationDate() {return "20200326";}
	
	public static final int LIMIT = 15;


	private Service engine;
	private List list;


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.clipboardwatcher1.engine");
		list = new ArrayList();
		engine.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e)
	{clipboardChanged();}
	
	
	private void clipboardChanged()
	{
		try
		{
			String s = (String) engine.g();
			if(s==null) return;
			
			list.remove(s);
			list.add(s);
			while(list.size()>LIMIT) list.remove(0);
			historyChanged();
		}
		catch(Exception e)
		{Outside.err(this,"clipboardChanged()",e);}
	}

	public Object g() throws Exception
	{return list;}
	
	
	private void historyChanged()
	{send(this,"historyChanged()");}
}
