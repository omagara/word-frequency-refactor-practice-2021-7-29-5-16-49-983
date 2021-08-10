import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String EMPTY_SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);
            return reconstructWordInfo(wordInfoList);
        } catch (Exception e)  {
            return CALCULATE_ERROR; // TODO: customized exception
        }
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> words = Arrays.asList(sentence.split(EMPTY_SPACE));
        List<WordInfo> wordInfoList = new ArrayList<>();
            //TODO: use stream map
        for (String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }
        sortWordInfo(wordInfoList);


        return wordInfoList;
    }

    private void sortWordInfo(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
    }

    private String reconstructWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining(LINE_BREAK));
    }
}
