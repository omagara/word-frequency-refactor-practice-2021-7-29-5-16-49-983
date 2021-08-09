import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String EMPTY_SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getResult(String sentence){



            try {

                //split the input string with 1 to n pieces of spaces
                List<WordInfo> wordInfoList=getWordInfoList(sentence);
                return reconstructWordInfo(wordInfoList);
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> words = Arrays.asList(sentence.split(EMPTY_SPACE));
        List<WordInfo> wordInfoList = new ArrayList<>();

        for (String word: new HashSet<>(words)){
            int count = Collections.frequency(words,word);
            wordInfoList.add(new WordInfo(word,count));
        }
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

        return wordInfoList;
    }

    private String reconstructWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining(LINE_BREAK));
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();

        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            }

            else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return map;
    }


}
