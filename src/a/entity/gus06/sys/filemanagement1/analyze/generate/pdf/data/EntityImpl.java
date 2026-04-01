package a.entity.gus06.sys.filemanagement1.analyze.generate.pdf.data;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201104";}


	private Service emptyDir;
	private Service generateSeq;
	private Service generateFull;
	private Service retrieveMd5Set;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
		generateSeq = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.sequence");
		generateFull = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.full");
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.pdf.retrieve.md5set");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		// GENERATED DIR
		
		File dirGen = (File) ((R) engine).r("dirGenerated_pdf");
		emptyDir.p(dirGen);

		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected pdf: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		
		p.println("AUTHORS -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"author_md5","pdf.author",null});
		
		p.println("ISBN -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"isbn_md5","pdf.isbn",null});
		
		p.println("CREATOR -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"creator_md5","pdf.creator",null});
		
		p.println("PRODUCER -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"producer_md5","pdf.producer",null});
		
		p.println("SUBJECT -> MD5");
		generateSeq.p(new Object[]{engine,dirGen,md5Set,"subject_md5","pdf.subject",null});
		
		p.println("TITLE -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"title_md5","pdf.title",null});
		
		p.println("NAME0 -> MD5");
		generateFull.p(new Object[]{engine,dirGen,md5Set,"name0_md5","name0",null});
		
		p.println("______________");
		
		((V)engine).v("pdfGenerated",null);
	}
}