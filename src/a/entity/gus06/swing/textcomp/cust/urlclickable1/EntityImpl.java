package a.entity.gus06.swing.textcomp.cust.urlclickable1;

import a.framework.*;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseMotionListener;
import java.util.Objects;
import java.util.Map;
import javax.swing.JToolTip;
import javax.swing.text.Highlighter;
import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.text.DefaultHighlighter;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.awt.Desktop;
import java.net.URI;
import java.awt.Cursor;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251201";}

	public static final String URL_REGEX = "https?:\\/\\/[a-zA-Z0-9\\.\\?\\/_%+-]+";
	public static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
	
	public static final Color HIGHLIGHT_COLOR = new Color(180, 220, 255);
	public static final Highlighter.HighlightPainter PAINTER = new DefaultHighlighter.DefaultHighlightPainter(HIGHLIGHT_COLOR);


	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		new Holder(comp);
	}
	
	private class Holder implements MouseListener, MouseMotionListener, DocumentListener
	{
		private JTextComponent comp;
		private Highlighter high;
		private List<Sequence> urls;
		private Sequence target;
		private Object currentHighlight = null;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			high = comp.getHighlighter();
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
			comp.getDocument().addDocumentListener(this);
			urls = new ArrayList<>();
			scan();
		}
		
		public void insertUpdate(DocumentEvent e) {scan();}
		public void removeUpdate(DocumentEvent e) {scan();}
		public void changedUpdate(DocumentEvent e) {}

		public void mouseMoved(MouseEvent e)
		{checkAt(comp.viewToModel(e.getPoint()));}
		
		
		
		private void scan()
		{
			clear();
			urls.clear();
			String text = comp.getText();
			Matcher matcher = URL_PATTERN.matcher(text);
			while(matcher.find())
			{
				String urlText = matcher.group();
				int start = matcher.start();
				int end = matcher.end();
				urls.add(new Sequence(urlText, start, end));
			}
   			checkAt(comp.getCaretPosition());
		}
		
		private Sequence findUrlAt(int pos)
		{
			for(Sequence url : urls)
			if(url.start<=pos && url.end>pos) return url;
			return null;
		}
		
		private void checkAt(int pos)
		{
			Sequence url = findUrlAt(pos);
			if(url==null) clear();
			else handle(url);
		}
		
		private void clear()
		{
			if(target==null) return;
			high.removeHighlight(currentHighlight);
			target=null;
			currentHighlight = null;
			comp.setCursor(Cursor.getDefaultCursor());
		}
		
		private void handle(Sequence url)
		{
			if(target!=null && target.start==url.start && target.end==url.end) return;
			if(currentHighlight!=null) high.removeHighlight(currentHighlight);
			currentHighlight = addHighlight(high, url);
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			target = url;
		}

		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2 && target!=null) 
			browseUrl(target.text);
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {}
	}
	
	private class Sequence
	{
		public String text;
		public int start;
		public int end;
	
		public Sequence(String text, int start, int end)
		{
			this.text = text;
			this.start = start;
			this.end = end;
		}
	}
	
	private void browseUrl(String currentUrl)
	{
		try
		{
			Desktop.getDesktop().browse(new URI(currentUrl));
		}
		catch(Exception e)
		{Outside.err(this,"browseUrl(String)",e);}
	}
	
	private Object addHighlight(Highlighter high, Sequence url)
	{
		try
		{
			return high.addHighlight(url.start, url.end, PAINTER);
		}
		catch(Exception e)
		{Outside.err(this,"addHighlight(Highlighter, Sequence)",e);}
		return null;
	}
}
