package a.entity.gus06.swing.frame.persister.bounds;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20150609";}


	private Service manager;
	private Service frameToInfo;
	private Service infoToFrame;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.app.persister1.manager");
		frameToInfo = Outside.service(this,"gus06.swing.frame.frametobounds");
		infoToFrame = Outside.service(this,"gus06.swing.frame.cust2.bounds");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JFrame comp = (JFrame) obj;
		
		String text = (String) manager.r(key);
		if(text!=null && !text.equals("")) custFrame(text,comp);
		
		manager.v(key,new G(){
			public Object g() throws Exception
			{return frameToInfo.t(comp);}
		});
	}
	
	
	
	private void custFrame(String text, JFrame comp)
	{
		try{infoToFrame.v(text,comp);}
		catch(Exception e)
		{Outside.err(this,"custFrame(String,Frame)",e);}
	}
}
