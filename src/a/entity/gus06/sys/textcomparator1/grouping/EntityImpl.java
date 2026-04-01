package a.entity.gus06.sys.textcomparator1.grouping;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190616";}
	
	public static final String KEY_ENDS = "ends";
	public static final String KEY_STARTS = "starts";
	public static final String KEY_BEFORE = "before";
	public static final String KEY_AFTER = "after";
	public static final String KEY_SAME = "same";


	private Service segmentation;

	public EntityImpl() throws Exception
	{
		segmentation = Outside.service(this,"gus06.sys.textcomparator1.segmentation");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[] lines1 = toLines(o[0]);
		String[] lines2 = toLines(o[1]);
		
		int nb1 = lines1.length;
		int nb2 = lines2.length;
		
		if(nb1==0)
		{
			List blocks = new ArrayList();
			Map block = newBlock(blocks);
			for(int i=0;i<nb2;i++) addAfter(block,lines2[i]);
			return blocks;
		}
		
		if(nb2==0)
		{
			List blocks = new ArrayList();
			Map block = newBlock(blocks);
			for(int i=0;i<nb1;i++) addBefore(block,lines1[i]);
			return blocks;
		}
		
		String[] lines1_ = new String[nb1];
		String[] lines2_ = new String[nb2];
		
		for(int i=0;i<nb1;i++) lines1_[i] = lines1[i].trim();
		for(int i=0;i<nb2;i++) lines2_[i] = lines2[i].trim();
		
		Object[] pos = (Object[]) segmentation.t(new Object[]{lines1_,lines2_});
		if(pos==null)
		{
			List blocks = new ArrayList();
			Map block = newBlock(blocks);
			for(int i=0;i<nb1;i++) addSame(block,lines1[i]);
			return blocks;
		}
		
		int[] pos2 = (int[]) pos[1];
		
		List blocks = new ArrayList();
		Map block = newBlock(blocks);
		
		for(int i=0;i<nb2;i++)
		{
			int p = pos2[i];
			if(p==-1)
			{
				if(hasSame(block)) block = newBlock(blocks);
				addAfter(block,lines2[i]);
			}
			else
			{
				if(i==0)
				{
					if(p>0)
					{
						for(int j=0;j<p;j++) 
						addBefore(block,lines1[j]);
					}
				}
				else
				{
					int pp = pos2[i-1];
					if(pp==-1)
					{
						if(hasSame(block)) block = newBlock(blocks);
						for(int j=getStart1(block);j<p;j++) 
						addBefore(block,lines1[j]);
					}
					else if(p>pp+1)
					{
						if(hasSame(block)) block = newBlock(blocks);
						for(int j=pp+1;j<p;j++)
						addBefore(block,lines1[j]);
					}
				}
				
				if(hasBefore(block) || hasAfter(block)) block = newBlock(blocks);
				addSame(block,lines2[i]);
			}
		}
		
		if(block!=null)
		{
			if(pos2[nb2-1]==-1)
			for(int i=getStart1(block);i<nb1;i++) 
			addBefore(block,lines1[i]);
			
			setEnds(block,nb1,nb2);
		}
		return blocks;
	}
	
	
	
	
	
	private Map newBlock(List blocks)
	{
		Map previous = blocks.size()>0 ? (Map) blocks.get(blocks.size()-1) : null;
		Map block = new HashMap();
		blocks.add(block);
		
		if(previous==null)
		{
			setStarts(block,0,0);
		}
		else if(hasSame(previous))
		{
			int nb = getSame(previous).size();
			int[] starts = getStarts(previous);
			int v1 = starts[0]+nb;
			int v2 = starts[1]+nb;
			
			setEnds(previous,v1,v2);
			setStarts(block,v1,v2);
		}
		else
		{
			int nb1 = hasBefore(previous) ? getBefore(previous).size() : 0;
			int nb2 = hasAfter(previous) ? getAfter(previous).size() : 0;
			int[] starts = getStarts(previous);
			int v1 = starts[0]+nb1;
			int v2 = starts[1]+nb2;
			
			setEnds(previous,v1,v2);
			setStarts(block,v1,v2);
		}
		return block;
	}
	
	
	
	private void addBefore(Map block, String line)
	{add(block,KEY_BEFORE,line);}
	
	private void addAfter(Map block, String line)
	{add(block,KEY_AFTER,line);}
	
	private void addSame(Map block, String line)
	{add(block,KEY_SAME,line);}
	
	private void add(Map block, String key, String line)
	{
		if(!has(block,key)) block.put(key,new ArrayList());
		get(block,key).add(line);
	}
	
	
	
	private boolean hasBefore(Map block)
	{return has(block,KEY_BEFORE);}
	
	private boolean hasAfter(Map block)
	{return has(block,KEY_AFTER);}
	
	private boolean hasSame(Map block)
	{return has(block,KEY_SAME);}
	
	private boolean has(Map block, String key)
	{return block.containsKey(key);}
	
	
	
	private List getBefore(Map block)
	{return get(block,KEY_BEFORE);}
	
	private List getAfter(Map block)
	{return get(block,KEY_AFTER);}
	
	private List getSame(Map block)
	{return get(block,KEY_SAME);}
	
	private List get(Map block, String key)
	{return (List) block.get(key);}
	
	
	
	private int[] getStarts(Map block)
	{return (int[]) block.get(KEY_STARTS);}
	
	private int getStart1(Map block)
	{return getStarts(block)[0];}
	
	private int getStart2(Map block)
	{return getStarts(block)[1];}
	
	
	
	private int[] getEnds(Map block)
	{return (int[]) block.get(KEY_ENDS);}
	
	private int getEnd1(Map block)
	{return getEnds(block)[0];}
	
	private int getEnd2(Map block)
	{return getEnds(block)[1];}
	
	
	
	private void setStarts(Map block, int start1, int start2)
	{block.put(KEY_STARTS,new int[]{start1,start2});}
	
	private void setEnds(Map block, int end1, int end2)
	{block.put(KEY_ENDS,new int[]{end1,end2});}
	
	
	
	private String[] toLines(Object obj) throws Exception
	{
		if(obj==null) return new String[]{};
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split("\n",-1);;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}