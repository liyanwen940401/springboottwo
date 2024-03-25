package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.domin.RNP2;
import cn.liyw.domin.User;
import cn.liyw.risk.RiskModule;
import cn.liyw.risk.RiskModuleFactory;
import cn.liyw.service.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RNPtest {

    @Autowired
    private RiskModuleFactory riskModuleFactory;

    static Set<String> operator = new HashSet<String>() {{
        add("&");
        add("!");
        add("|");
    }};

    static Map<String, Integer> operatorLevelMap = new HashMap<String, Integer>() {{
        put("!", 2);
        put("&", 1);
        put("|", 0);
    }};

    static Set<String> brackets = new HashSet<String>() {{
        add("(");
        add(")");
    }};

    static String bracketLeft = "(";
    static String bracketRight = ")";

    static String opType = "$";
    static String opStart = "{";
    static String opStop = "}";
    static String space = " ";
    public static final String MODULE_TRUE = "Y";
    public static final String MODULE_FALSE = "N";

    public boolean compute(LinkedList<String> s1, User user) {
        LinkedList<String> stack = new LinkedList<String>();
        if (s1.size() == 1) {
            String tmp = s1.pop();
            return process4(tmp, user);
        }
        while (s1.size() != 0) {
            List<String> nameList = new ArrayList<>();
            boolean ret = false;
            String tmp = s1.pop();
            if (operator.contains(tmp)) {
                String d2 = stack.pop();
                if (tmp.equals("&")) {
                    String d1 = stack.pop();
                    ret = process1(getList(d1, d2), user);
                } else if (tmp.equals("|")) {
                    String d1 = stack.pop();
                    ret = process2(getList(d1, d2), user);
                } else if (tmp.equals("!")) {
                    ret = process3(d2, user);
                } else {

                }
                if (ret) {
                    stack.push(MODULE_TRUE);
                } else {
                    stack.push(MODULE_FALSE);
                }
            } else {
                stack.push(tmp);
            }
        }
        String ret = stack.pop();
        if (ret.equals(MODULE_TRUE)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createHandler(String opType, User user) {
        if (opType.equals(MODULE_TRUE)) {
            return true;
        } else if (opType.equals(MODULE_FALSE)) {
            return false;
        }
        // 根据配置创建风控模块对象
        RiskModule riskModule = null;
        try {
            riskModule = riskModuleFactory.create(opType);
        } catch (Exception ex) {
            return true;
        }
        boolean ret = riskModule.invokeAsync(user);
        return ret;
    }

    public boolean process1(List<String> list, User user) {
        //&
        String d1 = list.get(0);
        String d2 = list.get(1);
        boolean ret = createHandler(d1, user);
        if (!ret) {
            return false;
        }
        return createHandler(d2, user);
    }

    private List<String> getList(String d1, String d2) {
        List<String> nameList = new ArrayList<>();
        if (d2.equals(MODULE_TRUE) || d2.equals(MODULE_FALSE)) {
            nameList.add(d2);
            nameList.add(d1);
        } else {
            nameList.add(d1);
            nameList.add(d2);
        }
        return nameList;
    }

    public boolean process2(List<String> list, User user) {
        //|
        String d1 = list.get(0);
        String d2 = list.get(1);
        boolean ret = createHandler(d1, user);
        if (!ret) {
            return createHandler(d2, user);
        } else {
            return true;
        }

    }

    public boolean process3(String d1, User user) {
        boolean ret = createHandler(d1, user);
        return !ret;

    }

    public boolean process4(String d1, User user) {
        boolean ret = createHandler(d1, user);
        return ret;

    }

    /**
     * 生成逆波兰式
     */
    public LinkedList<String> RPN(String str) {

        StringHolder holder = new StringHolder(str);

        // 临时存储运算符
        LinkedList<String> s1 = new LinkedList<String>();
        // 输入逆波兰式
        LinkedList<String> s2 = new LinkedList<String>();
        if (str.length() == 1) {
            s2.add(str);
            return s2;
        }
        while (!holder.finished()) {
            // 5. 重复以下的1~4步，直至处理完所有的输入字符
            String tmp = holder.next();

            while (space.equals(tmp)) {
                tmp = holder.next();
            }
            if (tmp == null) {
                break;
            }

            if (!operator.contains(tmp) && !brackets.contains(tmp)) {
                // 1. 若取出的字符是操作数，则分析出完整的运算数，该操作数直接送入S2栈
                s2.push(getOp(holder));
            } else if (operator.contains(tmp)) {
                // 2. 若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较
                Integer operatorLevel = operatorLevelMap.get(tmp);
                String s1Top = s1.peekFirst();
                Integer s1TopLevel = operatorLevelMap.get(s1Top);
                if (s1.size() == 0 || bracketLeft.equals(s1Top) || (operatorLevel - s1TopLevel) > 0) {
                    // 2.1 如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈
                    s1.push(tmp);
                } else {
                    // 2.2.1 否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于（不包括等于）该运算符优先级
                    while (s1.size() != 0 && !bracketLeft.equals(s1Top) && (operatorLevel - s1TopLevel) <= 0) {
                        s2.push(s1.pop());
                        s1Top = s1.peekFirst();
                        s1TopLevel = operatorLevelMap.get(s1Top);
                    }
                    // 2.2.2 最后将该运算符送入S1栈。
                    s1.push(tmp);
                }
            } else if (bracketLeft.equals(tmp)) {
                // 3. 若取出的字符是“（”，则直接送入S1栈顶。
                s1.push(tmp);
            } else if (bracketRight.equals(tmp)) {
                // 4. 若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
                while (s1.size() != 0 && !bracketLeft.equals(s1.peekFirst())) {
                    s2.push(s1.pop());
                }
                if (bracketLeft.equals(s1.peekFirst())) {
                    s1.pop();
                }
            }
        }
        // 6. 将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
        while (s1.size() != 0) {
            if (s1.peekFirst().equals(bracketLeft)) {
                s1.pop();
            } else {
                s2.push(s1.pop());
            }
        }
        Collections.reverse(s2);
        return s2;
    }

    /**
     * 获取操作数
     */
    public String getOp(StringHolder holder) {
        if (opType.equals(holder.current())) {
            // 变量 除非遇到结束符，否则不结束
            StringBuilder builder = new StringBuilder(opType);
            // 如果字符未结束，并且下一个字符不是变量结束符。就添加下一个字符
            while (!holder.finished() && !opStop.equals(holder.next())) {
                builder.append(holder.current());
            }
            if (!opStop.equals(holder.current())) {
                throw new RuntimeException(String.format("变量%s有误", builder.toString()));
            }
            builder.append(opStop);
            return builder.toString();
        } else {
            // 常量
            StringBuilder builder = new StringBuilder(holder.current());
            while (!holder.finished()) {
                if (!constantEnd(holder.next())) {
                    builder.append(holder.current());
                } else {
                    holder.back();
                    break;
                }
            }
            return builder.toString();
        }
    }

    public static String constant = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890._";

    public boolean constantEnd(String tmp) {
        if (space.equals(tmp) || operator.contains(tmp) || brackets.contains(tmp)) {
            // 空格 操作符 括号
            return true;
        }
        if (!constant.contains(tmp)) {
            // 常规字符
            return true;
        }
        return false;
    }

    public static class StringHolder {
        private String str;
        private int pos;
        private String tmp;

        public StringHolder(String str) {
            this.str = str;
            this.pos = -1;
        }

        public String next() {
            if (pos + 1 == str.length()) {
                this.tmp = null;
            } else {
                pos++;
                this.tmp = String.valueOf(str.charAt(pos));
            }
            return this.tmp;
        }

        public void back() {
            this.pos--;
            this.tmp = String.valueOf(str.charAt(pos));
        }

        public String current() {
            return tmp;
        }

        public boolean finished() {
            return pos + 1 == str.length();
        }
    }

    @Test
    public void T_add() throws Exception {
        User user = new User();
        user.setName("zhang");
        user.setGearId(1);
        user.setAge(2);
        user.setId(3l);
        RNP2 rnp2 = new RNP2();
        LinkedList<String> list = rnp2.RPN("a&(e|f)|d|(c|g&h)");
        System.out.println(GsonUtil.toJson(user));
        Boolean ret = compute(list, user);
        System.out.println(ret);
    }

}