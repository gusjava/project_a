package a.entity.gus06.dir.runtask.rename.from.filesdico;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161224";}


	private Service listing;
	private Service fileChooser;
	private Service fileToMap;
	private Service buildMd5;
	private Service findPath;
	private Service move;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		fileChooser = Outside.service(this,"gus06.file.choose.open.file");
		fileToMap = Outside.service(this,"gus06.file.string.dico.build.rowmap.col0.strict");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		findPath = Outside.service(this,"gus06.dirfile.find.uprootedpath");
		move = Outside.service(this,"gus06.file.op.move.autorename");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		File dir_done = new File(dir,"DONE");
		File dir_notfound = new File(dir,"NOTFOUND");
		
		dir_done.mkdirs();
		dir_notfound.mkdirs();
		
		File dicoFile = (File) fileChooser.g();
		if(dicoFile==null || !dicoFile.isFile()) return;
		
		Map dico = (Map) fileToMap.t(dicoFile);
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			String md5 = (String) buildMd5.t(f);
			
			if(!dico.containsKey(md5))
			{
				File f1 = new File(dir_notfound,f.getName());
				move.p(new File[]{f,f1});
			}
			else
			{
				String[] row = (String[]) dico.get(md5);
				String newName = row[row.length-1];
				File newParent = new File(row[row.length-2]);
				
				String newPath = (String) findPath.t(newParent);
				File d1 = new File(dir_done,newPath);
				File f1 = new File(d1,newName);
				
				move.p(new File[]{f,f1});
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
