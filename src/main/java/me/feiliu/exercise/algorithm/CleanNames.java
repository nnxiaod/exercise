package me.feiliu.exercise.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 题目：假设有一个名字列表，先需要，将除去单个字符条目之外的列表内容，放在一个逗号分隔的字符串返回，且每个名字的首字母要大写。
 *
 * @author liufei
 */
public class CleanNames {

    /**
     * 命令式写法
     *
     * @param names 原名字
     */
    public String processOne(List<String> names) {
        if (names == null || names.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            if (isNotBlank(name) && name.length() > 1) {
                sb.append(capitalize(name)).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 函数式写法
     *
     * @param names 原名字
     */
    public String processTwo(List<String> names) {
        if (names == null || names.size() == 0) {
            return "";
        }
        // return names.stream().filter(name -> name != null).filter(name -> name.length() > 1).map(this::capitalize).collect(Collectors.joining(","));
        // return names.stream().filter(name -> name.length() > 1).map(this::capitalize).collect(Collectors.joining(","));
        return names.parallelStream().filter(this::isNotBlank).filter(name -> name.length() > 1).map(this::capitalize).collect(Collectors.joining(","));
    }

    private boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    private boolean isBlank(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] cs = s.toCharArray();
        boolean blank = true;
        for (char c : cs) {
            if (c != ' ' && c != '\r' && c != '\n' && c != '\b') {
                blank = false;
                break;
            }
        }
        return blank;
    }

    /**
     * 将首字母大写
     *
     * @param s 原字符串
     */
    private String capitalize(String s) {
        if (isBlank(s)) {
            return "";
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<String>() {{
            add("t");
            add("o");
            add("m");
            add("tom");
            add("jerry");
            add("green");
            add("a");
            add("tim");
            add("   ");
            add("\r");
            add("\n");
            add("abc");
            add(" ");
            add("     ");
            add("tt");
            add(null);
        }};
        CleanNames cn = new CleanNames();
        System.out.println("命令式写法：" + cn.processOne(names));
        System.out.println("函数式写法：" + cn.processTwo(names));
    }
}
