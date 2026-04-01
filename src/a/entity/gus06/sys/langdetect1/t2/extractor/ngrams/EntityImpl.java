package a.entity.gus06.sys.langdetect1.t2.extractor.ngrams;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}


	private Service buildProbMap;
	
	public EntityImpl() throws Exception
	{
		buildProbMap = Outside.service(this,"gus06.sys.langdetect1.build.probmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Map probMap = (Map) buildProbMap.g();
		
		List list = new ArrayList();
		NGram ngram = new NGram();
        
		for(int i=0;i<text.length();++i)
		{
			ngram.addChar(text.charAt(i));
            
			for(int n=1;n<=3;++n)
			{
				String w = ngram.get(n);
				if (w!=null && probMap.containsKey(w)) list.add(w);
			}
        	}
        	return list;
	}
	
	
	private class NGram
	{
		private StringBuffer grams_ = new StringBuffer(" ");
		private boolean capitalword_ = false;

		public void addChar(char ch)
		{
			ch = NORMALIZE.normalize(ch);
			char lastchar = grams_.charAt(grams_.length() - 1);
			if (lastchar == ' ')
			{
				grams_ = new StringBuffer(" ");
				capitalword_ = false;
				if (ch==' ') return;
			}
			else if (grams_.length() >= 3)
			{
				grams_.deleteCharAt(0);
			}
			grams_.append(ch);

			if (Character.isUpperCase(ch))
			{
				if (Character.isUpperCase(lastchar)) capitalword_ = true;
			}
			else
			{
				capitalword_ = false;
			}
		}
		
		public String get(int n)
		{
			if (capitalword_) return null;
			int len = grams_.length(); 
			if (n < 1 || n > 3 || len < n) return null;
			if (n == 1) {
				char ch = grams_.charAt(len - 1);
				if (ch == ' ') return null;
				return Character.toString(ch);
			}
			return grams_.substring(len - n, len);
		}
	}
}
