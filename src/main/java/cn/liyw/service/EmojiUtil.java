package cn.liyw.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Emoji转换工具类
 *
 * @version $ID$
 * @since 2018/11/12 13:44
 */
public class EmojiUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(EmojiUtil.class);

    /**
     * emoji表情转换成字符串
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String emojiEncode(String str) throws UnsupportedEncodingException {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb,  unicodeEncode(matcher.group(1)) );
        }
        matcher.appendTail(sb);
        LOGGER.debug("emojiEncode " + str + " to " + sb.toString() + ", len：" + sb.length());
        return sb.toString();
    }

    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\\\u" + hexB;
        }
        return unicodeBytes;
    }
    /**
     * 将包含字符串转换成emoji表情
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException exception
     */
    public static String emojiDecode(String str) throws UnsupportedEncodingException {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("emojiDecode error", e);
                throw e;
            }
        }
        matcher.appendTail(sb);
        LOGGER.debug("emojiDecode " + str + " to " + sb.toString());
        return sb.toString();
    }

    /**
     * @param string
     * @return 转换之后的内容
     * @Title: unicodeDecode
     * @Description: unicode解码 将Unicode的编码转换为中文
     */
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str= "datadd🌹🔥🚛🕧";
        String set = emojiEncode(str);
        String de = unicodeDecode(set);
        System.out.println("111");

    }
}
