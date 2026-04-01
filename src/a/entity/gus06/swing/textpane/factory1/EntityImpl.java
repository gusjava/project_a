package a.entity.gus06.swing.textpane.factory1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140726";}

	
	
	public Object i() throws Exception
	{return new JTextPane1();}



	private class JTextPane1 extends JTextPane implements P, V
	{
		private List<P> l1 = new ArrayList<P>();
		private List<T> l2 = new ArrayList<T>();
		
		public void p(Object obj) throws Exception
		{l1.add((P) obj);}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("painter")) {l1.add((P) obj);return;}
			if(key.equals("formater")) {l2.add((T) obj);return;}
			throw new Exception("Unknown key: "+key);
		}

        	public JTextPane1()
		{setOpaque(false);}


		protected void paintComponent(Graphics g)
		{
			g.setColor(getBackground());
			g.fillRect(0,0,getWidth(),getHeight());
			for(P p:l1) handle(p,g,this);
         		super.paintComponent(g);
        	}


		public void repaint(long tm, int x, int y, int w, int h)
		{super.repaint(tm,0,0,getWidth(),getHeight());}
		
		
		public void setText(String text)
		{
			for(T t:l2) text = format(t,text);
            		super.setText(text);
		}
	}
	
	



	private void handle(P p, Graphics g, JTextPane comp)
	{
		try{p.p(new Object[]{g,comp});}
		catch(Exception e) {Outside.err(this,"handle(P,Graphics,JTextPane)",e);}
	}
	
	
	private String format(T t, String text)
	{
		try{return (String) t.t(text);}
		catch(Exception e) {Outside.err(this,"format(T,String)",e);}
		return text;
	}
}
