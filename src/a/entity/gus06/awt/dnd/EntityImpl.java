package a.entity.gus06.awt.dnd;

import a.framework.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141115";}


	private Service flavorProvider;
	private DataFlavor[] flavor0;
	
	public EntityImpl() throws Exception
	{
		flavorProvider = Outside.service(this,"gus06.awt.dnd.flavor");
		flavor0 = (DataFlavor[]) flavorProvider.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		P p = (P) o[1];
		G g = (G) o[2];
		
		new TransferHandler0(comp,p,g);
	}

	
	
	
	private Object findImportedData(Transferable t, DataFlavor df)
	{
		try{return t.getTransferData(df);}
		catch(Exception e){return null;} 
	}
	
	
	private Object findImportedData(Transferable t)
	{
		for(int i=0;i<flavor0.length;i++)
		{
			Object data = findImportedData(t,flavor0[i]);
			if(data!=null) return data;
		}
		return null;
	}
	
	
	
	private boolean dataToP(P p, Object data)
	{
		try{p.p(data);return true;}
		catch(Exception e) {Outside.err(this,"dataToP(P,Object)",e);} 
		return false;
	}
	
	private Object dataFromG(G g)
	{
		try{return g.g();}
		catch(Exception e) {Outside.err(this,"dataFromG(G)",e);} 
		return null;
	}
	
	private boolean isSupported(DataFlavor df)
	{
		for(int i=0;i<flavor0.length;i++)
			if(flavor0[i].getClass().equals(df.getClass())) return true;
		return false;
	}
	
	
	
	public class Transferable0 implements Transferable
	{
		private Object object; 
		
		public Transferable0(Object object)
		{this.object = object;}
		
		public Object getTransferData(DataFlavor dataFlavor) throws UnsupportedFlavorException
		{return object;}
		
		public DataFlavor[] getTransferDataFlavors()
		{return flavor0;}
		
		public boolean isDataFlavorSupported(DataFlavor flavor)
		{return isSupported(flavor);}
	}
	
	
	
	
	private class TransferHandler0 extends TransferHandler implements MouseListener
	{
		private P p_;
		private G g_;
		private TransferHandler previous_;
		
		public TransferHandler0(JComponent source, P p, G g)
		{
			super();
			this.p_ = p;
			this.g_ = g;
			this.previous_ = source.getTransferHandler();
			
			source.setTransferHandler(this);
			if(g!=null) source.addMouseListener(this);
		}
		
		public int getSourceActions(JComponent c)
		{return COPY_OR_MOVE;}
		
		
		protected Transferable createTransferable(JComponent c)
		{
			if(g_!=null)
			{
				Object data = dataFromG(g_);
				if(data==null) return null;
				return new Transferable0(data);
			}
			if(previous_!=null)
			{
				try{return createTransferableFromPrevious(previous_,c);}
				catch(Exception e){return super.createTransferable(c);}
			}
			return super.createTransferable(c);
		}

		
		public void exportToClipboard(JComponent comp, Clipboard clip, int action) throws IllegalStateException
		{
			if(previous_!=null)
				previous_.exportToClipboard(comp,clip,action);
		}


		public boolean importData(JComponent c, Transferable t)
		{
			if(p_!=null)
			{
				Object data = findImportedData(t);
				if(data==null) return false;
				return dataToP(p_,data);
			}
			if(previous_!=null)
				return previous_.importData(c,t);
			return false;
		}


		public boolean canImport(JComponent c, DataFlavor[] flavors)
		{
			if(p_!=null)
			{
				for(int i=0;i<flavors.length;i++)
					if(!isSupported(flavors[i])) return false;
				return true;
			}
			if(previous_!=null)
				return previous_.canImport(c,flavors);
			return super.canImport(c,flavors);
		}
		
		
		public boolean canImport(TransferSupport support)
		{
			return super.canImport(support);
		}
		
		
		protected void exportDone(JComponent source, Transferable data, int action)
		{
			if(previous_!=null)
			{
				try{exportDoneFromPrevious(previous_,source,data,action);}
				catch(Exception e){super.exportDone(source,data,action);}
			}
			else super.exportDone(source,data,action);
		}
		
		public void mousePressed(MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				JComponent source = (JComponent)e.getSource();
				exportAsDrag(source,e,COPY);
			}
		}
		
		public void mouseClicked(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
	}
	
	
	

	private Transferable createTransferableFromPrevious(TransferHandler previous, JComponent c) throws Exception
	{
		Method m = previous.getClass().getDeclaredMethod("createTransferable",JComponent.class);
		m.setAccessible(true);
		return (Transferable) m.invoke(previous,c);
	}
	
	private void exportDoneFromPrevious(TransferHandler previous, JComponent source, Transferable data, int action) throws Exception
	{
		Method m = previous.getClass().getDeclaredMethod("exportDone",JComponent.class,Transferable.class,Integer.TYPE);
		m.setAccessible(true);
		m.invoke(previous,source,data,action);
	}
}