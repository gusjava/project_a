package a.entity.gus06.sys.filemanagement1.analyze.generate.ebook.data.author;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220731";}


	private Service generateSeq;
	private Service retrieveMd5Set;
	private Service formatAuthor;
	
	public EntityImpl() throws Exception
	{
		generateSeq = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.sequence");
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.retrieve.md5set");
		formatAuthor = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.format.author");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		// GENERATED DIR
		
		File dirGen = (File) ((R) engine).r("dirGenerated_ebook");

		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected ebooks: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		p.println("AUTHORS -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"author_md5","ebook.author",formatAuthor});
		
		p.println("______________");
		
		((V)engine).v("ebookGenerated",null);
	}
}