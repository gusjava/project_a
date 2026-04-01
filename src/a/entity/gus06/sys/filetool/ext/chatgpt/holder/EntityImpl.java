package a.entity.gus06.sys.filetool.ext.chatgpt.holder;

import a.framework.*;
import java.io.File;
import java.util.Map;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250531";}

	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	

	private Service gui1;
	private Service gui2;
	private Service tab;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.sys.chatgpt.gui1");
		gui2 = Outside.service(this,"*gus06.sys.chatgpt.gui2");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		tab.v("Ask", gui1);
		tab.v("Translate", gui2);
	}
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		String model = (String) map.get(KEY_MODEL);
		String apikey = (String) map.get(KEY_APIKEY);
		
		gui1.v("model", model);
		gui1.v("apikey", apikey);
		
		gui2.v("model", model);
		gui2.v("apikey", apikey);
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
}