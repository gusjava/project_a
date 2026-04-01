package a.entity.gus06.sys.dirsearch1.gui.mode;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, V {

	public String creationDate() {return "20200315";}


	private JPanel panel;
	
	private Object modeManager;
	
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("modeManager")) {modeManager = obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
}
