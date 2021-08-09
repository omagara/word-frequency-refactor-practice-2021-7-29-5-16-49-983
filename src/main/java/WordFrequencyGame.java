import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String EMPTY_SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getResult(String sentence){



            try {

                //split the input string with 1 to n pieces of spaces
                return getWordInfoList(sentence);
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }

    private String getWordInfoList(String sentence) {
        String[] words = sentence.split(EMPTY_SPACE);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map =getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;

        wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        return reconstructWordInfo(wordInfoList);
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
