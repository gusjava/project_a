package a.entity.gus06.string.perform.permutations;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170424";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List output = new ArrayList();
		permutations(output,"",s);
		return output;
	}
	
	
	private void permutations(List output, String perm, String input)
	{
		int len = input.length();
		if(len==0) output.add(perm);
		else for(int i=0;i<len;i++)
		{
			String newPerm = perm + input.charAt(i);
			String newInput = input.substring(0,i)+input.substring(i+1,len);
			permutations(output, newPerm, newInput);
		}
	}
}
