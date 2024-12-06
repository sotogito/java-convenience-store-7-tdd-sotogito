package store.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {

    private final static String ORDER_FORM = "\\[(.*?)-(.*?)]"; //note 와~~~~~~탐욕적 탐색 개큰실수
    // private final static String ORDER_FORM = "\\[(.*?)-(\\d+)]";


    public static Map<String, Integer> parse(String orders) {
        Map<String, Integer> orderPair = new HashMap<>();

        String[] splitOrders = orders.split(",");
        Pattern pattern = Pattern.compile(ORDER_FORM); //한번만 하면 됨

        try {
            for (String item : splitOrders) {
                item = item.trim();
                validateOneOrderForm(item); //string 상태에서 기호 1개씩 있는지 확인

                Matcher matcher = pattern.matcher(item);
                if (matcher.find()) { //find가 아니라 matches로 확인 -> 그래야 숫자 파싱 예외 잡음
                    String productName = matcher.group(1);
                    int quantity = Integer.parseInt(matcher.group(2));
                    orderPair.put(productName, quantity);
                    continue;
                }
                throw new IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요: " + item);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수량은 숫자로 입력해주세요.");
        }

        if (orderPair.isEmpty()) {
            throw new IllegalArgumentException("주문 항목이 비어 있습니다. 올바른 형식으로 입력해 주세요.");
        }

        return orderPair;
    }

    private static void validateOneOrderForm(String removeParentheses) {
        validateNoParentheses(removeParentheses);
        validateNoHyphen(removeParentheses);
    }

    private static void validateNoParentheses(String splitCommaValue) {
        int openCount = countContains(splitCommaValue, "[");
        int closeCount = countContains(splitCommaValue, "]");

        if (openCount != 1 || closeCount != 1) {
            throw new IllegalArgumentException("괄호 문제");
        }
    }

    private static void validateNoHyphen(String splitCommaValue) {
        int containCount = countContains(splitCommaValue, "-");
        if (containCount != 1) {
            throw new IllegalArgumentException("마이너스 문제");
        }
    }

    private static int countContains(String orderForm, String delimiter) {
        char delimiterChar = delimiter.charAt(0);
        return (int) orderForm.chars()
                .filter(c -> c == delimiterChar)
                .count();
    }

}
