package demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class Mutils {
	private static final String DEBUG = "debug";
	public static boolean IsAppDebug() {
		return DEBUG.equals(System.getenv("ALMN_DEBUG"));
	}
	
	/**
	 * @生成指定长度的随机字符串
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {
		return randomString(length,"1234567890qwertyuipasdfghjklzxcvbnm1234567890QWERTYUIPASDFGHJKLZXCVBNM");
	}
	/**
	 * @生成指定长度的随机字符串
	 * @字符串内容从str中获取
	 * @param length
	 * @return
	 */
	public static String randomString(int length,String str) {
		if(StringUtils.isEmpty(str) || length<1) {
			return "";
		}
		// 声明随机对象，便于生成随机数
		Random random = new Random();
		// 字符凭借的对象
		StringBuffer buffer = new StringBuffer();
		int strlen = str.length();
		for(int i=0;i < length;i++) {
			buffer.append(str.charAt(random.nextInt(strlen)));
		}
		return buffer.toString();
	}
	/**
	 * @将指定的字符串进行md5加密处理
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		if(str == null) {
			return null;
		}
		try {
			byte[] res = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
			StringBuffer buffer = new StringBuffer();
			for(int i=0;i < res.length;i++) {
				// 先将数值类型转换为16进制
				int temp = 0xff & res[i];
				String hexString = Integer.toHexString(temp);
				if(hexString.length() == 1) {
					buffer.append("0");
				}
				buffer.append(hexString);
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	/**
	 *	将驼峰命名转换为下划线命名
	 * @param hump
	 * @return
	 */
	public static String humpToUl(String hump) {
		if(StringUtils.isEmpty(hump)) {
			return hump;
		}
		// 匹配字符
		Matcher matcher = humpPattern.matcher(hump);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(buffer, "_" + matcher.group(0).toLowerCase());
		}
		// 将最后一次匹配到内容之后的字符串追加到StringBuffer中。
		matcher.appendTail(buffer);		
		return buffer.toString();
	}
	
}
