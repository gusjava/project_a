package a.entity.gus06.sys.langdetect1.t3.compute.probs;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}

	public static final double ALPHA_DEFAULT = 0.5;
	public static final double ALPHA_WIDTH = 0.05;
	public static final int ITERATION_LIMIT = 1000;
	public static final double CONV_THRESHOLD = 0.99999;
	public static final int BASE_FREQ = 10000;
	public static final int N_TRIAL = 7;
	
	
	private Service buildProbMap;
	private Service buildLangList;


	
	public EntityImpl() throws Exception
	{
		buildProbMap = Outside.service(this,"gus06.sys.langdetect1.build.probmap");
		buildLangList = Outside.service(this,"gus06.sys.langdetect1.build.langlist");
	}


	public Object t(Object obj) throws Exception
	{
		List ngrams = (List) obj;
		if(ngrams.size()==0) throw new Exception("no features in text");
		
		List langList = (List) buildLangList.g();
		int langNb = langList.size();
		
		Map probMap = (Map) buildProbMap.g();
		
		double[] langProb = new double[langNb];
		Random rand = new Random();
		
		for (int t = 0; t < N_TRIAL; ++t)
		{
			double[] prob = new double[langNb];
			for(int i=0;i<langNb;++i) prob[i] = 1.0 / langNb;
			
			double alpha = ALPHA_DEFAULT + rand.nextGaussian() * ALPHA_WIDTH;

			for (int i = 0;; ++i)
			{
				int r = rand.nextInt(ngrams.size());
				String word = (String) ngrams.get(r);
				
				if(word != null && probMap.containsKey(word))
				{
					double[] langProbMap = (double[]) probMap.get(word);
					double weight = alpha / BASE_FREQ;
					
					for(int j=0;j<prob.length;++j)
					prob[j] *= weight + langProbMap[j];
				}
				
				if(i % 5 == 0)
				{
					if (normalizeProb(prob) > CONV_THRESHOLD || i>=ITERATION_LIMIT) break;
				}
			}
			
			for(int i=0;i<langNb;++i)
			langProb[i] += prob[i] / N_TRIAL;
		}
		return langProb;
	}
	
	
	
	
	
	private double normalizeProb(double[] prob)
	{
		double maxp = 0;
		double sump = 0;
		
		for(int i=0;i<prob.length;++i)
			sump += prob[i];
		
		for(int i=0;i<prob.length;++i)
		{
			double p = prob[i] / sump;
			if (maxp < p) maxp = p;
			prob[i] = p;
		}
		return maxp;
	}
}
