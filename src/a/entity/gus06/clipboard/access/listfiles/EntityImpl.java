package a.entity.gus06.clipboard.access.listfiles;

import a.framework.*;
import java.util.List;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.IOException;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20140801";}


	private Service findList;
	
	public EntityImpl() throws Exception
	{
		findList = Outside.service(this,"gus06.find.filelist");
	}


	
	public Object g() throws Exception
	{
		try{return c().getData(DataFlavor.javaFileListFlavor);}
		catch(Exception e) {return null;}
	}
	
	
	public void p(Object obj) throws Exception
	{
		List list = (List) findList.t(obj);
		FilesSelection t = new FilesSelection(list);
		c().setContents(t,t);
	}
	
	
	private Clipboard c()
	{return Toolkit.getDefaultToolkit().getSystemClipboard();}
	
	
	
	private class FilesSelection implements Transferable, ClipboardOwner
	{
		private List files;

		public FilesSelection(List files)
		{this.files = files;}

		public Object getTransferData(DataFlavor f) throws UnsupportedFlavorException, IOException
		{
			if(f.equals(DataFlavor.javaFileListFlavor) && files!=null) return files;
			throw new UnsupportedFlavorException(f);
		}
		
		public DataFlavor[] getTransferDataFlavors()
		{return new DataFlavor[]{DataFlavor.javaFileListFlavor};}
		
		
		public boolean isDataFlavorSupported(DataFlavor f)
		{
			DataFlavor[] flavors = getTransferDataFlavors();
			for(DataFlavor flavor:flavors) if(flavor.equals(f)) return true;
			return false;
		}

		public void lostOwnership(Clipboard c, Transferable t)
		{}
	}
}