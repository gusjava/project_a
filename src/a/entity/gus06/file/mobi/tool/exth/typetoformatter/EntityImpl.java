package a.entity.gus06.file.mobi.tool.exth.typetoformatter;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}
	
	
	private Service utf8;
	private Service int1;
	private Service long1;
	
	public EntityImpl() throws Exception
	{
		utf8 = Outside.service(this,"gus06.convert.bytearraytoutf8");
		int1 = Outside.service(this,"gus06.convert.bytearraytoint");
		long1 = Outside.service(this,"gus06.convert.bytearraytolong");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		int type = ((Integer) obj).intValue();
		
		switch(type)
		{
			case 116:return int1;//"STARTREADING";
			case 201:return int1; //"COVER_OFFSET";
			case 202:return int1; //"THUMBNAIL_OFFSET";
			case 204:return int1; //"CREATOR_SOFTWARE";
			case 205:return int1; //"CREATOR_MAJOR_VERSION";
			case 206:return int1; //"CREATOR_MINOR_VERSION";
			case 207:return int1; //"CREATOR_BUILD_NUMBER";
			case 401:return int1; // "CLIPPING_LIMIT";
			case 402:return int1; // "PUBLISHER_LIMIT";
			
			
			
			
//			case 100:return "AUTHOR";	
//			case 101:return "PUBLISHER";	
//			case 102:return "IMPRINT";	
//			case 103:return "DESCRIPTION";	
//			case 104:return "ISBN";	
//			case 105:return "SUBJECT";	
//			case 106:return "PUBLISHING_DATE";	
//			case 107:return "REVIEW";	
//			case 108:return "CONTRIBUTOR";	
//			case 109:return "RIGHTS";	
//			case 110:return "SUBJECT_CODE";	
//			case 111:return "TYPE";	
//			case 112:return "SOURCE";	
//			case 113:return "ASIN";	
//			case 114:return "VERSION_NUMBER";	
//			case 115:return "SAMPLE";
//			case 117:return "ADULT";
//			case 118:return "RETAIL_PRICE";
//			case 119:return "RETAIL_PRICE_CURRENCY";
//			case 120:return "TSC";
//			case 121:return "KF8_BOUNDARY_OFFSET";
//			case 122:return "FIXED_LAYOUT";	
//			case 123:return "BOOK_TYPE";	
//			case 124:return "ORIENTATION_LOCK";	
//			case 125:return "COUNT_OF_RESOURCES";	
//			case 126:return "ORIGINAL_RESOLUTION";	
//			case 127:return "ZERO_GUTTER";	
//			case 128:return "ZERO_MARGIN";	
//			case 129:return "KF8_COVER_URI";
//			case 131:return "UNKNOWN_131";	
//			case 132:return "REGION_MAGNIFICATION";	
//			case 150:return "LENDING_ENABLED";
//			case 200:return "DICTIONARY_SHORT_NAME";
//			case 203:return "HAS_FAKE_COVER";
//			case 208:return "WATERMARK";
//			case 209:return "TAMPER_PROOF_KEYS";
//			case 300:return "FONT_SIGNATURE";
//			case 403:return "UNKNOWN_403";
//			case 404:return "TTS_OFF";
//			case 405:return "BORROWED";
//			case 406:return "BORROWED_EXPIRATION";
//			case 407:return "UNKNOWN_407";
//			case 450:return "UNKNOWN_450";
//			case 451:return "UNKNOWN_451";
//			case 452:return "UNKNOWN_452";
//			case 453:return "UNKNOWN_453";
//			case 501:return "CDE_TYPE";
//			case 502:return "LAST_UPDATE_TIME";
//			case 503:return "UPDATED_TITLE";
//			case 504:return "ASIN_COPY";
//			case 505:return "AMAZON_CONTENT_REFERENCE";
//			case 506:return "TITLE_LANGUAGE";
//			case 507:return "TITLE_DISPLAY_DIRECTION";
//			case 508:return "TITLE_PRONUNCIATION";
//			case 509:return "TITLE_COLLATION";
//			case 510:return "SECONDARY_TITLE";
//			case 511:return "SECONDARY_TITLE_LANGUAGE";
//			case 512:return "SECONDARY_TITLE_DIRECTION";
//			case 513:return "SECONDARY_TITLE_PRONUNCIATION";
//			case 514:return "SECONDARY_TITLE_COLLATION";
//			case 515:return "AUTHOR_LANGUAGE";
//			case 516:return "AUTHOR_DISPLAY_DIRECTION";
//			case 517:return "AUTHOR_PRONUNCIATION";
//			case 518:return "AUTHOR_COLLATION";
//			case 519:return "AUTHOR_TYPE";
//			case 520:return "PUBLISHER_LANGUAGE";
//			case 521:return "PUBLISHER_DISPLAY_DIRECTION";
//			case 522:return "PUBLISHER_PRONUNCIATION";
//			case 523:return "PUBLISHER_COLLATION";
//			case 524:return "LANGUAGE";
//			case 525:return "ALIGNMENT";
//			case 526:return "NCX_INGESTED_BY_SOFTWARE";
//			case 527:return "PAGE_PROGRESSION_DIRECTION";
//			case 528:return "OVERRIDE_KINDLE_FONTS";
//			case 529:return "COMPRESSION_UPGRADED";
//			case 530:return "SOFT_HYPHENS_IN_CONTENT";
//			case 531:return "DICTIONARY_IN_LANGAGUE";
//			case 532:return "DICTIONARY_OUT_LANGUAGE";
//			case 533:return "FONT_CONVERTED";
//			case 534:return "AMAZON_CREATOR_INFO";
//			case 535:return "CREATOR_BUILD_NUMBER_COPY";
//			case 536:return "HD_MEDIA_CONTAINERS_INFO";
//			case 538:return "RESOURCE_CONTAINER_FIDELITY";
//			case 539:return "HD_CONTAINER_MIMETYPE";
//			case 540:return "SAMPLE_FOR_SPECIAL_PURPOSE";
//			case 541:return "KINDLETOOL_OPERATION_INFORMATION";
//			case 542:return "CONTAINER_ID";
//			case 543:return "ASSET_TYPE";
//			case 544:return "UNKNOWN_544";
//			case 547:return "IN_MEMORY";
		
			default:return utf8;
		}
	}
}