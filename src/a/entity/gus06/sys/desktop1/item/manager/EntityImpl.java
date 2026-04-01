package a.entity.gus06.sys.desktop1.item.manager;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191121";}
	
	public static final String KEY_GUI_PANE = "gui_pane";


	private Service buildFrame;

	public EntityImpl() throws Exception
	{
		buildFrame = Outside.service(this,"gus06.sys.desktop1.item.frame.build");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Manager((Map) obj);}
	
	
	
	private void moveFrame(JInternalFrame frame)
	{
		try{((P) frame).p("savePos");}
		catch(Exception e)
		{Outside.err(this,"moveFrame(JInternalFrame)",e);}
	}
	
	private void clearFrame(JInternalFrame frame)
	{
		try{((P) frame).p("clear");}
		catch(Exception e)
		{Outside.err(this,"clearFrame(JInternalFrame)",e);}
	}
	
	private String id(JInternalFrame frame)
	{
		try{return (String) ((R) frame).r("id");}
		catch(Exception e)
		{Outside.err(this,"id(JInternalFrame)",e);}
		return null;
	}
	
	
	
	
	
	private class Manager extends S1 implements V, R, InternalFrameListener, ComponentListener
	{
		private Map main;
		private Map itemMap;
		
		private JInternalFrame selectedItem;
		private JInternalFrame loadedItem;
		
		
		public Manager(Map main)
		{
			this.main = main;
			itemMap = new HashMap();
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("add")) {addItem();return;}
			if(key.equals("remove")) {removeItem();return;}
			if(key.equals("load")) {loadItem((String) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("selected")) return selectedItem;
			if(key.equals("loaded")) return loadedItem;
			if(key.equals("keys")) return new String[]{"selected","loaded"};
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		public void internalFrameActivated(InternalFrameEvent e) {selectFrame((JInternalFrame) e.getInternalFrame());}
		public void internalFrameClosing(InternalFrameEvent e) {removeFrame((JInternalFrame) e.getInternalFrame());}
		public void internalFrameDeactivated(InternalFrameEvent e) {}
		public void internalFrameDeiconified(InternalFrameEvent e) {}
		public void internalFrameIconified(InternalFrameEvent e) {}
		public void internalFrameOpened(InternalFrameEvent e) {}
		public void internalFrameClosed(InternalFrameEvent e) {}
		
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {moveFrame((JInternalFrame) e.getSource());}
		public void componentResized(ComponentEvent e) {moveFrame((JInternalFrame) e.getSource());}
		public void componentShown(ComponentEvent e) {}
		
		
		private void selectFrame(JInternalFrame frame)
		{
			selectedItem = frame;
			selectionChanged();
		}
		
		private void removeFrame(JInternalFrame frame)
		{
			String id = id(frame);
			if(id==null) return;
			JDesktopPane pane = pane();
			
			removeListeners(frame);
			itemMap.remove(id);
			pane.remove(frame);
			clearFrame(frame);
			selectFrame(null);
			
			pane.repaint();
			itemRemoved();
		}
		
		private void addItem() throws Exception
		{
			String id = "ITEM_"+itemMap.size();
			loadItem(id);
		}
		
		private void removeItem() throws Exception
		{
			if(selectedItem==null) return;
			removeFrame(selectedItem);
		}
		
		private void loadItem(String id) throws Exception
		{
			JDesktopPane pane = pane();
			JInternalFrame frame = (JInternalFrame) buildFrame.t(new Object[]{main,id});
			
			itemMap.put(id,frame);
			pane.add(frame);
			
			addListeners(frame);
			loadedItem = frame;
			itemLoaded();
		}
		
		
		private JDesktopPane pane()
		{return (JDesktopPane) main.get(KEY_GUI_PANE);}
		
		private void addListeners(JInternalFrame frame)
		{
			frame.addInternalFrameListener(this);
			frame.addComponentListener(this);
		}
		
		private void removeListeners(JInternalFrame frame)
		{
			frame.removeInternalFrameListener(this);
			frame.removeComponentListener(this);
		}
		
		
		private void selectionChanged()
		{send(this,"selectionChanged()");}
		
		private void itemLoaded()
		{send(this,"itemLoaded()");}
		
		private void itemRemoved()
		{send(this,"itemRemoved()");}
	}
}
