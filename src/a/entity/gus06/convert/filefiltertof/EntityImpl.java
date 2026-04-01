package a.entity.gus06.convert.filefiltertof;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}


	public Object t(Object obj) throws Exception
	{return new F1((FileFilter) obj);}
	
	
	
		
	
	private class F1 implements G, F, P
	{
		private FileFilter ff;
		public F1(FileFilter ff) {this.ff = ff;}
		
		public Object g() throws Exception
		{return ff;}
		
		public void p(Object obj) throws Exception
		{this.ff = (FileFilter) obj;}
		
		public boolean f(Object obj) throws Exception
		{return ff.accept((File) obj);}
	}
}
