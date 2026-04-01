package a.entity.gus06.sys.datepicker1.fr.gui2.field.editor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class EntityImpl extends S1 implements Entity, P, G, I {

	public String creationDate() {return "20160622";}

	
	class HighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{
		public HighlightPainter(Color color)
		{super(color);}
	}
	
	

	public static final String DEFAULT = "jj / mm / aaaa";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
	
	private Highlighter high;
	private HighlightPainter painter = new HighlightPainter(Color.LIGHT_GRAY);
	
	private Calendar calendar = Calendar.getInstance();

	private JTextField field;
	private int part = 0;
	private Date date;
	
	

	public EntityImpl() throws Exception
	{
		field = new JTextField(DEFAULT);
		field.setEditable(false);
		field.setFocusable(true);
		field.setOpaque(true);
		
		field.setBackground(Color.WHITE);
		high = field.getHighlighter();
		
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) nextPart();
				else if(e.getKeyCode()==KeyEvent.VK_SPACE) nextPart();
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) previousPart();
				else if(e.getKeyCode()==KeyEvent.VK_UP) up();
				else if(e.getKeyCode()==KeyEvent.VK_DOWN) down();
			}
		});
		
		field.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {high.removeAllHighlights();}
			public void focusGained(FocusEvent e) {highlightCurrentPart();}
		});
		
		field.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				Point p = e.getPoint();
				int pos = field.viewToModel(p);
				selectPart(pos);
			}
		});
	}


	

	public Object i() throws Exception
	{return field;}



	public Object g() throws Exception
	{return date;}


	
	public void p(Object obj) throws Exception
	{
		date = (Date) obj;
		updateGui();
	}
	
	
	
	
	private void updateGui()
	{
		String s = date==null?DEFAULT:sdf.format(date);
		field.setText(s);
		
		if(field.hasFocus()) highlightCurrentPart();
		else high.removeAllHighlights();
	}
	
	
	
	
	private void selectPart(int c)
	{
		if(c<4)	part=0;
		else if(c>=4 && c<=8)part=1;
		else if(c>8)part=2;
		
		highlightCurrentPart();
	}
	
	
	
	private void nextPart()
	{
		part++;
		if(part==3) part=0;
		highlightCurrentPart();
	}
	
	
	private void previousPart()
	{
		part--;
		if(part==-1) part=2;
		highlightCurrentPart();
	}
	
	
	private void up()
	{
		if(date==null) return;
		if(part==0) previousDay();
		else if(part==1) previousMonth();
		else if(part==2) previousYear();
	}
	
	
	private void down()
	{
		if(date==null) return;
		if(part==0) nextDay();
		else if(part==1) nextMonth();
		else if(part==2) nextYear();
	}
	
	
	
	
	
	private void highlightCurrentPart()
	{
		if(part==0) highlight(0,2);
		else if(part==1) highlight(5,7);
		else if(part==2) highlight(10,14);
	}
	
	
	private void highlight(int start, int end)
	{
		try
		{
			high.removeAllHighlights();
			high.addHighlight(start,end,painter);
		}
		catch(Exception e){Outside.err(this,"highlight(int,int)",e);}
	}
	
	
	
	
	
	
	private void previousDay()
	{
		calendar.setTime(date);
		calendar.roll(Calendar.DAY_OF_MONTH,-1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	private void nextDay()
	{
		calendar.setTime(date);
		calendar.roll(Calendar.DAY_OF_MONTH,1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	
	
	
	private void previousMonth()
	{
		calendar.setTime(date);
		calendar.roll(Calendar.MONTH,-1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	
	private void nextMonth()
	{
		calendar.setTime(date);
		calendar.roll(Calendar.MONTH,1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	
	
	
	private void previousYear()
	{
		calendar.setTime(date);
		calendar.add(Calendar.YEAR,-1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	
	private void nextYear()
	{
		calendar.setTime(date);
		calendar.add(Calendar.YEAR,1);
		date = calendar.getTime();
		
		updateGui();
		dataModified();
	}
	
	
	
	
	private void dataModified()
	{send(this,"dataModified()");}
}