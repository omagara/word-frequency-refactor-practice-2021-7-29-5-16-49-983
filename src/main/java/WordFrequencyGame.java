import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

public class WordFrequencyGame {

    public static final String EMPTY_SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);
            return reconstructWordInfo(sortWordInfo(wordInfoList));
        } catch (Exception error)  {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> words = Arrays.asList(sentence.split(EMPTY_SPACE));
        Set<String> wordInfo = new HashSet<>(words);
        return wordInfo.stream()
                .map(word -> new WordInfo(word, frequency(words, word)))
                .collect(Collectors.toList());
    }

    private List<WordInfo> sortWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .sorted((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount())
                .collect(Collectors.toList());
    }

    private String reconstructWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining(LINE_BREAK));
    }
}
