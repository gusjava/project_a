package a.entity.gus06.appli.gusdbmanager.execute.config.choosefile;

import java.io.File;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150613";}

	
	public static final String FILEDESC = "Fichier de configuration";
	public static final String EXTENSION = "prop_crypt";
	
	
	private Service fcHolder;
	private JFileChooser fc;
	
	private FileFilter0 fileFilter;
	private Icon icon;
	
	
	
	public EntityImpl() throws Exception
	{
		fcHolder = Outside.service(this,"gus06.swing.filechooser.holder");
		icon = (Icon) Outside.resource(this,"icon#FILE_server");
		
		fileFilter = new FileFilter0();
		
		fc = (JFileChooser) fcHolder.i();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(fileFilter);
		
		FileView fv = fc.getFileView();
		fc.setFileView(new FileView0(fv));
	}
	
	
	
	public Object g() throws Exception
	{
		int val = fc.showOpenDialog(null);
		if(val==JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		return null;
	}
	
	
	
	
	
	private class FileFilter0 extends FileFilter
	{
		public boolean accept(File f)	{return isTargetFile(f) || f.isDirectory();}
		public String getDescription()	{return FILEDESC+" (*."+EXTENSION+")";}
	}
	
	
	
	private class FileView0 extends FileView
	{
		private FileView fv;
		public FileView0(FileView fv) {this.fv = fv;}
		
		public String getName(File f) {return fv.getName(f);}
		public String getDescription(File f) {return fv.getDescription(f);}
		public Boolean isTraversable(File f) {return fv.isTraversable(f);}
		public Icon getIcon(File f)
		{
			if(isTargetFile(f)) return icon;
			return fv.getIcon(f);
		}
	}
	
	private boolean isTargetFile(File f)
	{return f.getName().toLowerCase().endsWith("."+EXTENSION);}
}
