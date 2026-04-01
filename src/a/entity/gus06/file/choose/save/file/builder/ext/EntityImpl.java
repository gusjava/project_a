package a.entity.gus06.file.choose.save.file.builder.ext;

import a.framework.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150710";}


	private Service fcHolder;
	private JFileChooser fc;
	
	public EntityImpl() throws Exception
	{
		fcHolder = Outside.service(this,"*gus06.swing.filechooser.holder");
		fc = (JFileChooser) fcHolder.i();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return new G1(o[0],o[1]);
	}
	
	
	private class G1 implements G
	{
		private String[] ext;
		private String filedesc;
		private FileFilter0 fileFilter;
		
		public G1(String extension, String filedesc)
		{
			this.ext = extension.split(";");
			this.filedesc = filedesc;
			fileFilter = new FileFilter0();
		}
		
		public Object g() throws Exception
		{
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			fc.setFileFilter(fileFilter);
			int val = fc.showSaveDialog(null);
			fc.setFileFilter(null);
			
			if(val!=JFileChooser.APPROVE_OPTION) return null;
			
			File f = fc.getSelectedFile();
			if(!f.exists() && !hasExtension(f))
				f = new File(f.getAbsoluteFile()+"."+ext[0]);
			return f;
		}
		
		
		
		private class FileFilter0 extends FileFilter
		{
			public boolean accept(File f)
			{return f.isDirectory() || hasExtension(f);}
			
			public String getDescription()
			{return filedesc+" ("+extToString()+")";}
		}
		
		private String extToString()
		{
			StringBuffer b = new StringBuffer();
			for(int i=0;i<ext.length-1;i++) b.append("*."+ext[i]+", ");
			b.append("*."+ext[ext.length-1]);
			return b.toString();
		}
		
		private boolean hasExtension(File f)
		{
			String n = f.getName().toLowerCase();
			for(String ext_:ext) if(n.endsWith("."+ext_)) return true;
			return false;
		}
	}
}
