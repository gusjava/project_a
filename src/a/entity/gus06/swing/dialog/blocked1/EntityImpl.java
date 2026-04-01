package a.entity.gus06.swing.dialog.blocked1;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, V, F {

	public String creationDate() {return "20151007";}

	
	private JFrame mainFrame;
	private Dialog0 dialog;
	
	private int width = -1;
	private int height = -1;
	
	

	public EntityImpl() throws Exception
	{
		mainFrame = (JFrame) Outside.resource(this,"mainframe");
		dialog = new Dialog0(mainFrame);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("width"))
		{
			width = obj==null?-1:int_(obj);
			return;
		}
		
		if(key.equals("height"))
		{
			height = obj==null?-1:int_(obj);
			return;
		}
		
		if(key.equals("dimension"))
		{
			Dimension d = (Dimension) obj;
			width = d==null?-1:d.width;
			height = d==null?-1:d.height;
			return;
		}
	}

	
	public void p(Object obj) throws Exception
	{
		if(obj==null) dialog.hideContent();
		else dialog.showContent((JComponent) obj);
	}
	
	
	public boolean f(Object obj) throws Exception
	{return dialog.isVisible();}
	
	
	
	private void dialogHidden()
	{send(this,"dialogHidden()");}
	
	
	private int int_(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	
	private class Dialog0 extends JDialog
	{
		public Dialog0(JFrame parent)
		{
			super(parent,true);
			setUndecorated(true);
			setResizable(false);
		}
	
		public void showContent(JComponent content)
		{
			setContentPane(content);
			pack();
		
			int w = width==-1?getWidth():width;
			int h = height==-1?getHeight():height;
		
			setSize(w,h);
			setLocationRelativeTo(null);
			setVisible(true);
		}
	
		public void hideContent()
		{
			if(!isVisible()) return;
			dispose();
			dialogHidden();
		}
	}
}
