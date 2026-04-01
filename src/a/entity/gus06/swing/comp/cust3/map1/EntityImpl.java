package a.entity.gus06.swing.comp.cust3.map1;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.util.Iterator;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140907";}


	private Service findBorder;
	private Service findFont;
	private Service findColor;
	private Service findInteger;
	private Service findDimension;
	private Service deriveFont;
	
	public EntityImpl() throws Exception
	{
		findBorder = Outside.service(this,"gus06.find.border");
		findFont = Outside.service(this,"gus06.find.font");
		findColor = Outside.service(this,"gus06.find.color");
		findInteger = Outside.service(this,"gus06.find.integer");
		findDimension = Outside.service(this,"gus06.find.dimension");
		deriveFont = Outside.service(this,"gus06.font.derivefont");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		Map map = (Map) o[1];
		
		Object border = get(map,"border");
		if(border!=null) custBorder(comp,border);
		
		Object font = get(map,"font");
		if(font!=null) custFont(comp,font);
		
		Object fontsize = get(map,"fontsize");
		if(fontsize!=null) custFontsize(comp,fontsize);
		
		Object opaque = get(map,"opaque");
		if(opaque!=null) custOpaque(comp,opaque);
		
		Object tooltipText = get(map,"tooltiptext");
		if(tooltipText!=null) custTooltipText(comp,tooltipText);
		
		Object foreground = get(map,"foreground");
		if(foreground!=null) custForeground(comp,foreground);
		
		Object background = get(map,"background");
		if(background!=null) custBackground(comp,background);
		
		Object size = get(map,"size");
		if(size!=null) custSize(comp,size);
		
		Object preferredsize = get(map,"preferredsize");
		if(preferredsize!=null) custSize(comp,preferredsize);
		
		Object minimumsize = get(map,"minimumsize");
		if(minimumsize!=null) custMinimumSize(comp,minimumsize);
		
		Object maximumsize = get(map,"maximumsize");
		if(maximumsize!=null) custMaximumSize(comp,maximumsize);
	}
	
	
	
	
	private void custBorder(JComponent comp, Object value) throws Exception
	{
		Border border = (Border) findBorder.t(value);
		comp.setBorder(border);
	}
	
	private void custFont(JComponent comp, Object value) throws Exception
	{
		Font font = (Font) findFont.t(value);
		comp.setFont(font);
	}
	
	private void custFontsize(JComponent comp, Object value) throws Exception
	{
		Integer fontSize = (Integer) findInteger.t(value);
		Font font = (Font) deriveFont.t(new Object[]{comp.getFont(), fontSize});
		comp.setFont(font);
	}
	
	private void custOpaque(JComponent comp, Object value) throws Exception
	{
		boolean isOpaque = Boolean.parseBoolean(""+value);
		comp.setOpaque(isOpaque);
	}
	
	private void custTooltipText(JComponent comp, Object value) throws Exception
	{
		String tooltipText = (String) value;
		comp.setToolTipText(tooltipText);
	}
	
	private void custForeground(JComponent comp, Object value) throws Exception
	{
		Color color = (Color) findColor.t(value);
		comp.setForeground(color);
	}
	
	private void custBackground(JComponent comp, Object value) throws Exception
	{
		Color color = (Color) findColor.t(value);
		comp.setBackground(color);
	}
	
	private void custSize(JComponent comp, Object value) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(value);
		comp.setSize(dim);
	}
	
	private void custPreferredSize(JComponent comp, Object value) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(value);
		comp.setPreferredSize(dim);
	}
	
	private void custMinimumSize(JComponent comp, Object value) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(value);
		comp.setMinimumSize(dim);
	}
	
	private void custMaximumSize(JComponent comp, Object value) throws Exception
	{
		Dimension dim = (Dimension) findDimension.t(value);
		comp.setMaximumSize(dim);
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}