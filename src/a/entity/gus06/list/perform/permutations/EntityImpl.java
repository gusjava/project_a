package a.entity.gus06.list.perform.permutations;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170424";}


	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		List output = new ArrayList();
		permutations(output,new ArrayList(),l);
		return output;
	}
	
	
	private void permutations(List output, List perm, List input)
	{
		int len = input.size();
		if(len==0) output.add(perm);
		else for(int i=0;i<len;i++)
		{
			List newPerm = add(perm,input.get(i));
			List newInput = remove(input,i);
			permutations(output, newPerm, newInput);
		}
	}
	
	
	public List add(List l, Object elem)
	{
		List list = new ArrayList(l);
		list.add(elem);
		return list;
	}
	
	
	private List remove(List l, int index)
	{
		List list = new ArrayList(l);
		list.remove(index);
		return list;
	}
}