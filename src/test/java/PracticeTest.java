import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PracticeTest {


    @Test
    void reverse() {
        assertEquals("tset", Practice.reverse("test"));
        assertEquals("hello", Practice.reverse("olleh"));

/*        //测试两种reverse的代码性能
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Practice.reverse("test");
        }
        long time2 = System.currentTimeMillis();
        System.out.println("StringBuilder版本代码耗时" + (time2 - time1) + "ms");


        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Practice.reverse2("test");
        }
        long time4 = System.currentTimeMillis();
        System.out.println("String加号版本代码耗时" + (time4 - time3) + "ms");*/
    }

    @Test
    void latinPig() {
        assertEquals("anana-bay", Practice.latinPig("banana"));
    }

    @Test
    void countVowel() {
        assertEquals(2, Practice.countVowel("hello"));
    }

    @Test
    void countVowelMap() {
        Map<Character, Integer> vowelCountMap = new HashMap<>();
        vowelCountMap.put('e', 1);
        vowelCountMap.put('a', 1);
        vowelCountMap.put('u', 2);
        vowelCountMap.put('i', 1);
        assertEquals(vowelCountMap, Practice.countVowelMap("beautiful"));
    }

    @Test
    void isPalindrome() {
        assertEquals(true, Practice.isPalindrome("racecar"));
        assertEquals(true, Practice.isPalindrome("raccar"));
        assertEquals(false, Practice.isPalindrome("test"));
        assertEquals(true, Practice.isPalindrome("teet"));
    }

    @Test
    void wordCount() {
        Map<String, Integer> wordCountMap = Practice.wordCount("hello,I have a pen, hello, I like pen");
        System.out.println(wordCountMap);
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("hello", 2);
        expectedMap.put("I", 2);
        expectedMap.put("have", 1);
        expectedMap.put("a", 1);
        expectedMap.put("pen", 2);
        expectedMap.put("like", 1);
        assertEquals(expectedMap, wordCountMap);

    }

    @Test
    void wordCount1() throws IOException {
        Map<String, Integer> wordCountMap = Practice.wordCount(Paths.get("src/test/resources/input.txt"), StandardCharsets.UTF_8);
        System.out.println(wordCountMap);
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("hello", 4);
        expectedMap.put("I", 4);
        expectedMap.put("have", 2);
        expectedMap.put("a", 2);
        expectedMap.put("pen", 4);
        expectedMap.put("like", 2);
        assertEquals(expectedMap, wordCountMap);
    }
}