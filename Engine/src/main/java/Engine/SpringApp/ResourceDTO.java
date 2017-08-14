package Engine.SpringApp;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ResourceDTO {

    private String input;
    private String output;
    private Integer exchange_rate;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(Integer exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
