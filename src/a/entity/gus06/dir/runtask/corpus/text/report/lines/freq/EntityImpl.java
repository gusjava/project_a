package a.entity.gus06.dir.runtask.corpus.text.report.lines.freq;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151014";}
	
	private Service listing;
	private Service handleLine;
	private Service writeFreqMap;
	private Service buildAppender;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
		handleLine = Outside.service(this,"gus06.file.string.reader.handlelines.autodetect");
		writeFreqMap = Outside.service(this,"gus06.file.write.string.freqmap");
		buildAppender = Outside.service(this,"gus06.map.freqmap.build.appender");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File reportFile = new File(dir.getAbsolutePath()+"_linefreq.txt");
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		Map map = new HashMap();
		P appender = (P) buildAppender.t(map);
		
		for(File f:ff)
		{
			handleLine.p(new Object[]{f,appender,null,interrupt});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		writeFreqMap.p(new Object[]{reportFile,map});
	}
}
