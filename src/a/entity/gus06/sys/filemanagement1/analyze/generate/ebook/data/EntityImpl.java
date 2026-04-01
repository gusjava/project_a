package a.entity.gus06.sys.filemanagement1.analyze.generate.ebook.data;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201102";}


	private Service emptyDir;
	private Service generateSeq;
	private Service generateFull;
	private Service retrieveMd5Set;
	private Service formatAuthor;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
		generateSeq = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.sequence");
		generateFull = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.full");
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
		emptyDir.p(dirGen);

		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected ebooks: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		
		p.println("AUTHORS -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"author_md5","ebook.author",formatAuthor});
		
		p.println("ASIN -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"asin_md5","ebook.asin",null});
		
		p.println("ISBN -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"isbn_md5","ebook.isbn",null});
		
		p.println("LANGUAGE -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"language_md5","ebook.language",null});
		
		p.println("PUBLISHER -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"publisher_md5","ebook.publisher",null});
		
		p.println("SUBJECT -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"subject_md5","ebook.subject",null});
		
		p.println("TITLE -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"title_md5","ebook.title",null});
		
		p.println("NAME0 -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"name0_md5","name0",null});
		
		p.println("______________");
		
		((V)engine).v("ebookGenerated",null);
	}
}