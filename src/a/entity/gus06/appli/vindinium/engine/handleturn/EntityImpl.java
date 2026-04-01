package a.entity.gus06.appli.vindinium.engine.handleturn;

import a.framework.*;

public class EntityImpl extends Turn1 implements Entity, P {

	public String creationDate() {return "20170923";}

	public static final int NUMBER = 4;
	public static final int DRINK_COST = 2;
	
	
	private Service decodeBoard;
	private Service encodeBoard;

	public EntityImpl() throws Exception
	{
		decodeBoard = Outside.service(this,"gus06.appli.vindinium.engine.board.decode");
		encodeBoard = Outside.service(this,"gus06.appli.vindinium.engine.board.encode");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		initGame(t[0]);
		initDirection(t[1]);
		
		if(direction.length != NUMBER) throw new Exception("Invalid direction number: "+direction.length);
		if(heroes.size() != NUMBER) throw new Exception("Invalid hero number: "+heroes.size());
		
		decodeTiles();
		
		for(int i=0;i<NUMBER;i++) handleAction(i);
		for(int i=0;i<NUMBER;i++) produceGold(i);
		for(int i=0;i<NUMBER;i++) becomeThristy(i);
		
		encodeTiles();
	}
	
	
	
	private void decodeTiles() throws Exception
	{tiles = (int[][]) decodeBoard.t(board.get(DATA.B_TILES));}
	
	private void encodeTiles() throws Exception
	{board.put(DATA.B_TILES,encodeBoard.t(tiles));}
	


	private void handleAction(int index) throws Exception
	{
		int[] pos0 = heroPos(index);
		int[] pos1 = findNextPos(direction[index],pos0);
		
		int code = getTileCode(pos1);

		switch(code) {
		case TILE.AIR: heroMove(index,pos1);break;
		case TILE.TAVERN: haveDrink(index);break;
		
		case TILE.HERO1: heroHitHero(index,pos1);break;
		case TILE.HERO2: heroHitHero(index,pos1);break;
		case TILE.HERO3: heroHitHero(index,pos1);break;
		case TILE.HERO4: heroHitHero(index,pos1);break;
		
		case TILE.MINE: heroHitMine(index,pos1);break;
		case TILE.MINE1: heroHitMine(index,pos1);break;
		case TILE.MINE2: heroHitMine(index,pos1);break;
		case TILE.MINE3: heroHitMine(index,pos1);break;
		case TILE.MINE4: heroHitMine(index,pos1);break;
		}
	}
	
	
	private void produceGold(int index)
	{
		int mineCode = heroMineCode(index);
		int mineCount = countTiles(mineCode);
		setHeroMineCount(index,mineCount);
		
		if(mineCount==0) return; // no gold produced
		
		int gold = heroGold(index);
		setHeroGold(index,gold + mineCount);
	}
	

	private void haveDrink(int index)
	{
		int gold = heroGold(index);
		if(gold<DRINK_COST) return;

		setHeroGold(index,gold-DRINK_COST);
		
		int life = heroLife(index);
		life += LIFE.DRINK;
		if(life>LIFE.MAX) life = LIFE.MAX;
		setHeroLife(index,life);
	}
	
	
	private void becomeThristy(int index)
	{
		int life = heroLife(index);
		if(life>1) setHeroLife(index,life-1);
	}
	
	private void heroMove(int index, int[] end)
	{
		int[] start = heroPos(index);
		exchangeTiles(start,end);
		setHeroPos(index,end);
	}
	
	private void respawnHero(int index, int newMineCode)
	{
		int heroId = heroId(index);
		int heroMineCode = heroMineCode(index);
		
		int[] start = heroPos(index);
		int[] end = heroSpawnPos(index);
		
		if(equals(start,end)) return;
		
		int endTileCode = getTileCode(end);
		if(endTileCode!=TILE.AIR)
			respawnHero(endTileCode-1,heroMineCode);
		
		setTileCode(start,TILE.AIR);
		setTileCode(end,heroId);
		
		setHeroPos(index,end);
		setHeroLife(index,LIFE.MAX);
		
		updateTiles(heroMineCode,newMineCode);
	}
	
	
	private void heroHitMine(int index, int[] targetPos)
	{
		int heroMineCode = heroMineCode(index);
		int targetMineCode = getTileCode(targetPos);
		
		if(heroMineCode==targetMineCode) return;
		
		int life = heroLife(index);
		life -= LIFE.HIT;
		if(life>0) //hero survives
		{
			setTileCode(targetPos,heroMineCode);
			setHeroLife(index,life);
		}
		else // hero dead
		{
			respawnHero(index,targetMineCode);
		}
	}
	
	
	private void heroHitHero(int index, int[] targetPos)
	{
		int targetId = getTileCode(targetPos);
		int targetIndex = targetId-1;
		
		int targetLife = heroLife(targetIndex);
		
		targetLife -= LIFE.HIT;
		if(targetLife>0) //hero survives
		{
			setHeroLife(targetIndex,targetLife);
		}
		else //hero dead
		{
			int heroMineCode = heroMineCode(index);
			respawnHero(targetIndex,heroMineCode);
		}
	}
}
