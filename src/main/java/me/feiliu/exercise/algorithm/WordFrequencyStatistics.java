package me.feiliu.exercise.algorithm;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词频统计 word frequency statistics
 *
 * 来源:<<函数式编程思维>> 1.1
 *
 * @author liufei
 */
public class WordFrequencyStatistics {

    /**
     * 不计入统计的单词
     */
    private Set<String> NON_WORD = new HashSet<String>() {

        {
            add("the");
            add("and");
            add("of");
            add("to");
            add("a");
            add("i");
            add("it");
            add("in");
            add("or");
            add("is");
            add("d");
            add("s");
            add("as");
            add("so");
            add("but");
            add("be");
        }
    };

    /**
     * 方法1：命令式编程
     *
     * @param words 待分词的字符串
     */
    public Map<String, Integer> wordFrequencyOne(String words) {
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        Matcher m = Pattern.compile("\\w+").matcher(words);
        while (m.find()) {
            String word = m.group().toLowerCase();
            if (!NON_WORD.contains(word)) {
                /*if (wordMap.get(word) == null) {
                    wordMap.put(word, 1);
                } else {
                    wordMap.put(word, wordMap.get(word) + 1);
                }*/


                wordMap.merge(word, 1, (a, b) -> a + 1);
            }
        }
        return wordMap;
    }

    /**
     * 方法2：函数式编程
     *
     * @param words 待分词的字符串
     */
    public Map<String, Integer> wordFrequencyTwo(String words) {
        TreeMap<String, Integer> wordMap = new TreeMap<String, Integer>();
        regexToList(words, "\\w+").stream().map(String::toLowerCase).filter(w -> !NON_WORD.contains(w))
                .forEach(w -> wordMap.put(w, wordMap.getOrDefault(w, 0) + 1));
        return wordMap;
    }

    private List<String> regexToList(String words, String regex) {
        List<String> wordList = new ArrayList<String>();
        Matcher m = Pattern.compile(regex).matcher(words);
        while (m.find()) {
            wordList.add(m.group());
        }
        return wordList;
    }

    /**
     * 排序并输出
     *
     * @param map 保存词频的map,key是单词,value是出现次数
     */
    public void out(Map<String, Integer> map) {
        map.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue()))).forEach(System.out::println);
    }

    public static void main(String[] args) {
        // 测试字符串
        String words = "Hi Spring fans and welcome to This Week in Spring from the premier JVM-language event SpringOne Platform 2017! There is a massive amount of stuff to cover, especially in light of SpringOne Platform, so let’s get to it!";
        WordFrequencyStatistics wfs = new WordFrequencyStatistics();

        // 命令式编程测试
//        System.out.println("命令式编程结果：");
//        Map<String, Integer> wordFreqMapOne = wfs.wordFrequencyOne(words);
//        wordFreqMapOne.keySet().forEach(e -> System.out.println(e + ":" + wordFreqMapOne.get(e)));

//        System.out.println("-------------");

        // 函数式编程测试
//        System.out.println("函数式编程结果：");
//        Map<String, Integer> wordFreqMapTwo = wfs.wordFrequencyTwo(words);
//        System.out.println("排序前的词频：");
//        wordFreqMapTwo.keySet().forEach(e -> System.out.println(e + ":" + wordFreqMapTwo.get(e)));

//        System.out.println("排序后的词频：");
        // List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordFreqMapTwo.entrySet());
        // Collections.sort(entryList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        // entryList.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

        // map按value排序并打印
//        wordFreqMapTwo.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue()))).forEach(System.out::println);

        // 从文件中读取：
        // InputStream is = WordFrequencyStatistics.class.getResourceAsStream("/file/wordFrequency.txt");
        // String path = WordFrequencyStatistics.class.getResource("/").getPath();
        // File f = new File(path + "file/wordFrequency.txt");
        File f = new File("/Users/liufei/Desktop/wordFrequency.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            List<String> list = new ArrayList<>();

            int a;
            while ((a = br.read()) != -1) {
                list.add((char) a + br.readLine());
            }

            StringBuilder sb = new StringBuilder("");

            list.forEach(sb::append);

            // FileUtils.readLines(f, Charset.forName("UTF-8")).forEach(sb::append);

            // wfs.out(wfs.wordFrequencyTwo(sb.toString()));
            wfs.out(wfs.wordFrequencyOne(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
