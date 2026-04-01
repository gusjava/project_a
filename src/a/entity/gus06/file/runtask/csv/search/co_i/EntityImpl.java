package a.entity.gus06.file.runtask.csv.search.co_i;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190702";}


	private Service readFile;
	private Service buildFilter;
	private Service getInput;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.csv.autodetect");
		buildFilter = Outside.service(this,"gus06.filter.string.build.contains_i");
		getInput = Outside.service(this,"gus06.input.text.dialog");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String input = (String) getInput.g();
		F filter = (F) buildFilter.t(input);
		
		File resultFile = new File(file.getAbsolutePath()+"_result.txt");
		PrintStream p = new PrintStream(resultFile);
		
		String[][] data = (String[][]) readFile.t(file);
		if(data.length==0) return;
		
		String[] header = data[0];
		
		for(int i=1;i<data.length;i++)
		{
			String[] row = data[i];
			
			for(int j=0;j<row.length;j++)
			{
				if(filter.f(row[j]))
				p.println(i+"\t"+header[j]+"="+row[j]);
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		p.close();
	}
}
