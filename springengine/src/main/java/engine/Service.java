package engine;

import java.io.Serializable;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer number;

    public Service() {}

    public Service(Integer number) {

        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return " Service number " + this.getNumber();
    }
}
