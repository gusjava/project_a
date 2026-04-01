package a.entity.gus06.sys.txtfiletail.build.catcher;

import a.framework.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.file.Files;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221010";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Catcher((File) obj);
	}
	
	private class Catcher extends S1 implements G
	{
		private File file;
		private int max = 0;
		private int index = 0;
		private StringBuilder b;
		
		public Catcher(File file)
		{
			this.file = file;
		}
		
		public Object g() throws Exception
		{
			b = new StringBuilder();
			index = 0;
			
			Path filePath = Paths.get(file.getAbsolutePath());
			try (Stream<String> lines = Files.lines(filePath))
			{
				lines.forEach(line->{
					index++;
					if(index>max) b.append(line+"\n");
				});
			}
			max = index;
			return b.toString();
		}
	}
}
