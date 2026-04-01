package a.entity.gus06.dir.perform.searchfiles.filtername;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151202";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		F filter = (F) o[1];
		
		List output = new ArrayList();
		handleObj(filter,output,input);
		return output;
	}
	
	
	private void handleObj(F filter, List output, Object input) throws Exception
	{
		if(input instanceof File) handleFile(filter, output, (File) input);
		else if(input instanceof List) handleList(filter, output, (List) input);
		else if(input instanceof File[]) handleArray(filter, output, (File[]) input);
	}
	
	private void handleList(F filter, List output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		handleFile(filter, output, (File) input.get(i));
	}
	
	private void handleArray(F filter, List output, File[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		handleFile(filter, output, input[i]);
	}
	
	private void handleDir(F filter, List output, File input) throws Exception
	{
		File[] ff = input.listFiles();
		for(int i=0;i<ff.length;i++)
		handleFile(filter,output,ff[i]);
	}
	
	private void handleFile(F filter, List output, File input) throws Exception
	{
		if(input.isDirectory()) handleDir(filter,output,input);
		else if(filter.f(input.getName())) output.add(input);
	}
}