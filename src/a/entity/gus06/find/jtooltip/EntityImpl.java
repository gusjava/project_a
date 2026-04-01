package a.entity.gus06.find.jtooltip;

import a.framework.*;
import java.util.Map;
import javax.swing.JToolTip;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220610";}


	private Service iconToJToolTip;
	private Service imageToJToolTip;
	private Service colorToJToolTip;

	
	public EntityImpl() throws Exception
	{
		iconToJToolTip = Outside.service(this,"gus06.convert.icontojtooltip");
		imageToJToolTip = Outside.service(this,"gus06.convert.imagetojtooltip");
		colorToJToolTip = Outside.service(this,"gus06.convert.colortojtooltip");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Number) return build(""+obj);
		if(obj instanceof String) return build((String) obj);
		if(obj instanceof Font) return build((Font) obj);
		if(obj instanceof Color) return colorToJToolTip.t(obj);
		if(obj instanceof Icon) return iconToJToolTip.t(obj);
		if(obj instanceof Image) return imageToJToolTip.t(obj);
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JToolTip build(String display) throws Exception
	{
		JToolTip tip = new JToolTip();
		tip.setTipText(display);
		//TODO handle display = iconId#text
		return tip;
	}
	
	private JToolTip build(Font font) throws Exception
	{
		JToolTip tip = new JToolTip();
		tip.setFont(font);
		return tip;
	}
	
	private JToolTip build(Map map) throws Exception
	{
		JToolTip tip = new JToolTip();
		return tip;
	}
}