package a.entity.gus06.sys.autocomplete1.highlighter1.el.line;

import a.framework.*;
import javax.swing.text.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160519";}

	public static final String PAINTER_KEY = "f1";


	private Service getPainter;
	private DefaultHighlighter.DefaultHighlightPainter painter;


	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Highlighter high = comp.getHighlighter();
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int count = root.getElementCount();
		
		Set<Integer> numbers = findNumbers(count,key);
		List<Integer> numbers1 = new ArrayList<>(numbers);
		Collections.sort(numbers1);
		
		for(Integer lineNb:numbers1)
		{
			int index = lineNb>0?lineNb-1:lineNb; 
			Element element = root.getElement(index);
			
			int start = element.getStartOffset();
			int end = element.getEndOffset();
			high.addHighlight(start,end-1,painter);
		}
	}
	
	
	
	private Set<Integer> findNumbers(int count, String rule) throws Exception
	{
		Set<Integer> set = new HashSet<>();
		String[] nn = rule.split(";");
		for(String n:nn) handle1(set,count,n);
		return set;
	}
	
	private void handle1(Set<Integer> set, int count, String rule) throws Exception
	{
		String[] nn = rule.split("\\|");
		String rule0 = nn[0];
		int[] skip = new int[nn.length-1];
		for(int i=1;i<nn.length;i++) skip[i-1] = toInt(nn[i],count);
		handle2(set,count,rule0,skip);
	}
	
	private void handle2(Set<Integer> set, int count, String rule, int[] skip) throws Exception
	{
		if(rule.equals("")) return;
		
		if(rule.equals("..") || rule.equals("all"))
		{
			handleAll(set,count,skip);
			return;
		}
		
		if(rule.equals("2n") || rule.equals("even"))
		{
			handleEven(set,count,skip);
			return;
		}
		
		if(rule.equals("2n+1") || rule.equals("odd"))
		{
			handleOdd(set,count,skip);
			return;
		}
		
		if(rule.contains("n+"))
		{
			String[] n = rule.split("n\\+");
			int n1 = toInt(n[0]);
			int n2 = toInt(n[1]);
			handleNN(set,count,n1,n2,skip);
			return;
		}
		
		if(isInt(rule))
		{
			int n = toInt(rule,count);
			if(n<0 || n>=count) throw new Exception("Invalid subsequence rule: "+rule);
			if(!has(skip,n)) set.add(Integer.valueOf(n));
			return;
		}
		
		if(rule.startsWith("..")) rule = "0"+rule;
		if(rule.endsWith("..")) rule = rule+"-1";
		
		String[] m = rule.split("\\.\\.");
		if(m.length!=2) throw new Exception("Invalid subsequence rule: "+rule);
		
		int start = toInt(m[0],count);
		if(start<0 || start>=count) throw new Exception("Invalid subsequence rule: "+rule);
		
		int end = toInt(m[1],count);
		if(end<0 || end>=count) throw new Exception("Invalid subsequence rule: "+rule);
		
		if(start<=end)
		{
			for(int i=start;i<=end;i++)
			if(!has(skip,i)) set.add(Integer.valueOf(i));
		}
		else
		{
			for(int i=start;i>=end;i--)
			if(!has(skip,i)) set.add(Integer.valueOf(i));
		}
	}
	
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	
	private void handleAll(Set<Integer> set, int count, int[] skip)
	{
		for(int i=0;i<count;i++)
		if(!has(skip,i)) set.add(Integer.valueOf(i));
	}
	
	
	private void handleEven(Set<Integer> set, int count, int[] skip)
	{
		for(int i=0;i<count;i++)
		if(i%2==0 && !has(skip,i)) set.add(Integer.valueOf(i));
	}
	
	private void handleOdd(Set<Integer> set, int count, int[] skip)
	{
		for(int i=0;i<count;i++)
		if(i%2==1 && !has(skip,i)) set.add(Integer.valueOf(i));
	}
	
	private void handleNN(Set<Integer> set, int count, int n1, int n2, int[] skip)
	{
		for(int i=n2;i<count;i++)
		if((i-n2)%n1==0 && !has(skip,i)) set.add(Integer.valueOf(i));
	}
	
	
	
	
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
	
	
	private int toInt(String s, int l)
	{
		int n = toInt(s);
		return n<0? n+l:n;
	}
	
	private boolean has(int[] nn, int v)
	{
		for(int n:nn) if(n==v) return true;
		return false;
	}
}
