package a.entity.gus06.dir.perform.searchdirs.all;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180304";}
	
	
	public Object t(Object obj) throws Exception
	{
		File input =(File) obj;
		
		List output = new ArrayList();
		handle(output,input);
		return output;
	}
	
	private void handle(List output, File input) throws Exception
	{
		if(input.isDirectory())
		{
			output.add(input);
			
			File[] ff = input.listFiles();
			for(int i=0;i<ff.length;i++)
			handle(output,ff[i]);
		}
	}
}
