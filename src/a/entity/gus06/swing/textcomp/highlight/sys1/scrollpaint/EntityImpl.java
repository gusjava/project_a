package a.entity.gus06.swing.textcomp.highlight.sys1.scrollpaint;

import a.framework.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.metal.MetalScrollBarUI;


public class EntityImpl implements Entity, T, MouseListener, MouseMotionListener {

	public String creationDate() {return "20140728";}
	
	
	public Object t(Object obj) throws Exception
	{
		JScrollPane scroll = (JScrollPane) obj;
		JTextComponent view = (JTextComponent) scroll.getViewport().getView();
		JScrollBar bar = scroll.getVerticalScrollBar();
        	
		bar.addMouseListener(this);
		bar.addMouseMotionListener(this);
		bar.setBlockIncrement(0);
        
		bar.setUI(new HighScrollBarUI2(view));
		return new BarRepainter(bar);
	}
		
		
	
	private class BarRepainter implements ActionListener
	{
		private JScrollBar bar;
		public BarRepainter(JScrollBar bar) {this.bar = bar;}
		public void actionPerformed(ActionEvent e) {bar.repaint();}
	}
		
		
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {move(e);}
	public void mouseDragged(MouseEvent e) {move(e);}
	public void mouseMoved(MouseEvent e) {}
	
	
	private void move(MouseEvent e)
	{
		JScrollBar bar = (JScrollBar) e.getSource();
		BoundedRangeModel model = bar.getModel();
		HighScrollBarUI2 ui = (HighScrollBarUI2) bar.getUI();
        
		int decr_h = ui.getDecrButton().getHeight();
		int incr_h = ui.getIncrButton().getHeight();
		int h0 = bar.getHeight() - decr_h - incr_h;
        
		double v = (e.getPoint().getY() - incr_h)/(double)h0;
        
		int min = model.getMinimum();
		int max = model.getMaximum();
        
		int newValue = (int)(min + (max-min)*v);
		bar.getModel().setValue(newValue);
	}
	
	



	public class HighScrollBarUI2 extends MetalScrollBarUI
	{
		private JTextComponent view;
		private Highlighter high;
		private Element root;

		public HighScrollBarUI2(JTextComponent view) throws Exception
		{
			this.view = view;
			high = view.getHighlighter();
			root = view.getDocument().getDefaultRootElement();
		}
    
		protected void paintTrack(Graphics g,JComponent c,Rectangle trackBounds)
		{
			super.paintTrack(g,c,trackBounds);
			paintHighlight(g,trackBounds);
		}
    
		protected void paintThumb(Graphics g,JComponent c,Rectangle thumbBounds)
		{
			super.paintThumb(g,c,thumbBounds);
			paintHighlight(g,getTrackBounds());
        
			g.setColor(Color.BLACK);
			g.drawRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,thumbBounds.height);
		}

    
		protected void paintHighlight(Graphics g, Rectangle trackBounds)
		{
			int w = trackBounds.width;
			int h = trackBounds.height;
        
			int x0 = trackBounds.x;
			int y0 = trackBounds.y;
        
			Highlighter.Highlight[] ht = high.getHighlights();
			for(int i=ht.length-1;i>=0;i--)
			{
				DefaultHighlighter.DefaultHighlightPainter painter = 
				(DefaultHighlighter.DefaultHighlightPainter)ht[i].getPainter();
            
				int start = ht[i].getStartOffset();
				int end = ht[i].getEndOffset();
            
				int startLine = root.getElementIndex(start);
				int endLine = root.getElementIndex(end);
				int lineNumber = root.getElementCount();
            
				int y = (int)(y0 + h*startLine/lineNumber);
				int dy = (int)(h*(endLine - startLine + 1)/lineNumber);
				if(dy==0)dy=1;
            
				Color color = painter.getColor();
				if(color==null) color = view.getSelectionColor();
            
				g.setColor(color);
				g.fillRect(x0,y,w,dy);
			}
		}
    
		protected Dimension getMinimumThumbSize()
		{return new Dimension(3,3);}
    
		public JButton getDecrButton()
		{return decrButton;}
    
		public JButton getIncrButton()
		{return incrButton;}
	}
}
