package com.ela.blessing.star.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 */
public class StringUtil {
	/**
	 * 判断字符串是否为null或是为空串（空格也算空串）
	 * @param	s	要校验的字符串
	 * @return	true 是，false 否
	 */
	public static boolean isNullOrEmpty(String s){
		if( s==null || s.trim().length()==0 ){
			return true;
		}
		if (compareString(s, "null")) {
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串是否为整数,不包含小数 , null或"" 返回false, 
	 * @param str
	 * @return 数字true,非数字false
	 */
	public static boolean isNumerical(String str){
		if(str == null || str.equals("")){
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			if (temp >= '0' && temp <= '9') {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 判断是不是16进制的数字
	 * @param str
	 * @return
	 */
    public static boolean isHexNumerical(String str) {
        if (str == null || str.equals("")) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (((c >= 'a') && (c <= 'f')) || ((c >= 'A') && (c <= 'F')) || ((c >= '0') && (c <= '9'))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * 检查字符串是否是数字，小数、整数返回true,  null或"" 返回false
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str){
		if(str == null || str.equals("")){
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			if ((temp >= '0' && temp <= '9') || temp == '.') {
				continue;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 名称是否合法，名称可以是 中文，英文，数字 组成，其他字符非法
	 * @param name
	 * @return 合法 true, 非法false
	 */
	public static boolean isRightName(String name){
		if (name == null || name.equals("")){
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			int temp = name.charAt(i);
			if ((temp >= 0x4E00 && temp <= 0x9FA5)
				|| //中文
			 (temp >= 0x0061 && temp <= 0x007A)
				|| //小写英文
			 (temp >= 0x0041 && temp <= 0x005A)
				|| //大写英文
			 (temp >= 0x0030 && temp <= 0x0039) //数字
			) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	public static boolean isCellPhone(String s){
		if(s == null || s.length() != 11){
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c < '0' || c > '9'){
				return false;
			}
		}

		return true;
	}

	public static String lastWord(String s, String regex){
		if(isNullOrEmpty(s)){
			return s;
		}

		String[] strArray;

		if(regex.equals(".")){
			strArray = s.split("\\.");
		}else{
			strArray = s.split(regex);
		}

		return strArray[strArray.length-1];
	}


	public static String firstWord(String s, String regex){
		if(isNullOrEmpty(s)){
			return s;
		}

		String[] strArray;

		if(regex.equals(".")){
			strArray = s.split("\\.");
		}else{
			strArray = s.split(regex);
		}

		return strArray[0];
	}

	public static String[] split(String s, String regex){
		String[] strArray;

		if(isNullOrEmpty(s)){
			strArray = new String[1];
			strArray[0] = s;
			return strArray;
		}



		if(regex.equals(".")){
			strArray = s.split("\\.");
		}else{
			strArray = s.split(regex);
		}

		return strArray;
	}



	public static String enCode(String str) {
		try {
			return URLEncoder.encode(str,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String decode(String str){
		try {
			return URLDecoder.decode(str,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 字符串相等 null和空字符串认为相等，忽略字符串前后空格
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        if (null == str1) {
            str1 = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (str1.trim().equals(str2.trim())) {
            return true;
        }
        return false;
    }

    /**
     * <p>功能描述:将字符串转码</p>
     * @param str
     * @param format	源编码
     * @param newFormat	目标编码
     * @return
     * @author: WangLiang
     * @update:[2014年12月12日 下午3:06:04] WangLiang [变更描述]
     */
    public static String convertCharacterSet(String str, String format, String newFormat) {
		if (str == null) {
			return null;
		}

		try {
			return new String(str.getBytes(format), newFormat);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

    /**
     * <p>功能描述:将字符串转码</p>
     * @param str
     * @return
     * @author: WangLiang
     * @update:[2014年12月12日 下午3:06:04] WangLiang [变更描述]
     */
    public static String convertCharacterSet(String str) {
		if (str == null) {
			return null;
		}

		try {
			return new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

    public static boolean isZipCode(String str){
    	if(str == null || str.length() != 6){
			return false;
		}
    	return StringUtil.isNumerical(str);
    }

    public static String join(Collection<String> strs){
    	StringBuffer t = new StringBuffer("");
    	for(String s : strs){
    		t.append(s).append(",");
    	}
    	return t.substring(0, t.length()-1).toString();
    }

    /**
     * 检查浮点数
     * @param num
     * @param type "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
     * @return
     */
    public static boolean checkFloat(String num,String type){
        String eL = "";
        if(type.equals("0+"))eL = "^\\d+(\\.\\d+)?$";//非负浮点数
        else if(type.equals("+"))eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数
        else if(type.equals("-0"))eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数
        else if(type.equals("-"))eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";//负浮点数
        else eL = "^(-?\\d+)(\\.\\d+)?$";//浮点数
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(num);
        boolean b = m.matches();
        return b;
    }
    
    /**
     * 此方法只能拦住大多数表情，后期扩展的表情不一定支持
     * 字符串是否包含EMOJI表情
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i+1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() -1) {
                    char ls = source.charAt(i+1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        source = new String();
        return  isEmoji;
    }
    
    /**
     * 获取星期数组，可以根据数组下标对应星期的数值，周日为1~周六为7
     * @return
     */
    public static String[] getWeekName(){
        String[] weekName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        return weekName;
    }
    /**
     * 去掉字符串中的中文汉字
     * @return
     */
    public static String replaceChineseChar(String str){
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);  
        Matcher mat=pat.matcher(str); 
        String repickStr = mat.replaceAll("");
        return repickStr;
    }
    /**
     * 字符串中的包含中文汉字
     * @return
     */
    public static boolean containChineseChar(String str){
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);  
        Matcher mat=pat.matcher(str); 
        return mat.find();
    }
    
    public static void main(String[] args) {
        System.out.println(containChineseChar("qweqwe4324324"));
    }
    
    /**
     * 判断是否为IP
     * 
     * @param addr
     * @return
     */
    public static boolean isIP(String addr) {
        if (addr == null || addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }
    
    /**
     * 判断是否为手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
       // Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Pattern p = Pattern.compile("^\\d{11}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 将手机号格式化为**1234
     * @param mobile
     * @return
     */
    public static String formatMobileNO(String mobile) {
        if(mobile!=null && !mobile.equals("") && mobile.length()>=4){
            return "**"+mobile.substring(mobile.length()-4);
        }
        return mobile;
    }
    
    /**
     * 格式化手机
     * @param mobile
     * @return
     */
    public static String formatMobile(String mobile) {
		if(mobile.length()<11) {
			return StringUtil.formatMobileNO(mobile);
		}else {
			return StringUtil.hideMobileNumber(mobile);
		}
	}
    
    /**
     * 正则表达式判断是否是日期
     * 支持格式"yyyy-MM-dd或者yyyy-mm-dd"
     * @param date 
     * @return
     */
    public static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp =
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }
    
    public static String getNotNullStr(Object obj){
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    
    public static String hideMobileNumber(String mobile){
    	if(mobile==null || mobile.length()<4){
    		return "";
    	}
//    	return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    	return mobile.substring(0, 3)+"****"+mobile.substring(mobile.length()-4, mobile.length());
    }
    
    public static String hideCertificateNumber(String certificate){
    	if(certificate==null || certificate.length()<8){
    		return "";
    	}
    	return certificate.substring(0,1)+"****"+certificate.substring(certificate.length()-4,certificate.length());
    }
    /**
     * 只显示前两位后两位，其余位数加*显示
     * @param certificate
     * @return
     */
    public static String hideCertificateNumber2(String certificate){
        if(certificate!=null && certificate.length()>2){
            String first = certificate.substring(0,1);
            String last = certificate.substring(certificate.length()-1);
            String mid = "";
            for(int i=0;i<certificate.length()-2;i++){
                mid+="*";
            }
            return first+mid+last;
        }else{
            return certificate;
        }
    }

	/**
	 *
	 * 邮箱脱敏
	 *
	 * @param email
	 * @return String
	 *
	 * @author mfXing
	 */
	public static String formatEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return "";
		}
		int index = StringUtils.indexOf(email, "@");
		if (index <= 1)
			return email;
		else
			return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
	}
}
