package a.entity.gus06.dir.runtask.text.smartreplace.duplicate.withroot;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190225";}


	private Service buildListing;
	private Service replace;
	private Service input;
	private Service isTextFile;
	private Service transformFile;
	private Service copyFile;
	
	private PrintStream out;
	
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		replace = Outside.service(this,"gus06.data.string.replace1.smart");
		input = Outside.service(this,"gus06.input.text.dialog");
		isTextFile = Outside.service(this,"gus06.file.string.check");
		transformFile = Outside.service(this,"gus06.file.string.perform2.apply.t");
		copyFile = Outside.service(this,"gus06.file.op.copy.replace");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String search = (String) input.t("Search string:");
		if(search==null) return;
		
		String replacement = (String) input.t("Replacement string:");
		if(replacement==null) return;
		
		String[] nn = replacement.split(";");
		int nb = nn.length;
		
		T[] t = new T[nb];
		for(int i=0;i<nb;i++)
		t[i] = new T1(search,nn[i]);
		
		File[] dir1 = new File[nb];
		for(int i=0;i<nb;i++)
		{
			String rootName1 = (String) t[i].t(dir.getName());
			dir1[i] = new File(dir.getParentFile(),rootName1);
			dir1[i].mkdirs();
		}
		
		
		List listing = (List) buildListing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+listing.size());
		
		for(int i=0;i<listing.size();i++)
		{
			File f = (File) listing.get(i);
			
			for(int j=0;j<nb;j++)
			handleFile(f,dir,dir1[j],t[j]);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
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
			boolean done = transformFile.f(new Object[]{f0,f1,t});
			if(done)
			{
//				out.println("MODIFIED\nf0: "+f0+"\nf1: "+f1);
			}
		}
		else
		{
			if(!isSame)
			{
				copyFile.p(new File[]{f0,f1});
//				out.println("COPIED\nf0: "+f0+"\nf1: "+f1);
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
