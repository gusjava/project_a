package a.entity.gus06.swing.textpane.cust.syntax.sql.ostermiller;

import a.framework.*;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import java.util.Hashtable;
import java.util.Map;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20150622";}

	
	private Map styles;
	
	
	public EntityImpl()
	{
		styles = new Hashtable();
		initStyles();
	}
	
	

	public void p(Object obj) throws Exception
	{new Ostermiller((JTextPane)obj,styles);}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("styles"))
		{styles = (Map)obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("styles"))return styles;
		throw new Exception("Unknown key: "+key);
	}
	

	
	
	
	
	
	/*
	 * !getStype: whitespace
!getStype: reservedWord
!getStype: whitespace
!getStype: reservedWord
!getStype: whitespace
!getStype: identifier
!getStype: whitespace
!getStype: separator
	 */
	
	
	private void initStyles()
	{
		SimpleAttributeSet style;

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("body", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.blue);
		StyleConstants.setBold(style, true);
		StyleConstants.setItalic(style, false);
		styles.put("tag", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.blue);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("endtag", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("reference", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		//StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
		StyleConstants.setForeground(style, Color.GRAY);
		StyleConstants.setBold(style, true);
		StyleConstants.setItalic(style, false);
		styles.put("name", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		//StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
		StyleConstants.setForeground(style, Color.ORANGE.darker());
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, true);
		styles.put("value", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, true);
		StyleConstants.setItalic(style, false);
		styles.put("text", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
//		StyleConstants.setForeground(style, Color.blue);
//		StyleConstants.setBold(style, false);
		
		StyleConstants.setForeground(style, Color.MAGENTA.darker());
		StyleConstants.setBold(style, true);
		
		StyleConstants.setItalic(style, false);
		styles.put("reservedWord", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("identifier", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.GRAY);
		//StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("literal", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, new Color(0x000080)/*Color.navy*/);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("separator", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, true);
		StyleConstants.setItalic(style, false);
		styles.put("operator", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.green.darker());
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("comment", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, new Color(0xA020F0).darker()/*Color.purple*/);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("preprocessor", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("whitespace", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.red);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("error", style);

		style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setForeground(style, Color.orange);
		StyleConstants.setBold(style, false);
		StyleConstants.setItalic(style, false);
		styles.put("unknown", style);
	}

}
