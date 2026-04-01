package a.entity.gus06.sys.webserver1.main;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20140928";}


	private Service engine;
	private Service process;
	private Service pooler;


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.webserver1.main.engine");
		process = Outside.service(this,"gus06.sys.webserver1.main.process");
		pooler = Outside.service(this,"*gus06.thread.pooler.pobj.pool5");
		
		
		pooler.v("handler",new P() {
			public void p(Object obj){poolerToEngine(obj);}
		});
		
		engine.addActionListener(this);
		engine.e();
	}
	
	
	

	public void actionPerformed(ActionEvent e)
	{engineToPooler();}
	
	
	
	
	private void engineToPooler()
	{
		try
		{
			Object input = engine.g();
			pooler.p(input);
		}
		catch(Exception e)
		{Outside.err(this,"engineToPooler()",e);}
	}
	
	
	private void poolerToEngine(Object obj)
	{
		try
		{
			obj = process.t(obj);
			engine.p(obj);
		}
		catch(Exception e)
		{Outside.err(this,"poolerToEngine(Object)",e);}
	}
}
