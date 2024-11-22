package store.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {

    private final static String ORDER_FORM = "\\[(.*)-(.*)]";


    public static Map<String, Integer> parse(String orders) {
        Map<String, Integer> orderPair = new HashMap<>();

        Pattern pattern = Pattern.compile(ORDER_FORM);
        Matcher matcher = pattern.matcher(orders);

        try {
            while (matcher.find()) {
                orderPair.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수량은 숫자로 입력해주세요.");
        }
        if (orderPair.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
        return orderPair;
    }

}
