package Engine;

public class ExchangeItem {

    private final String input;
    private final String output;
    private final Integer exchangeRate;

    public ExchangeItem(String input, String output, Integer exchangeRate) {

        this.input = input;
        this.output = output;
        this.exchangeRate = exchangeRate;
    }

    public String getInput() {
        return this.input;
    }

    public String getOutput() {
        return this.output;
    }

    public Integer getExchangeRate() {
        return exchangeRate;
    }
}
