package a.entity.gus06.sys.langdetect1.engine;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160629";}


	private Service prepareText;
	private Service extractNgrams;
	private Service computeProbs;
	private Service findBest;
	
	public EntityImpl() throws Exception
	{
		prepareText = Outside.service(this,"gus06.sys.langdetect1.t1.prepare.text");
		extractNgrams = Outside.service(this,"gus06.sys.langdetect1.t2.extractor.ngrams");
		computeProbs = Outside.service(this,"gus06.sys.langdetect1.t3.compute.probs");
		findBest = Outside.service(this,"gus06.sys.langdetect1.t4.find.best");
	}
	


	public Object t(Object obj) throws Exception
	{
		String text = (String) prepareText.t(obj);
		
		List ngrams = (List) extractNgrams.t(text);
		
		double[] probs = (double[]) computeProbs.t(ngrams);
		
		return findBest.t(probs);
	}
}
