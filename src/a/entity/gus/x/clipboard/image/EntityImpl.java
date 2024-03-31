package a.entity.gus.x.clipboard.image;

import a.framework.*;
import java.awt.Image;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.IOException;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20240121";}
	
	public Object g() throws Exception
	{
		try{return c().getData(DataFlavor.imageFlavor);}
		catch(Exception e) {return null;}
	}
	
	public void p(Object obj) throws Exception
	{
		ImageSelection t = new ImageSelection((Image) obj);
		c().setContents(t,t);
	}
	
	private Clipboard c()
	{return Toolkit.getDefaultToolkit().getSystemClipboard();}
	
	private class ImageSelection implements Transferable, ClipboardOwner
	{
		private Image image;
		public ImageSelection(Image image)
		{this.image = image;}

		public Object getTransferData(DataFlavor f) throws UnsupportedFlavorException, IOException
		{
			if(f.equals(DataFlavor.imageFlavor) && image!=null) return image;
			throw new UnsupportedFlavorException(f);
		}
		
		public DataFlavor[] getTransferDataFlavors()
		{return new DataFlavor[]{DataFlavor.imageFlavor};}
		
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
