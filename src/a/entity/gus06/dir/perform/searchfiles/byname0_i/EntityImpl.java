package a.entity.gus06.dir.perform.searchfiles.byname0_i;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160520";}


	private Service getName0;
	
	public EntityImpl() throws Exception
	{
		getName0 = Outside.service(this,"gus06.file.getname0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String name = ((String) o[1]).toLowerCase();
		
		List output = new ArrayList();
		handleObj(name,output,input);
		return output;
	}
	
	
	private void handleObj(String name, List output, Object input) throws Exception
	{
		if(input instanceof File) handleFile(name, output, (File) input);
		else if(input instanceof List) handleList(name, output, (List) input);
		else if(input instanceof File[]) handleArray(name, output, (File[]) input);
	}
	
	private void handleList(String name, List output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		handleFile(name, output, (File) input.get(i));
	}
	
	private void handleArray(String name, List output, File[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		handleFile(name, output, input[i]);
	}
	
	private void handleFile(String name, List output, File input) throws Exception
	{
		if(input.isDirectory()) handleDir(name,output,input);
		else if(filter(input,name)) output.add(input);
	}
	
	private void handleDir(String name, List output, File input) throws Exception
	{
		File[] ff = input.listFiles();
		for(int i=0;i<ff.length;i++)
		handleFile(name,output,ff[i]);
	}
	
	private boolean filter(File file, String name) throws Exception
	{
		String name_ = (String) getName0.t(file);
		return name_.toLowerCase().equals(name);
	}
}