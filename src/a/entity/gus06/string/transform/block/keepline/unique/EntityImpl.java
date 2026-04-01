package a.entity.gus06.string.transform.block.keepline.unique;

import a.framework.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231204";}
	
	public static final String DELIM = "\n";


	private Service buildBlocks;
	
	public EntityImpl() throws Exception
	{
		buildBlocks = Outside.service(this,"gus06.data.string.build.lineblocks");
	}

	
	public Object t(Object obj) throws Exception
	{
		List blocks = (List) buildBlocks.t(obj);
		int nb = blocks.size();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			List block = (List) blocks.get(i);
			for(int j=0;j<block.size();j++)
			{
				String line = (String) block.get(j);
				if(!foundInOtherBlock(blocks, j, line))
				{
					b.append(line);
					b.append(DELIM);
				}
			}
			b.append(DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private boolean foundInOtherBlock(List blocks, int j, String line)
	{
		int nb = blocks.size();
		for(int i=0;i<nb;i++) if(i!=j)
		{
			List block = (List) blocks.get(i);
			if(block.contains(line)) return true;
		}
		return false;
	}
}
