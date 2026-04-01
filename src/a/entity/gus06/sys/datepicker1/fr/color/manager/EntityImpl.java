package a.entity.gus06.sys.datepicker1.fr.color.manager;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20160617";}

	private Color COLOR_TODAY = Color.ORANGE;
	private Color COLOR_INVALID = Color.LIGHT_GRAY;
	private Color COLOR_WEEKEND = new Color(117,177,54);
	private Color COLOR_SELECTED = new Color(0,153,204);

	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("today")) return COLOR_TODAY;
		if(key.equals("weekend")) return COLOR_WEEKEND;
		if(key.equals("invalid")) return COLOR_INVALID;
		if(key.equals("selected")) return COLOR_SELECTED;
		
		if(key.equals("keys")) return new String[]{"today","weekend","invalid","selected"};
		
		return null;
	}
}
