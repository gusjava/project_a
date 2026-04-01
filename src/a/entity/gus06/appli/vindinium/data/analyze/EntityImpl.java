package a.entity.gus06.appli.vindinium.data.analyze;

import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}

	private Service analyzer_life;
	private Service analyzer_gold;
	private Service analyzer_mine;
	private Service analyzer_position;
	private Service analyzer_bot1;
	private Service score_bot1;

	
	public EntityImpl() throws Exception
	{
		analyzer_life = Outside.service(this,"gus06.appli.vindinium.data.analyze.lifeanalyzer");
		analyzer_gold = Outside.service(this,"gus06.appli.vindinium.data.analyze.goldanalyzer");
		analyzer_mine = Outside.service(this,"gus06.appli.vindinium.data.analyze.mineanalyzer");
		analyzer_position = Outside.service(this,"gus06.appli.vindinium.data.analyze.positionanalyzer");
		analyzer_bot1 = Outside.service(this,"gus06.appli.vindinium.data.analyze.botanalyzer1");
		score_bot1 = Outside.service(this,"gus06.appli.vindinium.data.analyze.botscore1");
	}
	
	public void e() throws Exception
	{
		analyzer_life.e();
		analyzer_gold.e();
		analyzer_mine.e();
		analyzer_position.e();
		analyzer_bot1.e();
		score_bot1.e();
	}

	public void p(Object obj) throws Exception
	{
		analyzer_life.p(obj);
		analyzer_gold.p(obj);
		analyzer_mine.p(obj);
		analyzer_position.p(obj);
		analyzer_bot1.p(obj);
		score_bot1.p(obj);
	}
}
