package a.entity.gus06.sys.desktop1.execute.item.remove;

import a.framework.*;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260113";}

	public static final String KEY_GUI_PANE = "gui_pane";
	public static final String KEY_ITEM_MANAGER = "item_manager";
	
	
	
	public void p(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		JDesktopPane pane = (JDesktopPane) main.get(KEY_GUI_PANE);
		V manager = (V) main.get(KEY_ITEM_MANAGER);
		
		manager.v("remove",null);
	}
}
