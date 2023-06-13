import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String convertedString = "Hallo hier ist Nico, der ein wenig kranken Scheiß probieren möchte, dafür benötigt er aber auch kurze Wörter wie Öl, AA, Q und solche Sachen.";
        String[] words = convertedString.split(" ");
        StringJoiner joiner = new StringJoiner(" ");
        for (String word : words) {
            joiner.add(Main.scamble(word));
        }
        System.out.println(joiner);
    }


    public static String scamble(final String word) {
        Pattern pattern = Pattern.compile("[.,?!-+*']");
        Stream<MatchResult> results = pattern.matcher(word).results();
        if (word.length() <= 2) {
            return word;
        }
        List<MatchResult> collect = results.toList();
        List<Character> characters = word.chars().mapToObj(i -> (char) i).toList();
        List<Character> scramble = new ArrayList<>(characters.subList(1, characters.size() - 1));
        Collections.shuffle(scramble);
        scramble.add(0, characters.get(0));
        scramble.add(characters.get(characters.size() - 1));
        return scramble.stream().map(String::valueOf).collect(Collectors.joining());
    }

}