import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Practice {
    public static void main(String[] args) {
        System.out.println(latinPig("banana"));


    }

    /**
     * 逆转字符串——输入一个字符串，将其逆转并输出。
     *
     * @param inputString
     * @return
     */
    public static String reverse(String inputString) {
        StringBuilder output = new StringBuilder(inputString);
        for (int i = 0; i < output.length(); i++) {
            output.setCharAt(i, inputString.charAt(inputString.length() - i - 1));
        }
        return output.toString();
    }

    /**
     * 逆转字符串——输入一个字符串，将其逆转并输出。
     *
     * @param inputString
     * @return
     */
    public static String reverse2(String inputString) {
        String output = "";
        for (int i = 0; i < inputString.length(); i++) {
            output = output + inputString.charAt(inputString.length() - i - 1);
        }
        return output;
    }

    /**
     * 拉丁猪文字游戏——这是一个英语语言游戏。
     * 基本规则是将一个英语单词的第一个辅音音素的字母移动到词尾并且加上后缀-ay（譬如“banana”会变成“anana-bay”）。
     * 可以在维基百科上了解更多内容。
     *
     * @param input
     * @return
     */
    public static String latinPig(String input) {
        String[] vowelArray = {"a", "e", "i", "o", "u"};
        final Set<String> vowelSet = new HashSet<>(Arrays.asList(vowelArray));
        int firstConsonantIndex = -1;
        for (int i = 0; i < input.length(); i++) {
            if (!vowelSet.contains(input.charAt(i))) {
                firstConsonantIndex = i;
                break;
            }
        }
        if (firstConsonantIndex != -1) {
            StringBuilder output = new StringBuilder(input.substring(firstConsonantIndex + 1, input.length()));
            output.append("-");
            output.append(input.charAt(firstConsonantIndex));
            output.append("ay");
            return output.toString();
        } else {
            return input;
        }
    }

    /**
     * 统计元音字母——输入一个字符串，统计处其中元音字母的数量。更复杂点的话统计出每个元音字母的数量。
     *
     * @param input
     * @return
     */
    public static int countVowel(String input) {
        Character[] vowelArray = {'a', 'e', 'i', 'o', 'u'};
        final Set<Character> vowelSet = new HashSet<>(Arrays.asList(vowelArray));
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (vowelSet.contains(input.charAt(i))) {
                count++;
            }
        }
        return count;
/*        // 使用Java 8的Stream来实现
        Character[] vowelArray = {'a', 'e', 'i', 'o', 'u'};
        final Set<Character> vowelSet = new HashSet<>(Arrays.asList(vowelArray));
        long count = input.chars().mapToObj(i -> (char) i).filter(i -> vowelSet.contains(i)).count();
        return (int) count;*/
    }

    /**
     * 统计元音字母——输入一个字符串，统计出每个元音字母的数量。
     *
     * @param input
     * @return
     */
    public static Map<Character, Integer> countVowelMap(String input) {
        Character[] vowelArray = {'a', 'e', 'i', 'o', 'u'};
        final Set<Character> vowelSet = new HashSet<>(Arrays.asList(vowelArray));
        Map<Character, Integer> vowelCountMap = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (vowelSet.contains(c)) {
                Integer originCount = vowelCountMap.getOrDefault(c, new Integer(0));
                vowelCountMap.put(c, originCount + 1);
            }
        }
        return vowelCountMap;

/*        // 使用Java 8的Stream来实现
        Character[] vowelArray = {'a', 'e', 'i', 'o', 'u'};
        final Set<Character> vowelSet = new HashSet<>(Arrays.asList(vowelArray));
        Map<Character, Integer> vowelCountMap = input.chars().mapToObj(i -> (char) i).filter(i -> vowelSet.contains(i)).collect(
                Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)
                )
        );
        return vowelCountMap;*/
    }

    /**
     * 判断是否为回文——判断用户输入的字符串是否为回文。回文是指正反拼写形式都是一样的词，譬如“racecar”。
     *
     * @param input
     * @return
     */
    public static boolean isPalindrome(String input) {
        for (int i = 0; i < input.length() / 2; ++i) {
            char left = input.charAt(i);
            char right = input.charAt(input.length() - i - 1);
            if (left != right) return false;
        }
        return true;
    }

    /**
     * 统计字符串中的单词数目——统计字符串中单词的数目
     *
     * @return
     */
    public static Map<String, Integer> wordCount(String input) {
        //首先分割出单词，\W表示[a-zA-Z_0-9]以外的字符
        String[] words = input.split("\\W+");

        //使用Map来存储每个单词的出现次数
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : words) {
            //读取该单词之前出现的次数
            int lastCount = wordCountMap.getOrDefault(word, 0);
            wordCountMap.put(word, lastCount + 1);
        }

        return wordCountMap;
/*        // 使用Java 8的Stream来实现
        //首先分割出单词，\W表示[a-zA-Z_0-9]以外的字符
        String[] words = input.split("\\W+");
        List<String> wordList = Arrays.asList(words);
        Map<String, Integer> wordCountMap = wordList.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)
                )
        );
        return wordCountMap;*/
    }


    /**
     * 从一个文本中读出字符串并生成单词数目统计结果。
     *
     * @param path
     * @param charset
     * @return
     * @throws IOException
     */
    public static Map<String, Integer> wordCount(Path path, Charset charset) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();

        // 首先把文本按行读取
        List<String> lines = Files.readAllLines(path, charset);
        // 对每行统计单词
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                int lastCount = wordCountMap.getOrDefault(word, 0);
                wordCountMap.put(word, lastCount + 1);
            }
        }
        return wordCountMap;
/*        // 使用Java 8 Stream来实现
        Map<String, Integer> wordCountMap = Files.readAllLines(path, charset).stream().
                flatMap(line -> Arrays.stream(line.split("\\W+"))).
                map(word -> new AbstractMap.SimpleEntry<>(word, 1)).
                collect(toMap(e -> e.getKey(), e -> e.getValue(), (v1, v2) -> v1 + v2));
        return wordCountMap;*/
    }
}
