package a.entity.gus06.sys.drawingpanel1.drawer;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20170425";}


	private Service build;


	private List drawers;
	private Color background;
	private Color foreground;
	private Object dimension;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.drawingpanel1.d");
	}
	
	
	public synchronized void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Graphics2D g2 = (Graphics2D) o[0];
		JComponent comp = (JComponent) o[1];
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,	RenderingHints.VALUE_STROKE_PURE);
		
		if(background!=null)
		{
			g2.setPaint(background);
			g2.fillRect(0,0,comp.getWidth(),comp.getHeight());
		}
		
		if(drawers!=null)
		{
			for(int i=0;i<drawers.size();i++)
			{
				P p = (P) drawers.get(i);
				
				if(foreground!=null) g2.setPaint(foreground);
				if(dimension!=null) ((V)p).v("dimension",dimension);
				p.p(obj);
			}
		}
	}
	
	
	public synchronized void v(String key, Object obj) throws Exception
	{
		if(key.equals("background")) {background = (Color) obj;return;}
		if(key.equals("foreground")) {foreground = (Color) obj;return;}
		if(key.equals("dimension")) {dimension = obj;return;}
		if(key.equals("data")) {initData((List) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void initData(List data) throws Exception
	{
		drawers = new ArrayList();
		for(int i=0;i<data.size();i++)
		{
			Map map = (Map) data.get(i);
			P drawer = (P) build.t(map);
			drawers.add(drawer);
		}
	}
}
