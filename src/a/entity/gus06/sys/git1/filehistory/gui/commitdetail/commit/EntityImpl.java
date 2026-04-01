package a.entity.gus06.sys.git1.filehistory.gui.commitdetail.commit;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201202";}


	private Service formatDate;

	private JPanel panel;
	private JTextPane0 area;
	
	private List commits;
	private Map commit;
	
	

	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.fr.format1");
		area = new JTextPane0();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		commits = (List) o[0];
		commit = (Map) o[1];
		
		area.setText("");
		
		String name = (String) commit.get("name");
		Date time = (Date) commit.get("time");
		String author = (String) commit.get("author");
		String email = (String) commit.get("email");
		String type = (String) commit.get("type");
		String message = (String) commit.get("message");
		G getParents = (G) commit.get("parents");
		G getBranches = (G) commit.get("branches");
		
		if(time==null) {reset();return;}
		
		List parents = (List) getParents.g();
		int parentNb = parents.size();
		
		List branches = (List) getBranches.g();
		String branchesStr = formatBranches(branches);
		
		area.appendBoldText("Name: ");
		area.appendText(name+"\n");
		
		area.appendBoldText("Parent nb: ");
		area.appendText(parentNb+"\n");
		
		area.appendBoldText("Time: ");
		area.appendText(formatDate.t(time)+"\n");
		
		area.appendBoldText("Author: ");
		area.appendText(author+"\n");
		
		area.appendBoldText("Email: ");
		area.appendText(email+"\n");
		
		area.appendBoldText("Type: ");
		area.appendText(type+"\n");
		
		area.appendBoldText("Branches: ");
		area.appendText(branchesStr+"\n");
		
		area.appendBoldText("Message: ");
		area.appendText(message.trim()+"\n");
	}
	
	
	
	private String formatBranches(List branches) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<branches.size();i++)
		{
			String branch = (String) branches.get(i);
			String[] n = branch.split("/");
			b.append(n[n.length-1]);
			if(i<branches.size()-1) b.append(", ");
		}
		return b.toString();
	}
	
	
	private void reset() throws Exception
	{
		commits = null;
		commit = null;
		area.setText("");
	}
	
	
	
	private class JTextPane0 extends JTextPane
	{
		private StyledDocument doc;
		private SimpleAttributeSet attr;
		
		public JTextPane0()
		{
			super();
			doc = getStyledDocument();
			attr = new SimpleAttributeSet();
		}
		
		public void initBold(boolean val)
		{StyleConstants.setBold(attr,val);}
		
		public void initItalic(boolean val)
		{StyleConstants.setItalic(attr,val);}
		 
		public void initUnderline(boolean val)
		{StyleConstants.setUnderline(attr,val);}
		
		public void initForeground(Color color)
		{StyleConstants.setForeground(attr,color);}
		
		public void initBackground(Color color)
		{StyleConstants.setBackground(attr,color);}
		
		public void appendText(String text) throws BadLocationException
		{doc.insertString(doc.getLength(),text,attr);}
		
		public void positionToEnd()
		{setCaretPosition(doc.getLength());}
		
		
		public void appendText(String text, Color color, boolean isBold, boolean isItalic) throws BadLocationException
		{
			if(isBold) initBold(true);
			if(isItalic) initItalic(true);
			initForeground(color);
			
			appendText(text);
			
			initForeground(Color.BLACK);
			initBold(false);
			initItalic(false);
		}
		
		
		public void appendBoldText(String text) throws BadLocationException
		{
			initBold(true);
			appendText(text);
			initBold(false);
		}
		
		public void setCaretToEnd()
		{setCaretPosition(doc.getLength());}
		
		public void setSize(Dimension d)
		{
			if(d.width < getParent().getSize().width)
			d.width = getParent().getSize().width;
			super.setSize(d);
		}
			
		public boolean getScrollableTracksViewportWidth()
		{return false;}
	}
}