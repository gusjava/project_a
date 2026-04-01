package a.entity.gus06.io.outputstream.textpane1.shell.append;

import a.framework.*;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180402";}

	public static final boolean RAW = false;
	public static final boolean DEBUG = false;
	
	
	public void p(Object obj) throws Exception
	{
//		System.out.println("RAW="+RAW+" & DEBUG="+DEBUG+" hascode="+EntityImpl.class.hashCode());
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextPane gui = (JTextPane) o[0];
		String text = (String) o[1];
		
		StyledDocument doc = gui.getStyledDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		
		if(RAW)
		{
			doc.insertString(doc.getLength(),text,attr);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean insideTag = false;
		
		int len = text.length();
		for(int i=0;i<len;i++)
		{
			char c = text.charAt(i);
			if(c=='\u001b')
			{
				if(sb.length()>0)
				{
					doc.insertString(doc.getLength(),sb.toString(),attr);
					sb.setLength(0);
				}
				insideTag = true;
			}
			else if(c=='m' && insideTag)
			{
				if(DEBUG)
				{
					resetAttr(attr,gui);
					setForeground(attr,Color.YELLOW);
					doc.insertString(doc.getLength(),"{"+sb.toString()+"}",attr);
				}
				initAttr(sb.toString(),attr,gui);
				sb.setLength(0);
				insideTag = false;
			}
			else sb.append(c);
		}
		
		if(!insideTag)
		doc.insertString(doc.getLength(),sb.toString(),attr);
		
		gui.setCaretPosition(gui.getDocument().getLength());
	}
	
	
	private void initAttr(String tag, SimpleAttributeSet attr, JTextPane gui)
	{
		tag = tag.replace("[","");
		String[] nn = tag.split(";");
		
		for(String n:nn) if(!n.trim().equals(""))
		initAttr2(n,attr,gui);
	}
	
	private void initAttr2(String part, SimpleAttributeSet attr, JTextPane gui)
	{
		try
		{
			int val = Integer.parseInt(part);
			switch(val)
			{
				case 0:resetAttr(attr,gui);break;
				case 1:StyleConstants.setBold(attr,true);break;
				case 4:StyleConstants.setUnderline(attr,true);break;
				case 7:invertBackgroundForeground(attr);break;
				
				case 30:setForeground(attr,Color.BLACK);break;
				case 31:setForeground(attr,Color.RED);break;
				case 32:setForeground(attr,Color.GREEN);break;
				case 33:setForeground(attr,Color.YELLOW);break;
				case 34:setForeground(attr,Color.BLUE);break;
				case 35:setForeground(attr,Color.MAGENTA);break;
				case 36:setForeground(attr,Color.CYAN);break;
				case 37:setForeground(attr,Color.GRAY);break;
				
				case 40:setBackground(attr,Color.BLACK);break;
				case 41:setBackground(attr,Color.RED);break;
				case 42:setBackground(attr,Color.GREEN);break;
				case 43:setBackground(attr,Color.YELLOW);break;
				case 44:setBackground(attr,Color.BLUE);break;
				case 45:setBackground(attr,Color.MAGENTA);break;
				case 46:setBackground(attr,Color.CYAN);break;
				case 47:setBackground(attr,Color.GRAY);break;
				
				default: throw new Exception("Unsupported value: "+val);
			}
		}
		catch(Exception e)
		{Outside.err(this,"initAttr2(String,SimpleAttributeSet,JTextPane)",e);}
	}
	
	
	private void resetAttr(SimpleAttributeSet attr, JTextPane gui)
	{
		StyleConstants.setBold(attr,false);
		StyleConstants.setItalic(attr,false);
		StyleConstants.setUnderline(attr,false);
		
		StyleConstants.setForeground(attr,gui.getForeground());
		StyleConstants.setBackground(attr,gui.getBackground());
	}
	
	private void invertBackgroundForeground(SimpleAttributeSet attr)
	{
		Color bg = StyleConstants.getBackground(attr);
		Color fg = StyleConstants.getForeground(attr);
		
		setForeground(attr,bg);
		setBackground(attr,fg);
	}
	
	private void setForeground(SimpleAttributeSet attr, Color c)
	{StyleConstants.setForeground(attr,c);}
	
	private void setBackground(SimpleAttributeSet attr, Color c)
	{StyleConstants.setBackground(attr,c);}
}