package edu.thu.util;

/**
 * 所有的静态常量值的工具类
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
	public static final String RESULT_CONTENT_DEFAULT = "";

	public static final int RESULT_CODE_EXCEPTION = -1;
	public static final int RESULT_CODE_SUCCEED = 1;
	public static final int RESULT_CODE_FAIL = 0;

}
