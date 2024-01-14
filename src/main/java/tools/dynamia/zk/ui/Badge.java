package tools.dynamia.zk.ui;

import org.zkoss.zhtml.Text;
import org.zkoss.zul.Span;


public class Badge extends Span {

    private String value;

    public Badge() {
        init();
    }

    public Badge(String value) {
        this.value = value;
        init();
    }

    private void init() {
        getChildren().clear();
        setSclass(null);
        if (value != null && !value.isBlank()) {
            String sclass = value.toLowerCase().trim().replace(" ", "-");
            setSclass("badge badge-" + sclass);
            appendChild(new Text(value));
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        init();
    }
}
