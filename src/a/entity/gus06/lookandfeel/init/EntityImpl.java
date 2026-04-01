package a.entity.gus06.lookandfeel.init;

import a.framework.*;
import java.util.Map;
import javax.swing.UIManager;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140918";}

	public static final String KEY_LAF = "app.laf";

	private Map prop;

	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		
		if(!prop.containsKey(KEY_LAF)) return;
		String value = (String) prop.get(KEY_LAF);
		
		if(prop.containsKey("laf."+value))
			value = (String) prop.get("laf."+value);
		
		UIManager.setLookAndFeel(value);
	}
}
