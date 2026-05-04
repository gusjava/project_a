package a.entity.gus.y.desktop1.gui.bar.cust;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191121";}
	
	public static final String KEY_PROP = "prop";
	public static final String KEY_GUI_BAR = "gui_bar";
	public static final String KEY_GUI_PANEL = "gui_panel";

	
	
	private Service buildActionFullScreen;
	private Service buildActionAddItem;
	private Service buildActionRemoveItem;
	private Service buildActionCustItem;
	
	public EntityImpl() throws Exception
	{
		buildActionFullScreen = Outside.service(this,"gus06.sys.fullscreen1.build.action");
		buildActionAddItem = Outside.service(this,"gus.y.desktop1.action.item.add");
		buildActionRemoveItem = Outside.service(this,"gus.y.desktop1.action.item.remove");
		buildActionCustItem = Outside.service(this,"gus.y.desktop1.action.item.customize");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		JToolBar bar = (JToolBar) main.get(KEY_GUI_BAR);
		JComponent panel = (JComponent) main.get(KEY_GUI_PANEL);
		
		Map prop = (Map) main.get(KEY_PROP);
		
		Action actionFullScreen = (Action) buildActionFullScreen.t(panel);
		Action actionAddItem = (Action) buildActionAddItem.t(main);
		Action actionRemoveItem = (Action) buildActionRemoveItem.t(main);
		Action actionCustItem = (Action) buildActionCustItem.t(main);
		
		bar.removeAll();
		
		bar.add(actionFullScreen);
		bar.addSeparator();
		bar.add(actionAddItem);
		bar.add(actionRemoveItem);
		bar.add(actionCustItem);
	}
}
