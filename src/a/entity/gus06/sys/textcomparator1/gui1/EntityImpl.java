package a.entity.gus06.sys.textcomparator1.gui1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridLayout;

public class EntityImpl implements Entity, P, I, R, E {

	public String creationDate() {return "20200209";}

	public static final String KEY_ENDS = "ends";
	public static final String KEY_STARTS = "starts";
	public static final String KEY_BEFORE = "before";
	public static final String KEY_AFTER = "after";
	public static final String KEY_SAME = "same";

	public static final Color COLOR_BEFORE = new Color(255,153,153);
	public static final Color COLOR_AFTER = new Color(153,255,153);
	
	public static final Color COLOR_RULE_BG = new Color(240,240,240);
	public static final Color COLOR_RULE_FG = new Color(153,153,153);


	private Service bgHolder;
	private Service grouping;
	private Service readFile;
	private Service scrollPainter;


	private JTextArea area;
	private JTextArea rule1;
	private JTextArea rule2;
	
	private JScrollPane scroll;
	private JLabel label;
	private JPanel panel;
	
	private String text1;
	private String text2;
	
	private String[] lines1;
	private String[] lines2;
	
	private List blocks;
	

	public EntityImpl() throws Exception
	{
		bgHolder = Outside.service(this,"*gus06.sys.textcomparator1.textarea.bg.holder");
		grouping = Outside.service(this,"gus06.sys.textcomparator1.grouping");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		scrollPainter = Outside.service(this,"gus06.sys.textcomparator1.scrollpainter");
		
		area = (JTextArea) bgHolder.i();
		area.setEditable(false);
		area.setMargin(new Insets(2,2,2,2));
		area.getCaret().setVisible(true);
		area.getCaret().setBlinkRate(0);
		
		rule1 = new JTextArea();
		rule1.setEditable(false);
		rule1.setMargin(new Insets(2,2,2,2));
		rule1.setBackground(COLOR_RULE_BG);
		rule1.setForeground(COLOR_RULE_FG);
		
		rule2 = new JTextArea();
		rule2.setEditable(false);
		rule2.setMargin(new Insets(2,2,2,2));
		rule2.setBackground(COLOR_RULE_BG);
		rule2.setForeground(COLOR_RULE_FG);
		
		
		JPanel ruler = new JPanel(new GridLayout(1,2));
		ruler.add(rule1);
		ruler.add(rule2);
		
		scroll = new JScrollPane(area);
		scroll.setRowHeaderView(ruler);
		scrollPainter.p(new Object[]{scroll,bgHolder});
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void e() throws Exception
	{reset();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("text1")) return text1;
		if(key.equals("text2")) return text2;
		if(key.equals("lines1")) return lines1;
		if(key.equals("lines2")) return lines2;
		if(key.equals("blocks")) return blocks;
		if(key.equals("comp")) return area;
		
		if(key.equals("keys"))
		return new String[]{"text1","text2","lines1","lines2","blocks","comp"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		reset();
		
		text1 = toString(o[0]);
		text2 = toString(o[1]);
		
		lines1 = toLines(text1);
		lines2 = toLines(text2);
		
		blocks = (List) grouping.t(new Object[]{lines1,lines2});
		if(blocks==null) throw new Exception("Block building failed");
		
		int removed = 0;
		int added = 0;
		int modified = 0;
		
		RuleContent ruleContent = new RuleContent();
		
		for(int i=0;i<blocks.size();i++)
		{
			Map block = (Map) blocks.get(i);
			
			List same = (List) get(block,KEY_SAME);
			List before = (List) get(block,KEY_BEFORE);
			List after = (List) get(block,KEY_AFTER);
			
			if(same!=null && before!=null) 
			throw new Exception("Invalid block data: same size="+same.size()+" & before.size="+before.size());
			
			if(same!=null && after!=null) 
			throw new Exception("Invalid block data: same size="+same.size()+" & after.size="+after.size());
			
			
			bgHolder.v("hr",Color.BLACK);
			
			if(same!=null) for(int j=0;j<same.size();j++)
			{
				String line = (String) same.get(j);
				bgHolder.p(line);
				ruleContent.addSame();
			}
			if(before!=null) for(int j=0;j<before.size();j++)
			{
				String line = (String) before.get(j);
				bgHolder.p(new Object[]{line,COLOR_BEFORE});
				ruleContent.addBefore();
			}
			if(after!=null) for(int j=0;j<after.size();j++)
			{
				String line = (String) after.get(j);
				bgHolder.p(new Object[]{line,COLOR_AFTER});
				ruleContent.addAfter();
			}
			
			if(before!=null && after!=null) modified++;
			else if(before!=null) removed++;
			else if(after!=null) added++;
		}
		bgHolder.v("hr",Color.BLACK);
		
		label.setText(labelDisplay(added,removed,modified));
		
		rule1.setText(ruleContent.getContent1());
		rule2.setText(ruleContent.getContent2());
		
		area.setCaretPosition(0);
		rule1.setCaretPosition(0);
		rule2.setCaretPosition(0);
		
		scroll.repaint();
	}
	
	
	
	
	private void addBlock(List list, Color color) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			String line = (String) list.get(i);
			if(line.contains("\n")) throw new Exception("Invalid line: ["+line+"]");
			bgHolder.p(new Object[]{line,color});
		}
	}
	
	
	
	private Object get(Map block, String key)
	{
		if(!block.containsKey(key)) return null;
		return block.get(key);
	}
	
	
	private void reset() throws Exception
	{
		text1 = null;
		text2 = null;
		lines1 = null;
		lines2 = null;
		blocks = null;
		bgHolder.e();
		scroll.repaint();
	}
	
	
	private String toString(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) readFile.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] toLines(String text)
	{
		if(text==null) return new String[]{};
		return text.split("\n",-1);
	}
	
	
	private String labelDisplay(int added, int removed, int modified)
	{
		StringBuffer b = new StringBuffer();
		if(added>0) b.append(" +"+added);
		if(removed>0) b.append(" -"+removed);
		if(modified>0) b.append(" ~"+modified);
		return b.toString();
	}
	
	
	
	private class RuleContent
	{
		private int index1 = 0;
		private int index2 = 0;
		
		private StringBuffer b1 = new StringBuffer();
		private StringBuffer b2 = new StringBuffer();
		
		public void addSame()
		{
			b1.append(index1+"\n");
			b2.append(index2+"\n");
			index1++;
			index2++;
		}
		
		public void addBefore()
		{
			b1.append(index1+"\n");
			b2.append("\n");
			index1++;
		}
		
		public void addAfter()
		{
			b1.append("\n");
			b2.append(index2+"\n");
			index2++;
		}
		
		public String getContent1()
		{return b1.toString();}
		
		public String getContent2()
		{return b2.toString();}
	}
}