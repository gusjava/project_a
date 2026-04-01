package a.entity.gus06.file.string.perform.execute.batch.filetobutton;

import a.framework.*;
import java.io.File;
import javax.swing.JButton;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260313";}


	private Service fileToExe;
	private Service exeToButton;

	public EntityImpl() throws Exception
	{
		fileToExe = Outside.service(this,"gus06.file.string.perform.execute.batch.filetoexecute");
		exeToButton = Outside.service(this,"gus06.swing.button.build.execute");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		E exe = (E) fileToExe.t(file);
		JButton button = (JButton) exeToButton.t(exe);
		button.setText(file.getName());
		return button;
	}
}
