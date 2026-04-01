package a.entity.gus06.dir.runtask.corpus.text.indexation.newdir;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150922";}


	private Service listing;
	private Service autoRename;
	private Service cutting;
	private Service handleLines;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		cutting = Outside.service(this,"gus06.string.split.wordcutting1");
		handleLines = Outside.service(this,"gus06.file.string.reader.handlelines.autodetect");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = (File) autoRename.t(dir);
		dir1.mkdirs();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			File f1 = new File(dir1,f.getName());
			PrintStream p1 = new PrintStream(f1);
			
			P handler = new LineHandler(p1);
			handleLines.p(new Object[]{f,handler,null,null});
			p1.close();
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	private class LineHandler implements P
	{
		private PrintStream p;
		public LineHandler(PrintStream p)
		{this.p = p;}
	
		public void p(Object obj) throws Exception
		{
			String line = (String) obj;
			String[] words = (String[]) cutting.t(line);
			for(String word:words) if(isValid(word)) p.println(word);
		}
	}
	
	
	
	private boolean isValid(String word)
	{
		return word.length()>2;
	}
}
