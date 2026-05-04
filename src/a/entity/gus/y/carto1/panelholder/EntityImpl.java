package a.entity.gus.y.carto1.panelholder;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EntityImpl extends S1 implements Entity, P, G, I, V, R, MouseListener, MouseMotionListener {

	public String creationDate() {return "20161202";}


	private Service screen;
	private Service selection;
	private Service drawer;
	private Service drag;
	
	private Service labelBuilder;
	
	
	private Map rectMap;
	private Set selectedSet;
	private Set dragSet;
	private Rectangle selectionArea;
	
	private JPanel panel;
	
	private boolean isSelecting = false;
	private boolean isDragging = false;
	


	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus.x.swing.panel.screen.drawn");
		selection = Outside.service(this,"*gus.y.carto1.selection");
		drawer = Outside.service(this,"*gus.y.carto1.paneldrawer1");
		drag = Outside.service(this,"*gus.y.carto1.drag");
		
		labelBuilder = Outside.service(this,"gus.y.carto1.labelbuilder");
		
		rectMap = new HashMap();
		
		initPanel();
		initPanelSelection();
		initPanelDrag();
		initPanelDrawer();
	}
	
	
	
	private void initPanel() throws Exception
	{
		panel = (JPanel) screen.i();
		panel.setFocusable(true);
		panel.setPreferredSize(new Dimension(200,100));
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	
	
	private void initPanelSelection() throws Exception
	{
		selection.v("rectMap",rectMap);
		selectedSet = (Set) selection.g();
		selectionArea = (Rectangle) selection.r("selectionArea");
	}
	
	
	private void initPanelDrag() throws Exception
	{
		drag.v("rectMap",rectMap);
		dragSet = (Set) drag.g();
	}
	
	
	
	private void initPanelDrawer() throws Exception
	{
		drawer.v("rectMap",rectMap);
		drawer.v("selectedSet",selectedSet);
		drawer.v("selectionArea",selectionArea);
		screen.p(drawer);
	}
	
	
	
	private void addRectangle(String name) throws Exception
	{
		Rectangle rec = (Rectangle) labelBuilder.t(name);
		rectMap.put(name,rec);
	}

	


	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			rectMap.clear();
		}
		else if(obj instanceof String)
		{
			addRectangle((String) obj);
		}
		else if(obj instanceof Collection)
		{
			Collection c = (Collection) obj;
			Iterator it = c.iterator();
			while(it.hasNext())
			{addRectangle((String)it.next());}
		}
		
		panel.repaint();
	}



	public Object i() throws Exception
	{return panel;}


	public Object g() throws Exception
	{return rectMap;}
	
	
	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("drawer"))
		{
			drawer = (Service) obj;
			initPanelDrawer();
			return;
		}
		if(key.equals("labelBuilder"))
		{
			labelBuilder = (Service) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
	}

	


	public Object r(String key) throws Exception
	{
		if(key.equals("drawer")) return drawer;
		if(key.equals("rectMap")) return rectMap;
		if(key.equals("selectedSet")) return selectedSet;
		if(key.equals("dragSet")) return dragSet;
		
		if(key.equals("keys")) return new String[]{"drawer","rectMap","selectedSet","dragSet"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	// MOUSE LISTENER METHODS
	public void mouseClicked(MouseEvent e)  {if(e.getClickCount()==2){doubleClicked();}}
	public void mouseEntered(MouseEvent e)  {}
	public void mouseExited(MouseEvent e)   {release(e.getX(),e.getY());}
	public void mousePressed(MouseEvent e)  {press(e.getX(),e.getY());}
	public void mouseReleased(MouseEvent e) {release(e.getX(),e.getY());}
	// MOUSE MOTION LISTENER METHODS
	public void mouseDragged(MouseEvent e)  {drag(e.getX(),e.getY());}
	public void mouseMoved(MouseEvent e)	{}
	
	
	private void doubleClicked()
	{
		
	}
	
	
	private void release(int x, int y)
	{
		if(isDragging) clearDrag();

		if(isSelecting)
		{
			clearSelectionArea();
			if(hasSelected()) selectFromCarto();
		}
		panel.repaint();
	}


	private void press(int x, int y)
	{
		panel.requestFocusInWindow();
		String target = targetName(x,y);
		if(target==null)
		{
			startSelectionArea(x, y);
			pressedEmpty();
		}
		else
		{
			if(!isSelected(target))
				setSelected(target);
			
			if(hasSelected())
			{
				dragSet.clear();
				dragSet.addAll(selectedSet);
				startDrag(x,y);
				selectFromCarto();
			}
		}
		panel.repaint();
	}
	
	
	private void drag(int x, int y)
	{
		if(isDragging)
		{
			moveDrag(x,y);
			panel.repaint();
		}
		else if(isSelecting)
		{
			modifySelectionArea(x,y);
			panel.repaint();
		}
	}
	
	
	private void startSelectionArea(int x, int y)
	{
		try
		{
			selection.v("startArea",new int[]{x,y});
			isSelecting = true;
		}
		catch(Exception e)
		{Outside.err(this,"startSelectionArea(int,int)",e);}
	}
	
	
	private void modifySelectionArea(int x, int y)
	{
		try
		{
			selection.v("modifyArea",new int[]{x,y});
			isSelecting = true;
		}
		catch(Exception e)
		{Outside.err(this,"modifySelectionArea(int,int)",e);}
	}
	
	
	private void clearSelectionArea()
	{
		try
		{
			selection.v("clearArea",null);
			isSelecting = false;
		}
		catch(Exception e)
		{Outside.err(this,"clearSelectionArea()",e);}
	}
	
	
	private void startDrag(int x, int y)
	{
		try
		{
			drag.v("start",new int[]{x,y});
			isDragging = true;
		}
		catch(Exception e)
		{Outside.err(this,"startDrag(int,int)",e);}
	}
	
	
	private void moveDrag(int x, int y)
	{
		try
		{
			drag.v("move",new int[]{x,y});
			isDragging = true;
		}
		catch(Exception e)
		{Outside.err(this,"moveDrag(int,int)",e);}
	}
	
	
	private void clearDrag()
	{
		dragSet.clear();
		isDragging = false;
	}
	
	
	
	private boolean hasSelected()
	{return !selectedSet.isEmpty();}
	
	private boolean isSelected(String name)
	{return selectedSet.contains(name);}
	
	private void setSelected(String name)
	{
		selectedSet.clear();
		selectedSet.add(name);
	}
	
	
	private String targetName(int x,int y)
	{
		Iterator it = rectMap.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Rectangle rec = (Rectangle)rectMap.get(name);
			if(rec.contains(x,y))return name;
		}
		return null;
	}
	
	
	
	private void selectFromCarto()
	{send(this,"selectFromCarto()");}
	
	private void pressedEmpty()
	{send(this,"pressedEmpty()");}
}
