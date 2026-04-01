package a.entity.gus06.string.type.unicodeblocks;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170118";}

	
	public Object t(Object obj) throws Exception
	{
		String str = (String) obj;
		Set blocks = new HashSet();
		
		for(int i=0;i<str.length();i++)
		{
			char c = str.charAt(i);
			String block = Character.UnicodeBlock.of(c).toString();
			blocks.add(block);
		}
		return blocks;
	}
}
