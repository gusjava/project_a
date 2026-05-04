package a.entity.gus.y.desktop1.item.frame.cust;

import a.framework.*;
import javax.swing.JInternalFrame;
import java.util.Map;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191122";}
	
	public static final String KEY_CLOSEABLE = "closeable";
	public static final String KEY_RESIZABLE = "resizable";
	public static final String KEY_MAXIMIZABLE = "maximizable";
	public static final String KEY_ICONIFIABLE = "iconifiable";
	public static final String KEY_DRAGGABLE = "draggable";
	public static final String KEY_AUTOPACK = "autopack";
	public static final String KEY_UNDECORATED = "undecorated";
	public static final String KEY_NOBORDER = "noborder";
	public static final String KEY_DISPLAY = "display";
	public static final String KEY_FOREGROUND = "foreground";
	public static final String KEY_BACKGROUND = "background";
	public static final String KEY_OPAQUE = "opaque";


	private Service setDraggable;
	private Service custDisplay;
	private Service autoPack;
	private Service findColor;
	private Service setForeground;
	private Service setBackground;
	private Service setOpaque;

	public EntityImpl() throws Exception
	{
		setDraggable = Outside.service(this,"gus06.swing.comp.cust.dragframe.internal");
		custDisplay = Outside.service(this,"gus06.swing.internalframe.cust2.display");
		autoPack = Outside.service(this,"gus06.swing.internalframe.autopack");
		findColor = Outside.service(this,"gus06.find.color");
		setForeground = Outside.service(this,"gus.x.swing.comp.cust3.foreground.full");
		setBackground = Outside.service(this,"gus.x.swing.comp.cust3.background.full");
		setOpaque = Outside.service(this,"gus06.swing.comp.cust.opaque.full");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JInternalFrame frame = (JInternalFrame) o[0];
		Map def = (Map) o[1];
		
		frame.setClosable(getBool(def,KEY_CLOSEABLE,false));
		frame.setResizable(getBool(def,KEY_RESIZABLE,true));
		frame.setMaximizable(getBool(def,KEY_MAXIMIZABLE,false));
		frame.setIconifiable(getBool(def,KEY_ICONIFIABLE,false));
		
		if(getBool(def,KEY_DRAGGABLE,false))
		setDraggable.p(frame.getContentPane());
		
		if(getBool(def,KEY_AUTOPACK,false))
		autoPack.p(frame);
		
		if(getBool(def,KEY_UNDECORATED,false))
		((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
		
		if(getBool(def,KEY_NOBORDER,false))
		frame.setBorder(BorderFactory.createEmptyBorder());
		
		String display = get(def,KEY_DISPLAY);
		custDisplay.v(display,frame);
		
		String foreground = get(def,KEY_FOREGROUND);
		if(foreground!=null) setForeground.p(new Object[]{frame,findColor.t(foreground)});
		
		String background = get(def,KEY_BACKGROUND);
		if(background!=null) setBackground.p(new Object[]{frame,findColor.t(background)});
		
		if(getBool(def,KEY_OPAQUE,false))
		setOpaque.p(frame);
	}
	
	
	
	private boolean getBool(Map map, String key, boolean defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key).equals("true");
	}
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
