package a.entity.gus06.dir.perform.smartreplace.duplicate.withroot;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190420";}


	private Service buildListing;
	private Service replace;
	private Service isTextFile;
	private Service transformFile;
	private Service copyFile;
	
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		replace = Outside.service(this,"gus06.data.string.replace1.smart");
		isTextFile = Outside.service(this,"gus06.file.string.check");
		transformFile = Outside.service(this,"gus06.file.string.perform2.apply.t");
		copyFile = Outside.service(this,"gus06.file.op.copy.replace");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		if(o.length==2) perform((File) o[0],(T) o[1]);
		else if(o.length==3) perform((File) o[0],(String) o[1],(String) o[2]);
		
		else throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	private void perform(File dir, String search, String replacement) throws Exception
	{
		T t = new T1(search,replacement);
		perform(dir,t);
	}
	
	
	private void perform(File dir, T t) throws Exception
	{
		String rootName1 = (String) t.t(dir.getName());
		File dir1 = new File(dir.getParentFile(),rootName1);
		dir1.mkdirs();
		
		List listing = (List) buildListing.t(dir);
		for(int i=0;i<listing.size();i++)
		{
			File f = (File) listing.get(i);
			handleFile(f,dir,dir1,t);
		}
	}
	
	
	private void handleFile(File f0, File dir, File dir1, T t) throws Exception
	{
		int len = dir.getAbsolutePath().length();
		String relPath0 = f0.getAbsolutePath().substring(len);
		String relPath1 = (String) t.t(relPath0);
		
		boolean isSame = relPath0.equals(relPath1);
		
		File f1 = new File(dir1,relPath1);
		
		if(isTextFile.f(f0))
		{
			transformFile.p(new Object[]{f0,f1,t});
		}
		else
		{
			if(!isSame)
			{
				copyFile.p(new File[]{f0,f1});
			}
		}
	}
	
	
	
	
	private class T1 implements T
	{
		private String search;
		private String replacement;
		
		public T1(String search, String replacement)
		{
			this.search = search;
			this.replacement = replacement;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			return replace.t(new String[]{s,search,replacement});
		}
	}
}
