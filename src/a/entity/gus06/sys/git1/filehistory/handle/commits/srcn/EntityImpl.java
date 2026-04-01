package a.entity.gus06.sys.git1.filehistory.handle.commits.srcn;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201203";}
	
	public static final String KEY_SRC = "src";
	public static final String KEY_FILE = "file";
	public static final String KEY_TIME = "time";
	
	public static final String KEY_SRC_N = "srcN";
	public static final String KEY_ERR_N = "errN";
	public static final String KEY_PREVIOUS = "previous";
	public static final String KEY_STATE = "state";


	private Service normalizeSrc;
	private Service grouping;

	public EntityImpl() throws Exception
	{
		normalizeSrc = Outside.service(this,"gus06.sys.git1.tool.src.normalizer");
		grouping = Outside.service(this,"gus06.sys.textcomparator1.grouping");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		List commits = (List) obj;
		
		int nb = commits.size();
		for(int i=0;i<nb;i++)
		{
			Map m = commitAt(commits,i);
			reset(m);
			normalizeSrc(m);
		}
		
		for(int i=0;i<nb;i++)
		{
			Map m1 = commitAt(commits,i);
			Map m0 = commitAt(commits,i+1);
			
			if(m0!=null) m1.put(KEY_PREVIOUS,m0);
			
			String state = computeState(m0,m1);
			m1.put(KEY_STATE,state);
		}
	}
	
	
	private void reset(Map m)
	{
		m.remove(KEY_SRC_N);
		m.remove(KEY_ERR_N);
		m.remove(KEY_PREVIOUS);
		m.remove(KEY_STATE);
	}
	
	
	private void normalizeSrc(Map m)
	{
		try
		{
			String src = (String) m.get(KEY_SRC);
			File file = (File) m.get(KEY_FILE);
			String srcN = (String) normalizeSrc.t(new Object[]{src,file});
			m.put(KEY_SRC_N,srcN);
		}
		catch(Exception e)
		{
			String message = "Failed to normalize src for commit: "+m.get(KEY_TIME);
			Exception e1 = new Exception(message,e);
			m.put(KEY_ERR_N,e1);
			
			Outside.err(this,"normalizeSrc(Map)",e1);
		}
	}


	private Map commitAt(List commits, int index)
	{
		if(index<0 || index>=commits.size()) return null;
		return (Map) commits.get(index);
	}
	
	
	private String computeState(Map m0, Map m1) throws Exception
	{
		if(m1.containsKey(KEY_ERR_N)) return "E";
		if(m0==null) return "A";
		
		String src0 = (String) m0.get(KEY_SRC);
		String src1 = (String) m1.get(KEY_SRC);
		
		if(src0==null && src1==null) return "#01";
		if(src0==null) return "#0";
		if(src1==null) return "#1";
		
		if(src0.trim().equals(src1.trim())) return "0";
		
		if(m1.containsKey("srcN") && m0.containsKey(KEY_SRC_N))
			return computeState(m0,m1,KEY_SRC_N);
		return computeState(m0,m1,KEY_SRC);
	}
	
	
	private String computeState(Map m0, Map m1, String srcKey) throws Exception
	{
		String src0 = (String) m0.get(srcKey);
		String src1 = (String) m1.get(srcKey);
		
		if(src0.trim().equals(src1.trim())) return "N";
		
		List blocks = (List) grouping.t(new Object[]{src0,src1});
		int sameNb = 0;
		int addedNb = 0;
		int removedNb = 0;
		
		for(int i=0;i<blocks.size();i++)
		{
			Map block = (Map) blocks.get(i);
			if(block.containsKey("same")) sameNb++;
			if(block.containsKey("after")) addedNb++;
			if(block.containsKey("before")) removedNb++;
		}
		
		if(addedNb>0 && removedNb>0) return "D";
		if(sameNb>0 && addedNb>0) return "DA";
		if(sameNb>0 && removedNb>0) return "DR";
		
		if(addedNb>0) return "A";
		if(removedNb>0) return "R";
		
		return "";
	}
}