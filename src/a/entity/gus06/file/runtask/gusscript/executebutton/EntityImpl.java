package a.entity.gus06.file.runtask.gusscript.executebutton;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201113";}
	
	public static final String MESSAGE = "Please, enter button's display";


	private Service fileToButton;
	private Service getName0;
	private Service inputDialog;
	private Service renderButton;
	private Service showOnTop;

	public EntityImpl() throws Exception
	{
		fileToButton = Outside.service(this,"gus06.file.string.perform.execute.script1.filetobutton");
		getName0 = Outside.service(this,"gus06.file.getname0");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
		renderButton = Outside.service(this,"gus06.swing.button.cust2.display");
		showOnTop = Outside.service(this,"gus06.swing.dialog.build.dialogontop.tabbed");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String name0 = (String) getName0.t(file);
		String display = (String) inputDialog.t(new String[]{MESSAGE,name0});
		if(display==null || display.equals("")) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		showButton(file,display);
		if(progress!=null) ((E)progress).e();
	}
	
	
	
	private void showButton(File file, String display) throws Exception
	{
		Object button = fileToButton.t(file);
		renderButton.v(display,button);
		showOnTop.p(button);
	}
}