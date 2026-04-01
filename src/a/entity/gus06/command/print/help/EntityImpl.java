package a.entity.gus06.command.print.help;

import a.framework.*;
import java.io.PrintStream;


public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140703";}


	private PrintStream out;

	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void e() throws Exception
	{
		out.println("HELP");
		out.println("___________");
		out.println("Type \"cmd\" to display all available commands");
		out.println("Type \"p mapping\" to display all available objects");
		out.println("Type \"p main\" to display application main storage");
		out.println("Type \"p prop\" to display application properties");
	}
}
