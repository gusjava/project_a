package a.entity.gus06.swing.textcomp.cust.action.ctrl_g.regex.perform.findexp.ask;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntityImpl implements Entity, ActionListener, G {

	public String creationDate() {return "20160509";}
	
	public static final Dimension DIM = new Dimension(800,350);
	public static final int GAP = 10;

	public static final String KEY_ALPHANUM = "alphanum";
	public static final String KEY_ALPHANUM1 = "alphanum1";
	public static final String KEY_ANGLE = "<>";
	public static final String KEY_COUNTRY = "country";
	public static final String KEY_CURLY = "{}";
	public static final String KEY_DATE_EN = "date_en";
	public static final String KEY_DATE_FR = "date_fr";
	public static final String KEY_DIGIT = "digit";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_TEL = "tel";
	public static final String KEY_HEXA = "hexa";
	public static final String KEY_HIRAGANA = "hiragana";
	public static final String KEY_HTML_COLOR = "html_color";
	public static final String KEY_KANJI = "kanji";
	public static final String KEY_KATAKANA = "katakana";
	public static final String KEY_LANGUAGE = "language";
	public static final String KEY_LOWER = "lower";
	public static final String KEY_LOWER0 = "lower0";
	public static final String KEY_LOWER1 = "lower1";
	public static final String KEY_LOWER10 = "lower10";
	public static final String KEY_MONTH_FR = "month_fr";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_PARENTHESIS = "()";
	public static final String KEY_PUNCT = "punct";
	public static final String KEY_QUOTE = "\'\'";
	public static final String KEY_QUOTE2 = "\"\"";
	public static final String KEY_ROMAN_NUMBER = "roman_number";
	public static final String KEY_SQUARE = "[]";
	public static final String KEY_TAB = "tab";
	public static final String KEY_TITLED = "titled";
	public static final String KEY_UPPER = "upper";
	public static final String KEY_UPPER0 = "upper0";
	public static final String KEY_UPPER1 = "upper0";
	public static final String KEY_UPPER10 = "upper10";
	public static final String KEY_URL = "url";
	public static final String KEY_WHITE = "white";
	public static final String KEY_WORD = "word";
	public static final String KEY_WORD1 = "word1";
	public static final String KEY_YEAR = "year";
	public static final String KEY_YEARP = "yearp";
	public static final String KEY_K1 = "k1";
	public static final String KEY_K2 = "k2";

	private Service okCancel;
	private Service onEscape;

	private JTextField field;
	private JPanel panel1;
	private JPanel panel;
	



	public EntityImpl() throws Exception
	{
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel0");
		onEscape = Outside.service(this,"gus.x.swing.comp.cust3.execute.escape");
		
		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));
		
		onEscape.p(new Object[]{field,new E(){
			public void e() throws Exception {cancel();}
		}});
		
		panel1 = new JPanel(new GridLayout(0,6,5,5));
		panel1.setBorder(BorderFactory.createEmptyBorder(GAP,0,0,0));
		
		
		add(KEY_EMAIL,"E-mails","Search for Email addresses");
		add(KEY_URL,"URLs","Search for URL addresses");
		add(KEY_TEL,"Telephone (fr)","Search for telephone numbers");
		add(KEY_COUNTRY,"Countries","Search for countries");
		add(KEY_LANGUAGE,"Languages","Search for languages");
		add(KEY_HTML_COLOR,"HTML color","Search for HTML color codes");

		add(KEY_DATE_FR,"Dates (fr)","Search for french dates");
		add(KEY_DATE_EN,"Dates (US)","Search for US dates");
		add(KEY_YEAR,"Years","Search for years from 1000 until 2999");
		add(KEY_YEARP,"Years (P)","Search for years from 1900 until now");
		add(KEY_MONTH_FR,"Months (fr)","Search for months");
		add(KEY_ROMAN_NUMBER,"Roman numbers","Search for roman numbers");
		
		add(KEY_DIGIT,"Digits","Search for digit characters");
		add(KEY_NUMBER,"Integer numbers","Search for integer numbers");
		add(KEY_HEXA,"Hexadecimal","Search for hexadecimal numbers");
		add(KEY_ALPHANUM1,"Alphanum","Search for alphanumeric values");
		add(KEY_UPPER,"Uppercase","Search for uppercase words");
		add(KEY_LOWER,"Lowercase","Search for lowercase words");

		add(KEY_HIRAGANA,"Hiragana","Search for hiragana japanese characters");
		add(KEY_KATAKANA,"Katakana","Search for katakana japanese characters");
		add(KEY_KANJI,"Kanjis","Search for kanji characters");
		add(KEY_PUNCT,"Punct","Search for punctuation characters");
		add(KEY_WHITE,"Whitespaces","Search for whitespaces");
		add(KEY_TAB,"Tabs","Search for tabs");
		
		add(KEY_QUOTE,"' ... '","Search for areas surrounded by simple quotes");
		add(KEY_QUOTE2,"\" ... \"","Search for areas surrounded by double quotes");
		add(KEY_PARENTHESIS,"( ... )","Search for areas surrounded by parenthesis");
		add(KEY_SQUARE,"[ ... ]","Search for areas surrounded by square brackets");
		add(KEY_CURLY,"{ ... }","Search for areas surrounded by curly brackets");
		add(KEY_ANGLE,"< ... >","Search for areas surrounded by angle brackets");
		
		add(KEY_TITLED,"Titled","Search for titled words");
		add(KEY_K1,"<key> :","Search for keys delimited by :");
		add(KEY_K2,"<key> =","Search for keys delimited by =");
		
		
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		
		panel.add(field,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
		
		field.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{
		okCancel.v("dimension",DIM);
		boolean result = okCancel.f(panel);
		return result ? field.getText() : null;
	}
	
	
	private void add(String rule, String text, String tooltip)
	{
		panel1.add(new JButton1(rule,text,tooltip));
	}
	
	
	
	private class JButton1 extends JButton implements ActionListener
	{
		private String rule;
		
		public JButton1(String rule, String text, String tooltip)
		{
			super(text);
			setToolTipText(tooltip);
			this.rule = rule;
			
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			field.setText(rule);
			ok();
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{ok();}
	
	
	
	private void ok()
	{
		try{okCancel.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"ok()",e);}
	}

	private void cancel()
	{
		try{okCancel.v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}

}
