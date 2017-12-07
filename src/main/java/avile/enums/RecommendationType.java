package avile.enums;

public enum RecommendationType {
    BOOK("book"), // 0
    PODCAST("podcast"), // 1
    VIDEO("video"), // 2
    BLOGPOST("blogpost"); // 3

    private final String type;

    RecommendationType(String s) {
        type = s;
    }

    @Override
    public String toString() {
        return this.type;
    }
}