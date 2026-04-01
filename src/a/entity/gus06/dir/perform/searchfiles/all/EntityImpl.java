package a.entity.gus06.dir.perform.searchfiles.all;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180304";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object input = obj;
		
		List output = new ArrayList();
		handleObj(output,input);
		return output;
	}
	
	
	private void handleObj(List output, Object input) throws Exception
	{
		if(input instanceof File) handleFile(output, (File) input);
		else if(input instanceof List) handleList(output, (List) input);
		else if(input instanceof File[]) handleArray(output, (File[]) input);
	}
	
	private void handleList(List output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		handleFile(output, (File) input.get(i));
	}
	
	private void handleArray(List output, File[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		handleFile(output, input[i]);
	}
	
	private void handleFile(List output, File input) throws Exception
	{
		if(input.isDirectory()) handleDir(output,input);
		else output.add(input);
	}
	
	private void handleDir(List output, File input) throws Exception
	{
		File[] ff = input.listFiles();
		for(int i=0;i<ff.length;i++)
		handleFile(output,ff[i]);
	}
}