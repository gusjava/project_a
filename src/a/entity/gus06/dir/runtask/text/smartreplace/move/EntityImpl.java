package a.entity.gus06.dir.runtask.text.smartreplace.move;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180226";}


	private Service buildListing;
	private Service replace;
	private Service input;
	private Service isTextFile;
	private Service transformFile;
	private Service deleteFile;
	private Service moveFile;
	
	private PrintStream out;
	
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		replace = Outside.service(this,"gus06.data.string.replace1.smart");
		input = Outside.service(this,"gus06.input.text.dialog");
		isTextFile = Outside.service(this,"gus06.file.string.check");
		transformFile = Outside.service(this,"gus06.file.string.perform2.apply.t");
		deleteFile = Outside.service(this,"gus06.file.op.delete");
		moveFile = Outside.service(this,"gus06.file.op.move.replace");
		
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
		
		T t = new T1(search,replacement);
		
		
		List listing = (List) buildListing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+listing.size());
		
		for(int i=0;i<listing.size();i++)
		{
			File f = (File) listing.get(i);
			
			handleFile(f,dir,t);
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private void handleFile(File f0, File dir, T t) throws Exception
	{
		int len = dir.getAbsolutePath().length();
		String relPath0 = f0.getAbsolutePath().substring(len);
		String relPath1 = (String) t.t(relPath0);
		
		boolean isSame = relPath0.equals(relPath1);
		
		File f1 = new File(dir,relPath1);
		
		if(isTextFile.f(f0))
		{
			boolean done = transformFile.f(new Object[]{f0,f1,t});
			if(done)
			{
				out.println("MODIFIED");
				out.println("f0: "+f0);
				out.println("f1: "+f1);
			}
			
			if(!isSame) deleteFile.p(f0);
		}
		else
		{
			if(!isSame)
			{
				moveFile.p(new File[]{f0,f1});
				out.println("MOVED");
				out.println("f0: "+f0);
				out.println("f1: "+f1);
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
