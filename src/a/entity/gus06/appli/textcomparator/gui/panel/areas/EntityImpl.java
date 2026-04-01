package a.entity.gus06.appli.textcomparator.gui.panel.areas;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.text.*;



public class EntityImpl extends S1 implements Entity, V, ActionListener, I, G {

	public String creationDate() {return "20150305";}
	
	
	public static final Color COLOR = new Color(204,255,204);
	
	private class HighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{public HighlightPainter(){super(COLOR);}}


	private Service textChangeDelay;
	private Service scrollSynchronizer;


	private HighlightPainter painter;

	private JPanel panel;
	
	private JTextArea area1;
	private JTextArea area2;
	
	private JScrollPane scroll1;
	private JScrollPane scroll2;

	private Highlighter high1;
	private Highlighter high2;

	

	public EntityImpl() throws Exception
	{
		textChangeDelay = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		scrollSynchronizer = Outside.service(this,"gus06.swing.scrollpane.scrollsynchronizer");
		
		painter = new HighlightPainter();
		
		area1 = new JTextArea();
		area2 = new JTextArea();
		
		high1 = area1.getHighlighter();
		high2 = area2.getHighlighter();
		
		scroll1 = new JScrollPane(area1);
		scroll2 = new JScrollPane(area2);
		
		panel = new JPanel(new GridLayout(1,2,3,3));
		panel.add(scroll1);
		panel.add(scroll2);
		
		((S) textChangeDelay.t(area1)).addActionListener(this);
		((S) textChangeDelay.t(area2)).addActionListener(this);
		
		scrollSynchronizer.p(new JScrollPane[]{scroll1,scroll2});
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{modified();}
	
	
	private void modified()
	{send(this,"modified()");}
	
	
	public Object g() throws Exception
	{return new String[]{text1(),text2()};}
	
	
	private String text1() {return area1.getText();}
	private String text2() {return area2.getText();}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("common")) {initCommon(Integer.parseInt((String)obj));return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initCommon(int pos) throws Exception
	{
		removeHighlights(high1);
		removeHighlights(high2);
		
		high1.addHighlight(0,pos,painter);
		high2.addHighlight(0,pos,painter);
	}
	
	
	

	
	
	private void removeHighlights(Highlighter high)
	{
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter))
		high.removeHighlight(h[i]);
	}
}
