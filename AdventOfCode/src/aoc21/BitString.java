package aoc21;

class BitString {
    private final String string;
    private int loc;

    public BitString(String str) {
        this.string = str;
        this.loc = 0;
    }

    public int consume(int n) {
        return Integer.parseInt(consumeStr(n), 2);
    }

    public String consumeStr(int n) {
        String result =  string.substring(loc, loc + n);
        loc += n;
        return result;
    }

    boolean hasNext() {
        return (loc < string.length()) && !remainingZeros();
    }

    private boolean remainingZeros() {
        for (int i = loc; i < string.length(); i++) {
            if (string.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
