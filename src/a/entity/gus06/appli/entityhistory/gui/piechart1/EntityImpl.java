package a.entity.gus06.appli.entityhistory.gui.piechart1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.awt.Point;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20201220";}
	
	public static final Color COLOR_MAP = Color.BLACK;
	public static final Color COLOR_SELECTED = Color.BLUE;
	public static final Color COLOR_UNSELECTED = Color.WHITE;


	private Service mapHolder;
	private Service buildList;
	private Service onF1;

	private JPanel screen;
	
	private Map points;
	private Map map;
	private List list;
	
	private boolean modePie = true;


	public EntityImpl() throws Exception
	{
		mapHolder = Outside.service(this,"gus06.appli.entityhistory.map.holder");
		buildList = Outside.service(this,"gus06.appli.entityhistory.gui.piechart1.buildlist");
		onF1 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f1");
		
		points = new HashMap();
		list = (List) buildList.t(mapHolder.g());
		screen = new JPanelScreen();
		
		onF1.p(new Object[]{screen,(E) this::shiftMode});
	}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		list = (List) buildList.t(mapHolder.g());
		screen.repaint();
	}
	
	
	private void shiftMode()
	{
		modePie = !modePie;
		screen.repaint();
	}
		
	
	
	public class JPanelScreen extends JPanel implements MouseMotionListener
	{
		public JPanelScreen()
		{
			super();
			setFocusable(true);
			addMouseMotionListener(this);
		}
		
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			if(modePie) paintPie(g2);
			else paintRect(g2);
		}
		
		
		private void paintPie(Graphics2D g2)
		{
			g2.setColor(getBackground());
			g2.fillRect(0,0,getWidth(),getHeight());
			
			double w = getWidth();
			double h = getHeight();
			
			double w2 = w/2;
			double h2 = h/2;
			
			double r = Math.min(w2,h2);
			double d = 2*r;
			
			double x0 = w2>h2 ? w2-r : 0;
			double y0 = h2>w2 ? h2-r : 0;
			
			double xc = x0+r;
			double yc = y0+r;
			
			double ra = r*0.1;
			double da = d*0.1;
			double xa0 = xc-ra;
			double ya0 = yc-ra;
			
			g2.setColor(COLOR_MAP);
			g2.fillOval((int) x0,(int) y0,(int) d, (int) d);
			
			g2.setColor(getBackground());
			g2.fillOval((int) xa0,(int) ya0,(int) da, (int) da);
			
			g2.drawLine((int) xc,(int) yc,(int) w, (int) yc);
			
			points.clear();
			if(list!=null && !list.isEmpty())
			{
				int nb = list.size();
				
				for(int i=0;i<nb;i++)
				{
					Map info = (Map) list.get(i);
					
					String name = (String) info.get("name");
					String date = (String) info.get("date");
					Color color = findColor(name);
					
					Double namePos = (Double) info.get("namePos");
					Double datePos = (Double) info.get("datePos");
					
					double angle = namePos*Math.PI*2;
					double distance = ra + (r-ra)*datePos;
					
					double x = xc + Math.cos(angle)*distance;
					double y = yc + Math.sin(angle)*distance;
					
					int x_ = (int) x;
					int y_ = (int) y;
					
					Set point = getPoint(x_,y_);
					point.add(name+" ["+date+"]");
					
					g2.setColor(color);
					g2.fillRect(x_,y_,2,2);
				}
			}
		}
		
		
		private void paintRect(Graphics2D g2)
		{
			g2.setColor(COLOR_MAP);
			g2.fillRect(0,0,getWidth(),getHeight());
			
			double w = getWidth();
			double h = getHeight();
			
			points.clear();
			if(list!=null && !list.isEmpty())
			{
				int nb = list.size();
				
				for(int i=0;i<nb;i++)
				{
					Map info = (Map) list.get(i);
					
					String name = (String) info.get("name");
					String date = (String) info.get("date");
					Color color = findColor(name);
					
					Double namePos = (Double) info.get("namePos");
					Double datePos = (Double) info.get("datePos");
					
					double x = w*datePos;
					double y = h*(1-namePos);
					
					int x_ = (int) x;
					int y_ = (int) y;
					
					Set point = getPoint(x_,y_);
					point.add(name+" ["+date+"]");
					
					g2.setColor(color);
					g2.fillRect(x_,y_,2,2);
				}
			}
		}
		
		
		private Color findColor(String name)
		{
			if(map!=null && map.containsKey(name)) return COLOR_SELECTED;
			return COLOR_UNSELECTED;
		}
		
		private Set getPoint(int x_, int y_)
		{
			String key = x_+"_"+y_;
			if(!points.containsKey(key)) points.put(key,new HashSet());
			return (Set) points.get(key);
		}
		
		private boolean hasPoint(int x_, int y_)
		{
			String key = x_+"_"+y_;
			return points.containsKey(key);
		}
		
		
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e)
		{
			Point p = e.getPoint();
			if(!hasPoint(p.x,p.y))
			{
				setToolTipText("");
				return;
			}
			
			Set point = getPoint(p.x,p.y);
			List l = new ArrayList(point);
			Collections.sort(l);
			
			StringBuffer b = new StringBuffer("<html>");
			for(int i=0;i<l.size();i++) b.append(l.get(i)+"<br/>");
			b.append("</html>");
			
			setToolTipText(b.toString());
		}
	}
}
