package a.entity.gus06.swing.checkbox.cust3.map1;

import a.framework.*;
import java.util.Map;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import javax.swing.JCheckBox;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221106";}
	
	public final static String KEY_TEXT = "text";
	public final static String KEY_TOOLTIP = "tooltip";
	public final static String KEY_FONT = "font";
	public final static String KEY_FONTSIZE = "fontsize";
	public final static String KEY_FOREGROUND = "foreground";
	public final static String KEY_BACKGROUND = "background";
	public final static String KEY_OPAQUE = "opaque";
	public final static String KEY_ENABLED = "enabled";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_BORDER = "border";


	private Service defaultCust;
	private Service findBorder;
	private Service findInsets;
	private Service findColor;
	private Service findFont;

	public EntityImpl() throws Exception
	{
		defaultCust = Outside.service(this,"gus06.swing.comp.cust3.map1");
		findBorder = Outside.service(this,"gus06.find.border");
		findInsets = Outside.service(this,"gus06.find.insets");
		findColor = Outside.service(this,"gus06.find.color");
		findFont = Outside.service(this,"gus06.find.font");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JCheckBox comp = (JCheckBox) o[0];
		Map map = (Map) o[1];
		
		defaultCust.p(new Object[]{comp,map});
		
		String text = (String) get(map,KEY_TEXT);
		String tooltip = (String) get(map,KEY_TOOLTIP);
		
		Font font = getFont(map,KEY_FONT);
		Integer fontsize = (Integer) get(map,KEY_FONTSIZE);
		
		Color foreground = getColor(map,KEY_FOREGROUND);
		Color background = getColor(map,KEY_BACKGROUND);
		
		Boolean opaque = (Boolean) get(map,KEY_OPAQUE);
		Boolean enabled = (Boolean) get(map,KEY_ENABLED);
		
		Insets margin = getInsets(map,KEY_MARGIN);
		Border border = getBorder(map,KEY_BORDER);
		
		if(text!=null) comp.setText(text);
		
		if(font!=null)
			comp.setFont(font);
		else
			if(fontsize!=null) comp.setFont(comp.getFont().deriveFont(fontsize.floatValue()));
		
		if(border!=null)
			comp.setBorder(border);
		
		if(margin!=null)
			comp.setMargin(margin);
		
		if(tooltip!=null)
			comp.setToolTipText(tooltip);
		
		if(foreground!=null)
			comp.setForeground(foreground);
		
		if(background!=null)
			comp.setBackground(background);
		
		if(opaque!=null)
			comp.setOpaque(opaque.booleanValue());
		
		if(enabled!=null)
			comp.setEnabled(enabled.booleanValue());
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Color getColor(Map map, String key) throws Exception
	{return (Color) findColor.t(get(map,key));}
	
	private Font getFont(Map map, String key) throws Exception
	{return (Font) findFont.t(get(map,key));}
	
	private Insets getInsets(Map map, String key) throws Exception
	{return (Insets) findInsets.t(get(map,key));}
	
	private Border getBorder(Map map, String key) throws Exception
	{return (Border) findBorder.t(get(map,key));}
}