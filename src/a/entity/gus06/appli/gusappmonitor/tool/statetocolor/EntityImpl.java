package a.entity.gus06.appli.gusappmonitor.tool.statetocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190414";}
	
	public static final String STATE_CONNECTED = "connected";
	public static final String STATE_CLOSED = "closed";
	public static final String STATE_LOST = "lost";
	
	
	public Object t(Object obj) throws Exception
	{
		String state = (String) obj;
		if(state==null) return Color.BLACK;
		
		if(state.equals(STATE_CONNECTED)) return Color.BLUE;
		if(state.equals(STATE_CLOSED)) return Color.GRAY;
		if(state.equals(STATE_LOST)) return Color.RED;
		return Color.BLACK;
	}
}
