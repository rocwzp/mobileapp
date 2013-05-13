package edu.thu.util;

/**
 * 所有的静态常量值的工具类 V0.1
 */
public class CommonUtil {

	public static final String RES = "res";
	public static final String DES = "des";
	public static final String JST = "jst";

	public static final String JNDI_DES = "java:comp/env/design";
	public static final String JNDI_RES = "java:comp/env/resources";
	public static final String JNDI_JST = "java:comp/env/jst_study";
	public static final String JNDI_PORTAL = "java:comp/env/portal";

	public static final int RESULT_CODE_DEFAULT = -1;
	public static final String RESULT_MESSAGE_DEFAULT = "网络或者服务器故障";
	public static final String RESULT_CONTENT_DEFAULT = "\"\"";

	// result type
	public static final int RESULT_CODE_EXCEPTION = -1;
	public static final int RESULT_CODE_SUCCEED = 1;
	public static final int RESULT_CODE_FAIL = 0;

	// suggestion type
	public static final int SUGGESTION_TYPE_NEW = 0;
	public static final int SUGGESTION_TYPE_HOT = 1;
	public static final int SUGGESTION_DEFAULT_COUNT = 20;

	// search type
	public static final int SEARCH_TYPE_COURSE = 0;
	public static final int SEARCH_TYPE_RESOURCE = 1;
	public static final int SEARCH_DEFAULT_COUNT = 20;

	//favorite type
	public static final int FAVORITE_TYPE_STU = 0;
	public static final int FAVORITE_TYPE_GROUP = 1;
	
}
