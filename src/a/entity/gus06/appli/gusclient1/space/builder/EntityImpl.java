package a.entity.gus06.appli.gusclient1.space.builder;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140718";}

	
	private Service findEntityName;
	private Service guiBuilder;
	private Service errGuiBuilder;
	
	private Map cache;
	

	public EntityImpl() throws Exception
	{
		findEntityName = Outside.service(this,"gus06.appli.gusclient1.space.findentity");
		guiBuilder = Outside.service(this,"gus06.sys.async.guibuilder");
		errGuiBuilder = Outside.service(this,"errguibuilder");
		cache = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		if(!cache.containsKey(id))
			cache.put(id,build(id));
		return cache.get(id);
	}
	
	
	
	private Object build(String id) throws Exception
	{
		try
		{
			String entityName = (String) findEntityName.t(id);
			return guiBuilder.t(entityName);
		}
		catch(Exception e)
		{
			Outside.err(this,"build(String)",e);
			return errPanel(id,e);
		}
	}
	
	
	
	
	
	private JPanel errPanel(String id, Exception e) throws Exception
	{
		JComponent errComp = (JComponent) errGuiBuilder.t(e);
		JLabel titleLabel = new JLabel("Argh... Space "+id+" could not be initialized");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(titleLabel,BorderLayout.NORTH);
		p.add(errComp,BorderLayout.CENTER);
		return p;
	}
}
