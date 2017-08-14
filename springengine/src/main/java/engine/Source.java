package engine;

import java.io.Serializable;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer number;

    public Source() {}

    public Source(Integer number) {

        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return " Source number " + this.getNumber();
    }
}
