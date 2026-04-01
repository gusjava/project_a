package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_slash.comment.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160830";}

	public static final String C_START = "/*";
	public static final String C_END = "*/";
	public static final String C_LINE = "//";
	public static final char X_LINE = '/';



	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		new Holder((JTextComponent) obj);
	}
	
	
	
	private class Holder
	{
		private JTextComponent comp;
		private PlainDocument document;
		private String text;
		private String selected;
		private int selectionStart;
		private int selectionEnd;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			document = (PlainDocument) comp.getDocument();
			text = comp.getText();
			selected = comp.getSelectedText();
			selectionStart = comp.getSelectionStart();
			selectionEnd = comp.getSelectionEnd();
			
			if(isMonoSelection()) handle1();
			else handle2();
		}
		
		private boolean isMonoSelection()
		{
			if(selected==null || selected.length()==0) return false;
			if(selected.contains("\n")) return false;
			if(isLineStart(selectionStart) && isLineEnd(selectionEnd)) return false;
			return true;
		}
		
		private boolean isLineStart(int n)
		{return n==0 || text.charAt(n-1)=='\n';}
		
		private boolean isLineEnd(int n)
		{return n==text.length() || text.charAt(n)=='\n';}
		
		
		
		private void handle1() throws Exception
		{
			String text1 = isComment1(selected) ? decomment1(selected) : comment1(selected);
			
			document.remove(selectionStart,selectionEnd-selectionStart);
			document.insertString(selectionStart,text1,null);
			comp.select(selectionStart,selectionStart+text1.length());
		}
		
		private boolean isComment1(String s)
		{return s!=null && s.startsWith(C_START) && s.endsWith(C_END);}
		
		private String decomment1(String s)
		{return s.substring(2,selected.length()-2);}
		
		private String comment1(String s)
		{return C_START+s+C_END;}
		
		
		
		
		private void handle2() throws Exception
		{
			int length = document.getLength();
			Element root = document.getDefaultRootElement();
			
			Element element1 = document.getParagraphElement(comp.getSelectionStart());
			Element element2 = document.getParagraphElement(comp.getSelectionEnd());
			
			int blockStart = element1.getStartOffset();
			int blockEnd = element2.getEndOffset();
			
			boolean endReached = blockEnd>length;
			if(endReached) blockEnd = length;
			
			String text = comp.getText(blockStart,blockEnd-blockStart);
			String text1 = text.startsWith(C_LINE) ? decomment2(text) : comment2(text);
			
			int textLen = text.length();
			int text1Len = text1.length();
			int length1 = length - textLen + text1Len;
			
			int blockEnd1 = blockStart + text1Len - 1;
			if(endReached) blockEnd1++;
			
			document.remove(blockStart,blockEnd-blockStart);
			document.insertString(blockStart,text1,null);
			comp.select(blockStart,blockEnd1);
		}
		
		private String decomment2(String s)
		{
			StringBuffer b = new StringBuffer();
			boolean isStart = true;
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c==X_LINE)
				{
					if(!isStart) b.append(c);
				}
				else if(c=='\n')
				{
					b.append(c);
					isStart = true;
				}
				else
				{
					b.append(c);
					isStart = false;
				}
			}
			return b.toString();
		}
		
		private String comment2(String s)
		{
			StringBuffer b = new StringBuffer();
			int n = 0;
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c=='/')
				{
					if(n==0 || n==1)
					{
						n++;
						b.append(c);
					}
					else if(n==2)
					{
						
					}
					else b.append(c);
				}
				else if(c=='\n')
				{
					if(n==0) b.append(C_LINE);
					b.append(c);
					n = 0;
				}
				else
				{
					if(n==0) b.append(C_LINE);
					b.append(c);
					n = -1;
				}
			}
			return b.toString();
		}
	}
}