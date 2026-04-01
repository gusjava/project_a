package a.entity.gus06.file.mobi.tool.exth.typetoname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		int type = ((Integer) obj).intValue();
		
		switch(type)
		{
			case 3:return "UNKNOWN1_3";
			
			case 100:return "AUTHOR";
			case 101:return "PUBLISHER";
			case 102:return "IMPRINT";
			case 103:return "DESCRIPTION";
			case 104:return "ISBN";
			case 105:return "SUBJECT";
			case 106:return "PUBLISHING_DATE";
			case 107:return "REVIEW";
			case 108:return "CONTRIBUTOR";
			case 109:return "RIGHTS";
			case 110:return "SUBJECT_CODE";	
			case 111:return "TYPE";	
			case 112:return "SOURCE";	
			case 113:return "ASIN";	
			case 114:return "VERSION_NUMBER";	
			case 115:return "SAMPLE";/** 0x0001 if the book content is only a sample of the full book */
			case 116:return "STARTREADING";/** Position (4-byte offset) in file at which to open when first opened */
			case 117:return "ADULT";/** Mobipocket Creator adds this if Adult only is checked on its GUI; contents: "yes" */
			case 118:return "RETAIL_PRICE";/** As text, e.g. "4.99" */
			case 119:return "RETAIL_PRICE_CURRENCY";	/** As text, e.g. "USD" */
			case 120:return "TSC";	
			case 121:return "KF8_BOUNDARY_OFFSET";	
			case 122:return "FIXED_LAYOUT";	
			case 123:return "BOOK_TYPE";	
			case 124:return "ORIENTATION_LOCK";	
			case 125:return "COUNT_OF_RESOURCES";	
			case 126:return "ORIGINAL_RESOLUTION";	
			case 127:return "ZERO_GUTTER";	
			case 128:return "ZERO_MARGIN";	
			case 129:return "KF8_COVER_URI";	
			
			case 131:return "UNKNOWN_131";	
			case 132:return "REGION_MAGNIFICATION";	
			
			case 150:return "LENDING_ENABLED";	
			
			case 200:return "DICTIONARY_SHORT_NAME";
			case 201:return "COVER_OFFSET";
			case 202:return "THUMBNAIL_OFFSET";
			case 203:return "HAS_FAKE_COVER";
			/** Known Values: 1=mobigen, 2=Mobipocket Creator, 200=kindlegen (Windows), 201=kindlegen (Linux),
	 		*  02=kindlegen (Mac).
			 * Warning: Calibre creates fake creator entries, pretending to be a Linux kindlegen 1.2 (201, 1, 2,
			 * 33307) for normal ebooks and a non-public Linux kindlegen 2.0 (201, 2, 0, 101) for periodicals.
			 */
			case 204:return "CREATOR_SOFTWARE";
			case 205:return "CREATOR_MAJOR_VERSION";
			case 206:return "CREATOR_MINOR_VERSION";
			case 207:return "CREATOR_BUILD_NUMBER";
			case 208:return "WATERMARK";
			case 209:return "TAMPER_PROOF_KEYS";/** Used by the Kindle (and Android app) for generating book-specific PIDs. */
			
			case 300:return "FONT_SIGNATURE";
			
			case 401:return "CLIPPING_LIMIT";/** Integer percentage of the text allowed to be clipped. Usually 10. */
			case 402:return "PUBLISHER_LIMIT";
			case 403:return "UNKNOWN_403";
			case 404:return "TTS_OFF";/** 1 - Text to Speech disabled; 0 - Text to Speech enabled */
			case 405:return "BORROWED";/** 1 in this field seems to indicate a rental book */
			case 406:return "BORROWED_EXPIRATION";/** If this field is removed from a rental, the book says it expired in 1969 */
			case 407:return "UNKNOWN_407";
			
			case 450:return "UNKNOWN_450";
			case 451:return "UNKNOWN_451";
			case 452:return "UNKNOWN_452";
			case 453:return "UNKNOWN_453";
			
			case 501:return "CDE_TYPE";/** PDOC - Personal Doc; EBOK - ebook; EBSP - ebook sample; */
			case 502:return "LAST_UPDATE_TIME";
			case 503:return "UPDATED_TITLE";
			case 504:return "ASIN_COPY";/** There is sometimes a copy of the asin here. */
			case 505:return "AMAZON_CONTENT_REFERENCE";
			case 506:return "TITLE_LANGUAGE";
			case 507:return "TITLE_DISPLAY_DIRECTION";
			case 508:return "TITLE_PRONUNCIATION";
			case 509:return "TITLE_COLLATION";
			case 510:return "SECONDARY_TITLE";
			case 511:return "SECONDARY_TITLE_LANGUAGE";
			case 512:return "SECONDARY_TITLE_DIRECTION";
			case 513:return "SECONDARY_TITLE_PRONUNCIATION";
			case 514:return "SECONDARY_TITLE_COLLATION";
			case 515:return "AUTHOR_LANGUAGE";
			case 516:return "AUTHOR_DISPLAY_DIRECTION";
			case 517:return "AUTHOR_PRONUNCIATION";
			case 518:return "AUTHOR_COLLATION";
			case 519:return "AUTHOR_TYPE";
			case 520:return "PUBLISHER_LANGUAGE";
			case 521:return "PUBLISHER_DISPLAY_DIRECTION";
			case 522:return "PUBLISHER_PRONUNCIATION";
			case 523:return "PUBLISHER_COLLATION";
			case 524:return "LANGUAGE";
			case 525:return "ALIGNMENT";
			case 526:return "NCX_INGESTED_BY_SOFTWARE";
			case 527:return "PAGE_PROGRESSION_DIRECTION";
			case 528:return "OVERRIDE_KINDLE_FONTS";
			case 529:return "COMPRESSION_UPGRADED";
			case 530:return "SOFT_HYPHENS_IN_CONTENT";
			case 531:return "DICTIONARY_IN_LANGAGUE";
			case 532:return "DICTIONARY_OUT_LANGUAGE";
			case 533:return "FONT_CONVERTED";
			case 534:return "AMAZON_CREATOR_INFO";
			case 535:return "CREATOR_BUILD_NUMBER_COPY";/** found 1019-d6e4792 in this record, which is a build number of Kindlegen 2.7 */
			case 536:return "HD_MEDIA_CONTAINERS_INFO";/** CONT_Header is 0, Ends with CONTAINER_BOUNDARY (or Asset_Type?) */
			
			case 538:return "RESOURCE_CONTAINER_FIDELITY";
			case 539:return "HD_CONTAINER_MIMETYPE";
			case 540:return "SAMPLE_FOR_SPECIAL_PURPOSE";
			case 541:return "KINDLETOOL_OPERATION_INFORMATION";
			case 542:return "CONTAINER_ID";
			case 543:return "ASSET_TYPE";
			case 544:return "UNKNOWN_544";
			
			case 547:return "IN_MEMORY";/** String 'I\x00n\x00M\x00e\x00m\x00o\x00r\x00y\x00' found in this record, for KindleGen V2.9 build 1029-0897292 */
		
			case 548:return "UNKNOWN1_548";
			
			default:throw new Exception("Unsupported type code: "+type);
		}
	}
}