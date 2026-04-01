package a.entity.gus06.sys.geomap1.europe.gui1;

import a.framework.*;
import java.net.URL;
import java.awt.Color;
import java.io.InputStream;
import java.net.URI;

public class EntityImpl extends S1 implements Entity, I, G, R, V {

	public String creationDate() {return "20250427";}


	private URL url;
	private JPanel1 panel;

	public EntityImpl() throws Exception
	{
		url = (URL) Outside.resource(this, "inside#url.svg/europe.svg");
		
		panel = new JPanel1(this);
		panel.load(url);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return panel.getSelectedCode();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("selectionMode")) return panel.getSelectionMode();
		
		if(key.equals("selectedCode")) return panel.getSelectedCode();
		if(key.equals("selectedName")) return panel.getSelectedName();
		
		if(key.equals("hoverCode")) return panel.getHoverCode();
		if(key.equals("hoverName")) return panel.getHoverName();
		
		if(key.equals("tooltipBgColor")) return panel.getTooltipBgColor();
		if(key.equals("tooltipFgColor")) return panel.getTooltipFgColor();
		if(key.equals("tooltipBorderColor")) return panel.getTooltipBorderColor();
		
		if(key.equals("selectionColor")) return panel.getSelectionColor();
		if(key.equals("seaColor")) return panel.getSeaColor();
		if(key.equals("areaColor")) return panel.getAreaColor();
		if(key.equals("hoverColor")) return panel.getHoverColor();
		
		if(key.equals("keys")) return new String[]{
			"url",
			"selectionMode",
			
			"selectedCode",
			"selectedName",
			
			"hoverCode", 
			"hoverName",
			
			"tooltipBgColor",
			"tooltipFgColor",
			"tooltipBorderColor",
			
			"selectionColor",
			"seaColor",
			"areaColor",
			"hoverColor"
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
		if(key.equals("tooltipBorderColor"))
		{
			panel.setTooltipBorderColor((Color) obj);
			return;
		}
		if(key.equals("tooltipFgColor"))
		{
			panel.setTooltipFgColor((Color) obj);
			return;
		}
		if(key.equals("selectionColor"))
		{
			panel.setSelectionColor((Color) obj);
			return;
		}
		if(key.equals("seaColor"))
		{
			panel.setSeaColor((Color) obj);
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
		
		throw new Exception("Unknown key: "+key);
	}
}