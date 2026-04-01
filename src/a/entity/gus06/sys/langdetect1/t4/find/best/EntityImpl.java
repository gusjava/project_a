package a.entity.gus06.sys.langdetect1.t4.find.best;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}
	
	public static final double PROB_THRESHOLD = 0.1;


	private Service buildLangList;
	
	public EntityImpl() throws Exception
	{
		buildLangList = Outside.service(this,"gus06.sys.langdetect1.build.langlist");
	}


	public Object t(Object obj) throws Exception
	{
		double[] probs = (double[]) obj;
		
		int index1 = -1;
		double prob1 = PROB_THRESHOLD;
    	
		for(int i=0; i<probs.length; ++i)
		if(probs[i] > prob1)
		{
			prob1 = probs[i];
			index1 = i;
		}
		
		if(index1==-1) return null;
		
		List langList = (List) buildLangList.g();
		return langList.get(index1);
	}
}
