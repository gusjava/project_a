package a.entity.gus06.sys.expression1.apply.op._tray_icon;

import a.framework.*;
import java.util.Map;
import javax.swing.Icon;
import java.awt.TrayIcon;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161003";}

	public final static String KEY_TOOLTIP = "tooltip";
	public final static String KEY_ICON = "icon";
	
	
	private Service iconToImage;
	
	public EntityImpl() throws Exception
	{
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Icon) return build((Icon) obj);
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private TrayIcon build(Map map) throws Exception
	{
		String tooltip = (String) get(map,KEY_TOOLTIP);
		Icon icon = (Icon) get(map,KEY_ICON);
		if(icon==null) throw new Exception("Null icon for TrayIcon creation");
		
		Image image = (Image) iconToImage.t(icon);
		
		TrayIcon trayIcon = new TrayIcon(image);
		if(tooltip!=null) trayIcon.setToolTip(tooltip);
		return trayIcon;
	}
	
	private TrayIcon build(Icon icon) throws Exception
	{
		Image image = (Image) iconToImage.t(icon);
		return new TrayIcon(image);
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
