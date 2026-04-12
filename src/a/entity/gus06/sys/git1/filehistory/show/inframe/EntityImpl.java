package a.entity.gus06.sys.git1.filehistory.show.inframe;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.awt.Dimension;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201203";}
	
	public static final String DISPLAY = "GIT#GIT history - ";
	public static final Dimension DIM = new Dimension(1500,800);

	private Service historyGuiBuilder;
	private Service show;


	public EntityImpl() throws Exception
	{
		historyGuiBuilder = Outside.service(this,"factory#gus06.sys.git1.filehistory.gui.maingui");
		show = Outside.service(this,"gus06.swing.frame.show2");
	}

	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File) showFile((File) obj);
		else if(obj instanceof Object[]) showArray((Object[]) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void showFile(File file) throws Exception
	{
		Object gui = historyGuiBuilder.g();
		((P) gui).p(file);
		
		String display = DISPLAY+file.getName();
		show.p(new Object[]{gui,DIM,display});
	}
	
	private void showArray(Object[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data number: "+array.length);
		
		File file = (File) array[0];
		String selection = (String) array[1];
		
		Object gui = historyGuiBuilder.g();
		((P) gui).p(file);
		((V) gui).v("select", selection);
		
		String display = DISPLAY+file.getName();
		show.p(new Object[]{gui,DIM,display});
	}
}