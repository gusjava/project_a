package a.entity.gus06.sys.geomap1.generic.gui1;

import a.framework.*;
import java.net.URL;
import java.awt.Color;
import java.io.InputStream;
import java.net.URI;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, G, R, V {

	public String creationDate() {return "20250504";}


	private JPanel1 panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel1(this);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return panel.getSelectedKey();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("selectionMode")) return panel.getSelectionMode();
		if(key.equals("selectedKey")) return panel.getSelectedKey();
		if(key.equals("hoverKey")) return panel.getHoverKey();
		
		if(key.equals("tooltipBgColor")) return panel.getTooltipBgColor();
		if(key.equals("tooltipFgColor")) return panel.getTooltipFgColor();
		if(key.equals("tooltipBorderColor")) return panel.getTooltipBorderColor();
		
		if(key.equals("selectionColor")) return panel.getSelectionColor();
		if(key.equals("otherColor")) return panel.getOtherColor();
		if(key.equals("areaColor")) return panel.getAreaColor();
		if(key.equals("hoverColor")) return panel.getHoverColor();
		
		if(key.equals("scaleFactor")) return panel.getScaleFactor();
		
		if(key.equals("keys")) return new String[]{
			"url",
			"selectionMode",
			"selectedKey",
			"hoverKey", 
			"tooltipColor",
			"selectionColor",
			"seaColor",
			"areaColor",
			"hoverColor",
			"scaleFactor"
		};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("uri"))
		{
			URI uri = (URI) obj;
			panel.load(uri);
			return;
		}
		if(key.equals("url"))
		{
			URL url = (URL) obj;
			panel.load(url);
			return;
		}
		if(key.equals("inputStream"))
		{
			InputStream is = (InputStream) obj;
			panel.load(is);
			return;
		}
		if(key.equals("showTooltip"))
		{
			boolean val = Boolean.parseBoolean(""+obj);
			panel.setShowTooltip(val);
			return;
		}
		if(key.equals("showMouseLocation"))
		{
			boolean val = Boolean.parseBoolean(""+obj);
			panel.setShowMouseLocation(val);
			return;
		}
		if(key.equals("w0"))
		{
			int val = Integer.parseInt(""+obj);
			panel.setW0(val);
			return;
		}
		if(key.equals("h0"))
		{
			int val = Integer.parseInt(""+obj);
			panel.setH0(val);
			return;
		}
		if(key.equals("x0"))
		{
			int val = Integer.parseInt(""+obj);
			panel.setX0(val);
			return;
		}
		if(key.equals("y0"))
		{
			int val = Integer.parseInt(""+obj);
			panel.setY0(val);
			return;
		}
		if(key.equals("area"))
		{
			Rectangle val = (Rectangle) obj;
			panel.setArea(val);
			return;
		}
		if(key.equals("selectionMode"))
		{
			String val = (String) obj;
			panel.setSelectionMode(val);
			return;
		}
		if(key.equals("select"))
		{
			String val = (String) obj;
			panel.select(val);
			return;
		}
		if(key.equals("tooltipBgColor"))
		{
			panel.setTooltipBgColor((Color) obj);
			return;
		}
		if(key.equals("tooltipFgColor"))
		{
			panel.setTooltipFgColor((Color) obj);
			return;
		}
		if(key.equals("tooltipBorderColor"))
		{
			panel.setTooltipBorderColor((Color) obj);
			return;
		}
		if(key.equals("tooltipFont"))
		{
			panel.setTooltipFont((Font) obj);
			return;
		}
		if(key.equals("selectionColor"))
		{
			panel.setSelectionColor((Color) obj);
			return;
		}
		if(key.equals("hoverColor"))
		{
			panel.setHoverColor((Color) obj);
			return;
		}
		if(key.equals("otherColor"))
		{
			panel.setOtherColor((Color) obj);
			return;
		}
		if(key.equals("areaColor"))
		{
			panel.setAreaColor((Color) obj);
			return;
		}
		if(key.equals("hoverColor"))
		{
			panel.setHoverColor((Color) obj);
			return;
		}
		if(key.equals("keyT"))
		{
			panel.setKeyT((T) obj);
			return;
		}
		if(key.equals("areaColorT"))
		{
			panel.setAreaColorT((T) obj);
			return;
		}
		if(key.equals("tooltipTextT"))
		{
			panel.setTooltipTextT((T) obj);
			return;
		}
		if(key.equals("tooltipIconT"))
		{
			panel.setTooltipIconT((T) obj);
			return;
		}
		if(key.equals("idPaintF"))
		{
			panel.setIdPaintF((F) obj);
			return;
		}
		if(key.equals("keySelectF"))
		{
			panel.setKeySelectF((F) obj);
			return;
		}
		if(key.equals("scaleFactor"))
		{
			double value = obj!=null ? (Double) obj : 1.0;
			panel.setScaleFactor(value);
			return;
		}
		if(key.equals("dimension"))
		{
			panel.setPreferredSize((Dimension) obj);
			panel.setMinimumSize((Dimension) obj);
			panel.setMaximumSize((Dimension) obj);
			return;
		}
		
		throw new Exception("Unknown key: "+key);
	}
}