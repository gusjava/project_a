package a.entity.gus06.debug.init.displaygui;

import a.framework.*;
import javax.swing.JFrame;
import java.util.Map;
import java.util.Iterator;
import javax.swing.JComponent;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140919";}


	private Service subMap;
	private Service newEntity;


	public EntityImpl() throws Exception
	{
		subMap = Outside.service(this,"gus06.app.prop.submap.display");
		newEntity = Outside.service(this,"entitynew");
		
		Map map = (Map) subMap.g();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String id = (String) it.next();
			String name = (String) map.get(id);
			handle(id,name);
		}
	}
	
	
	private void handle(String id, String name)
	{
		try
		{
			I entity = (I) newEntity.t(name);
			
			JFrame frame = new JFrame(id);
			frame.setContentPane((JComponent) entity.i());
			frame.setSize(500,300);
			frame.setVisible(true);
			frame.setState(JFrame.ICONIFIED);
		}
		catch(Exception e)
		{Outside.err(this,"handle(String,String)",e);}
	}
}
