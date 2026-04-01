package a.entity.gus06.file.perform.movedown.ask;

import a.framework.*;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20150924";}


	private Service moveOp;
	private Service findNeighbours;


	public EntityImpl() throws Exception
	{
		moveOp = Outside.service(this,"gus06.file.op.move.autorename");
		findNeighbours = Outside.service(this,"gus06.file.neighbours.finddirs");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		List listing = (List) findNeighbours.t(file);
		File dir = findDir(listing);
		if(dir==null) return null;
		
		File out = new File(dir,file.getName());
		moveOp.p(new File[]{file,out});
		return out;
	}
	
	
	
	private File findDir(List listing)
	{
		int number = listing.size();
		if(number==0) return null;
		if(number==1) return (File) listing.get(0);
		
		String message = "Please, choose target directory";
		String title = "Directory chooser";
		
		File[] values = new File[number];
		listing.toArray(values);
		
		return (File) JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,values,values[0]);
	}
}
