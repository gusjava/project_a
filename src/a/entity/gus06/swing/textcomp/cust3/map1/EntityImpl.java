package a.entity.gus06.swing.textcomp.cust3.map1;

import a.framework.*;
import java.util.Map;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.Insets;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20180211";}
	
	public final static String KEY_TEXT = "text";
	public final static String KEY_TOOLTIP = "tooltip";
	public final static String KEY_FONT = "font";
	public final static String KEY_FONTSIZE = "fontsize";
	public final static String KEY_FOREGROUND = "foreground";
	public final static String KEY_BACKGROUND = "background";
	public final static String KEY_CARETCOLOR = "caretcolor";
	public final static String KEY_SELECTIONCOLOR = "selectioncolor";
	public final static String KEY_SELECTEDTEXTCOLOR = "selectedtextcolor";
	public final static String KEY_OPAQUE = "opaque";
	public final static String KEY_EDITABLE = "editable";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_BORDER = "border";

	private Service findBorder;
	private Service findInsets;
	private Service findColor;
	private Service findFont;
	private Service findBoolean;
	private Service findInteger;

	public EntityImpl() throws Exception
	{
		findBorder = Outside.service(this,"gus06.find.border");
		findInsets = Outside.service(this,"gus06.find.insets");
		findColor = Outside.service(this,"gus06.find.color");
		findFont = Outside.service(this,"gus06.find.font");
		findBoolean = Outside.service(this,"gus06.find.boolean1");
		findInteger = Outside.service(this,"gus06.find.integer");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Map map = (Map) o[1];
		
		String text = (String) get(map,KEY_TEXT);
		String tooltip = (String) get(map,KEY_TOOLTIP);
		
		Font font = getFont(map,KEY_FONT);
		Integer fontsize = getInteger(map,KEY_FONTSIZE);
		
		Color foreground = getColor(map,KEY_FOREGROUND);
		Color background = getColor(map,KEY_BACKGROUND);
		Color caretcolor = getColor(map,KEY_CARETCOLOR);
		Color selectioncolor = getColor(map,KEY_SELECTIONCOLOR);
		Color selectedtextcolor = getColor(map,KEY_SELECTEDTEXTCOLOR);
		
		Boolean opaque = getBoolean(map,KEY_OPAQUE);
		Boolean editable = getBoolean(map,KEY_EDITABLE);
		
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
		
		if(caretcolor!=null)
			comp.setCaretColor(caretcolor);
		
		if(selectioncolor!=null)
			comp.setSelectionColor(selectioncolor);
		
		if(selectedtextcolor!=null)
			comp.setSelectedTextColor(selectedtextcolor);
		
		if(opaque!=null)
			comp.setOpaque(opaque.booleanValue());
		
		if(editable!=null)
			comp.setEditable(editable.booleanValue());
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
	
	private Color getColor(Map map, String key) throws Exception
	{return (Color) findColor.t(get(map,key));}
	
	private Boolean getBoolean(Map map, String key) throws Exception
	{return (Boolean) findBoolean.t(get(map,key));}
	
	private Font getFont(Map map, String key) throws Exception
	{return (Font) findFont.t(get(map,key));}
	
	private Insets getInsets(Map map, String key) throws Exception
	{return (Insets) findInsets.t(get(map,key));}
	
	private Border getBorder(Map map, String key) throws Exception
	{return (Border) findBorder.t(get(map,key));}
	
	private Integer getInteger(Map map, String key) throws Exception
	{return (Integer) findInteger.t(get(map,key));}
}