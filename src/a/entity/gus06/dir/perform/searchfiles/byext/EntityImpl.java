package a.entity.gus06.dir.perform.searchfiles.byext;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151202";}


	private Service isOfExt;
	
	public EntityImpl() throws Exception
	{
		isOfExt = Outside.service(this,"gus06.file.ext.isof");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String ext = ((String) o[1]).toLowerCase();
		
		List output = new ArrayList();
		handleObj(ext,output,input);
		return output;
	}
	
	
	private void handleObj(String ext, List output, Object input) throws Exception
	{
		if(input instanceof File) handleFile(ext, output, (File) input);
		else if(input instanceof List) handleList(ext, output, (List) input);
		else if(input instanceof File[]) handleArray(ext, output, (File[]) input);
	}
	
	private void handleList(String ext, List output, List input) throws Exception
	{
		for(int i=0;i<input.size();i++)
		handleFile(ext, output, (File) input.get(i));
	}
	
	private void handleArray(String ext, List output, File[] input) throws Exception
	{
		for(int i=0;i<input.length;i++)
		handleFile(ext, output, input[i]);
	}
	
	private void handleFile(String ext, List output, File input) throws Exception
	{
		if(input.isDirectory()) handleDir(ext,output,input);
		else if(filter(input,ext)) output.add(input);
	}
	
	
	private void handleDir(String ext, List output, File input) throws Exception
	{
		File[] ff = input.listFiles();
		for(int i=0;i<ff.length;i++)
		handleFile(ext,output,ff[i]);
	}
	
	private boolean filter(File file, String ext) throws Exception
	{return isOfExt.f(new Object[]{file,ext});}
}