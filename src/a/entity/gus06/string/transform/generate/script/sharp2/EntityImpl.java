package a.entity.gus06.string.transform.generate.script.sharp2;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180828";}
	
	

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("###",2);
		
		String script = n[0];
		String data = n[1].trim();
		
		String[] lines = data.split("\n",-1);
		int nb = lines.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String[] nn = lines[i].split("\t");
			
			String k = script;
			for(int j=0;j<nn.length;j++)
			k = k.replace("<"+j+">",nn[j]);
			
			b.append(k);
		}
		return b.toString();
	}
}
