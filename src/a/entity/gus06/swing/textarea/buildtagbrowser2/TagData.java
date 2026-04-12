package a.entity.gus06.swing.textarea.buildtagbrowser2;

import java.util.Vector;
import javax.swing.tree.TreePath;
import java.util.Collections;

public class TagData
{
	private Vector tags;
	private Vector tails;
	private char delim;
		
	
	public TagData(char delim)
	{
		this.delim = delim;
		
		tags = new Vector();
		tails = new Vector();
	}
	
	// GET
	
	public Vector getTags()
	{return tags;}
	
	public Vector getTails()
	{return tails;}
	
	// SIZE
	
	public int size()
	{return tags.size();}
	
	// AT
	
	public String tagAt(int index)
	{
		if(index>=tags.size()) return null;
		return (String) tags.get(index);
	}
		
	public String tailAt(int index)
	{
		if(index>=tails.size()) return null;
		return (String) tails.get(index);
	}
	
	// SET AT
	
	public void setTagAt(int index, String tag)
	{
		tags.set(index,tag);
	}
	
	// APPEND TAG AT
	
	public void appendTagAt(int index, String lastPart)
	{
		String newTag = tagAt(index)+lastPart;
		setTagAt(index, newTag);
	}
	
	// SWITCH TAG END AT
	
	public void switchTagEndAt(int index, String lastPart)
	{
		String tag = tagAt(index);
		String newTag = tag.endsWith(lastPart) ? tag.substring(0,tag.length()-lastPart.length()) : tag+lastPart;
		setTagAt(index, newTag);
	}
	
	// REMOVE AT
	
	public void removeAt(int index)
	{
			tags.remove(index);
			tails.remove(index);
	}
	
	// ADD AT
	
	public void addAt(int index, String tag, String tail)
	{
			tags.add(index,tag);
			tails.add(index,tail);
	}
	
	// ROTATE
	
	public void rotate(int fromIndex, int toIndex, int distance)
	{
			Collections.rotate(tags.subList(fromIndex, toIndex), distance);
			Collections.rotate(tails.subList(fromIndex, toIndex), distance);
	}
	
	// INIT
	
	public void init(Vector tags, Vector tails) throws Exception
	{
		if(tags.size()!=tails.size()) throw new Exception("Invalid different sizes for tags and tails ("+tags.size()+" & "+tails.size()+")");
		this.tags = tags;
		this.tails = tails;
	}
	
	
	public int levelFor(String tag)
	{
		if(tag==null) return -1;
		int level = 0;
		for(int i=0;i<tag.length();i++)
		{
			char c = tag.charAt(i);
			if(c!=delim) return level;
			level++;
		}
		return level;
	}
	
	public int levelAt(int index)
	{
		String tag = tagAt(index);
		return levelFor(tag);
	}
	
	public String nameAt(int index)
	{
		String tag = tagAt(index);
		int level = levelFor(tag);
		return tag.substring(level);
	}
	
	public int findBlockEnd(int index)
	{
		int level0 = levelAt(index);
		int tagNb = size();
		for(int i=index+1;i<tagNb;i++)
		{
			int level = levelAt(i);
			if(level<=level0) return i-1;
		}
		return tagNb-1;
	}
	
	public int findParent(int index)
	{
		int parentLevel = levelAt(index)-1;
		for(int i=index-1;i>=0;i--)
		{
			int level = levelAt(i);
			if(level==parentLevel) return i;
		}
		return -1;
	}
	
	public int findNextTagWithSameLevel(int index0)
	{
		int tagNb = size();
		int level0 = levelAt(index0);
		for(int i=index0+1;i<tagNb;i++)
		{
			int level = levelAt(i);
			if(level==level0) return i;
		}
		return -1;
	}
	
	
	public int findPreviousTagWithSameLevel(int index0)
	{
		int level0 = levelAt(index0);
		for(int i=index0-1;i>=0;i--)
		{
			int level = levelAt(i);
			if(level==level0) return i;
		}
		return -1;
	}
	
	
	public void increaseLevelAt(int index)
	{
		String tag = tagAt(index);
		setTagAt(index,delim+tag);
	}
	
	
	public boolean decreaseLevelAt(int index)
	{
		String tag = tagAt(index);
		if(tag.charAt(0)!=delim) return false;
		setTagAt(index,tag.substring(1));
		return true;
	}
	
	public String rebuildTag(String name, int level)
	{
		StringBuilder b = new StringBuilder();
		for(int i=0;i<level;i++) b.append(delim);
		b.append(name);
		return b.toString();
	}
}