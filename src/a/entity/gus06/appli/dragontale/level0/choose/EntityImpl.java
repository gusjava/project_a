package a.entity.gus06.appli.dragontale.level0.choose;

import javax.swing.JOptionPane;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}


	private Service start;
	private Service exit;

	public EntityImpl() throws Exception
	{
		start = Outside.service(this,"gus06.appli.dragontale.level1.start");
		exit = Outside.service(this,"gus.y.app1.execute.exit");
	}



	public void p(Object obj) throws Exception
	{
		int val = Integer.parseInt((String) obj);
		
		switch(val)
		{
		case 0: start();break;
		case 1: help();break;
		case 2: exit();break;
		default: return;
		}
	}


	
	private void start()
	{
		try{start.e();}
		catch(Exception e)
		{Outside.err(this,"start()",e);}
	}
	
	
	
	
	private void help()
	{
		try
		{
			JOptionPane.showMessageDialog(null,"HELP !");
		}
		catch(Exception e)
		{Outside.err(this,"help()",e);}
	}
	
	
	
	private void exit()
	{
		try{exit.e();}
		catch(Exception e)
		{Outside.err(this,"exit()",e);}
	}
}
