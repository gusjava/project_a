package a.entity.gus06.dir.perform.mimic.searchfiles.all;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}
	
	
	public Object t(Object obj) throws Exception
	{
		File[] roots = (File[]) obj;
		
		List output = new ArrayList();
		handle(output,roots,roots);
		return output;
	}
	
	private void handle(List output, File[] roots, File[] p) throws Exception
	{
		if(p[0].isDirectory()) handleDir(output,roots,p);
		else output.add(p);
	}
	
	private void handleDir(List output, File[] roots, File[] p) throws Exception
	{
		File[] ff = p[0].listFiles();
		int len = roots[0].getAbsolutePath().length();
		
		for(int i=0;i<ff.length;i++)
		{
			File f = ff[i];
			String fPath = f.getAbsolutePath().substring(len);
			
			File[] c = new File[roots.length];
			for(int j=0;j<roots.length;j++)
			c[j] = new File(roots[j]+fPath);
			
			handle(output,roots,c);
		}
	}
}
