package cn.liyw.service;

import cn.liyw.domin.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoyibin on 14:26 2018/9/27
 */
public class GsonUtil {
    private static final Gson gson = new Gson();

    /**
     * 转成Json字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json转Java对象
     */
    public static <T> T fromJson(String json, Class<T> clz) {

        return gson.fromJson(json, clz);
    }

    /**
     * Json转List集合
     */
    public static <T> List<T> jsonToList(String json, Class<T> clz) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转List集合
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        Gson mGson = new Gson();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * Json转换成Map的List集合对象
     */
    public static <T> List<Map<String, T>> toListMap(String json, Class<T> clz) {
        Type type = new TypeToken<List<Map<String, T>>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转Map对象
     */
    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        Type type = new TypeToken<Map<String, T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void main(String[] args)  {
//        String str = "Jake smiled as he rushed forward.&lt;/br&gt;&quot;Did you not understand from all that has gone down? A mindless rush like that will not work on me.&quot; Battulda shook his head as his figure turned illusory.&lt;/br&gt;Jake passed straight through Battulda, faltering forward, as he regained his balance.&lt;/br&gt;He turned around immediately, raising his sword.&lt;/br&gt;Clang!&lt;/br&gt;As expected, there was a counter attack almost instantly, and Battulda&#39;s spear pushed Jake back even further, forcing him against the wall.&lt;/br&gt;Jake could feel the cold metal of the door sear his skin.&lt;/br&gt;Battulda laughed as his spear danced in front of his hands, and he rushed forward.&lt;/br&gt;&quot;Nowhere to run?&quot; He roared as his spear dashed forward toward Jake, like a bullet.&lt;/br&gt;Jake&#39;s face was as serious as it could get as he could feel the air in front of him try to escape from the spear that was coming toward him.&lt;/br&gt;&quot;Now.&quot; He nodded as he suddenly fell on the ground, right on his knees.&lt;/br&gt;Clang!&lt;/br&gt;The spear flew right past him, striking the door behind him.&lt;/br&gt;Then, Jak";
String s = "Jake smiled as he rushed forward.\r\n&quot;Did \r\nyou not\r\n understand ";
//1.创建匹配模式
        Pattern pattern = Pattern.compile("\\r\\n｜\\r｜\\n");//匹配一个或多个数字字符

//2.选择匹配对象
        Matcher matcher = pattern.matcher(s);
//与谁匹配？与参数字符串str匹配


        int count = 0;

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, "</br>");
            } catch (Exception e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }
}
