import java.util.*;

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // Handles union: a,b,c
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length() &&
               expression.charAt(index) == ',') {

            index++; // skip ','

            result.addAll(parseTerm());
        }

        return result;
    }

    // Handles concatenation: abc, {a,b}c, a{b,c}
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != ','
                && expression.charAt(index) != '}') {

            Set<String> next;

            if (expression.charAt(index) == '{') {
                index++; // skip '{'

                next = parseExpression();

                index++; // skip '}'
            } else {
                char ch = expression.charAt(index++);

                next = new HashSet<>();
                next.add(String.valueOf(ch));
            }

            result = concatenate(result, next);
        }

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}