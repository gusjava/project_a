package a.entity.gus06.appli.laboswing.gui.jtextarea.support;

import a.framework.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160501";}

	
	
	public Object t(Object obj) throws Exception
	{return new Support((JTextArea) obj);}
	
	
	
	
	private class Support extends S1 implements R, DocumentListener, CaretListener, KeyListener, MouseMotionListener, MouseListener
	{
		private JTextArea area;
		private String method;
		private Object event;
		
		public Support(JTextArea area)
		{
			this.area = area;
			
			area.addKeyListener(this);
			area.addMouseListener(this);
			area.addMouseMotionListener(this);
			area.addCaretListener(this);
			area.getDocument().addDocumentListener(this);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("event")) return event;
			if(key.equals("method")) return method;
			
			if(key.equals("keys")) return new String[]{"event","method"};
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void handle(String method, Object event)
		{
			this.method = method;
			this.event = event;
			modified();
		}
		
		private void modified()
		{send(this,"modified()");}
		
		
		public void changedUpdate(DocumentEvent e)	{handle("changedUpdate(DocumentEvent)",e);}
		public void insertUpdate(DocumentEvent e)	{handle("insertUpdate(DocumentEvent)",e);}
		public void removeUpdate(DocumentEvent e)	{handle("removeUpdate(DocumentEvent)",e);}
		public void keyTyped(KeyEvent e)		{handle("keyTyped(KeyEvent)",e);}
		public void keyPressed(KeyEvent e)		{handle("keyPressed(KeyEvent)",e);}
		public void keyReleased(KeyEvent e)		{handle("keyReleased(KeyEvent)",e);}
		public void mouseDragged(MouseEvent e)		{handle("mouseDragged(MouseEvent)",e);}
		public void mouseMoved(MouseEvent e)		{handle("mouseMoved(MouseEvent)",e);}
		public void mouseClicked(MouseEvent e)		{handle("mouseClicked(MouseEvent)",e);}
		public void mousePressed(MouseEvent e)		{handle("mousePressed(MouseEvent)",e);}
		public void mouseReleased(MouseEvent e)		{handle("mouseReleased(MouseEvent)",e);}
		public void mouseEntered(MouseEvent e)		{handle("mouseEntered(MouseEvent)",e);}
		public void mouseExited(MouseEvent e)		{handle("mouseExited(MouseEvent)",e);}
		public void caretUpdate(CaretEvent e)		{handle("caretUpdate(CaretEvent)",e);}
	}
}
