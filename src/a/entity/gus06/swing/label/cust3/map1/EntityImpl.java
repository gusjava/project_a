package a.entity.gus06.swing.label.cust3.map1;

import a.framework.*;
import javax.swing.JLabel;
import java.util.Map;
import java.util.Iterator;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140912";}
	
	public final static String KEY_TEXT = "text";
	public final static String KEY_ICON = "icon";
	public final static String KEY_DISPLAY = "display";
	public final static String KEY_TOOLTIP = "tooltip";
	public final static String KEY_FONT = "font";
	public final static String KEY_FONTSIZE = "fontsize";
	public final static String KEY_FOREGROUND = "foreground";
	public final static String KEY_BACKGROUND = "background";
	public final static String KEY_OPAQUE = "opaque";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_BORDER = "border";
	public final static String KEY_ALIGNH = "alignh";
	public final static String KEY_ALIGNV = "alignv";
	public final static String KEY_TEXTH = "texth";
	public final static String KEY_TEXTV = "textv";
	public final static String KEY_REMOVABLE = "removable";


	private Service repaint;
	private Service findBorder;
	private Service findEmptyBorder;
	private Service setRemovable;
	private Service autoTooltip;


	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		findBorder = Outside.service(this,"gus06.find.border");
		findEmptyBorder = Outside.service(this,"gus06.find.emptyborder");
		setRemovable = Outside.service(this,"gus06.swing.comp.cust.removable");
		autoTooltip = Outside.service(this,"gus06.swing.label.cust.tooltip1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JLabel label = (JLabel) o[0];
		Map map = (Map) o[1];
		
		String display = (String) get(map,KEY_DISPLAY);
		String text = (String) get(map,KEY_TEXT);
		Icon icon = (Icon) get(map,KEY_ICON);
		String tooltip = (String) get(map,KEY_TOOLTIP);
		Font font = (Font) get(map,KEY_FONT);
		Integer fontsize = (Integer) get(map,KEY_FONTSIZE);
		Color foreground = (Color) get(map,KEY_FOREGROUND);
		Color background = (Color) get(map,KEY_BACKGROUND);
		Boolean opaque = (Boolean) get(map,KEY_OPAQUE);
		Object margin = get(map,KEY_MARGIN);
		Object border = get(map,KEY_BORDER);
		Object alignH = get(map,KEY_ALIGNH);
		Object alignV = get(map,KEY_ALIGNV);
		Object textH = get(map,KEY_TEXTH);
		Object textV = get(map,KEY_TEXTV);
		Boolean removable = (Boolean) get(map,KEY_REMOVABLE);
		
		if(display!=null)
		{
			repaint.v(display,label);
		}
		else
		{
			if(text!=null) label.setText(text);
			if(icon!=null) label.setIcon(icon);
		}
		
		if(font!=null)
		{
			label.setFont(font);
		}
		else
		{
			if(fontsize!=null) label.setFont(label.getFont().deriveFont(fontsize.floatValue()));
		}
		
		if(border!=null)
		{
			Border b = (Border) findBorder.t(border);
			label.setBorder(b);
		}
		else if(margin!=null)
		{
			EmptyBorder b = (EmptyBorder) findEmptyBorder.t(margin);
			label.setBorder(b);
		}
		
		if(alignH!=null)
		{
			int value = labelConst(alignH);
			label.setHorizontalAlignment(value);
		}
		
		if(alignV!=null)
		{
			int value = labelConst(alignV);
			label.setVerticalAlignment(value);
		}
		
		if(textH!=null)
		{
			int value = labelConst(textH);
			label.setHorizontalTextPosition(value);
		}
		
		if(textV!=null)
		{
			int value = labelConst(textV);
			label.setVerticalTextPosition(value);
		}
		
		if(tooltip!=null)
		{
			if(tooltip.equals("*")) autoTooltip.p(label);
			else label.setToolTipText(tooltip);
		}
		
		if(foreground!=null)
		{
			label.setForeground(foreground);
		}
		
		if(background!=null)
		{
			label.setBackground(background);
		}
		
		if(opaque!=null)
		{
			label.setOpaque(opaque.booleanValue());
		}
		if(removable!=null)
		{
			setRemovable(label,removable.booleanValue());
		}
	}
	
	
	
	
	
	private int labelConst(Object value) throws Exception
	{
		if(value instanceof Integer) return ((Integer) value).intValue();
		if(value instanceof String) return stringToLabelConst((String) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private int stringToLabelConst(String value) throws Exception
	{
		value = value.toLowerCase();
		if(value.equals("center")) return JLabel.CENTER;
		if(value.equals("right")) return JLabel.RIGHT;
		if(value.equals("left")) return JLabel.LEFT;
		if(value.equals("top")) return JLabel.TOP;
		if(value.equals("bottom")) return JLabel.BOTTOM;
		if(value.equals("leading")) return JLabel.LEADING;
		if(value.equals("trailing")) return JLabel.TRAILING;
		
		throw new Exception("Unknown value: "+value);
	}
	
	
	private int int_(String value)
	{return Integer.parseInt(value);}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private void setRemovable(JLabel label, boolean v) throws Exception
	{if(v) setRemovable.p(label);}
}