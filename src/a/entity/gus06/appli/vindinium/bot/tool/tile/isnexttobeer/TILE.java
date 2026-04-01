package a.entity.gus06.appli.vindinium.bot.tool.tile.isnexttobeer;

public class TILE {

	public static final int AIR = 0;
	
	public static final int HERO1 = 1;
	public static final int HERO2 = 2;
	public static final int HERO3 = 3;
	public static final int HERO4 = 4;

	public static final int MINE = 10;
	
	public static final int MINE1 = 11;
	public static final int MINE2 = 12;
	public static final int MINE3 = 13;
	public static final int MINE4 = 14;

	public static final int TAVERN = 5;
	public static final int WALL = 6;
	
	
	
	
	public static final boolean isFixed(int v) {return v>4;}
	public static final boolean isTavern(int v) {return v==5;}
	public static final boolean isWall(int v) {return v==6;}
	public static final boolean isMine(int v) {return v>=10;}
	public static final boolean isHero(int v) {return v>=1 && v<=4;}
	
	
	
	public static final boolean isFreeMine(int v)
	{return v==10;}
	
	public static final boolean isMyMine(int v, int me_id)
	{return v==10+me_id;}
	
	public static final boolean isTargetMine(int v, int me_id)
	{return isMine(v) && !isMyMine(v,me_id);}
	
	public static final boolean isEnemy(int v, int me_id)
	{return isHero(v) && v!=me_id;}
}
