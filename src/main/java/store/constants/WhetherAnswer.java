package store.constants;

public enum WhetherAnswer {
    YES("Y", true),
    NO("N", false);

    private String answer;
    private boolean isYes;

    WhetherAnswer(String answer, boolean isYes) {
        this.answer = answer;
        this.isYes = isYes;
    }

    public boolean isYes() {
        return isYes;
    }

    public static WhetherAnswer find(String input) {
        for (WhetherAnswer answer : WhetherAnswer.values()) {
            if (answer.answer.equals(input)) {
                return answer;
            }
        }
        throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해 주세요.");
    }

}
