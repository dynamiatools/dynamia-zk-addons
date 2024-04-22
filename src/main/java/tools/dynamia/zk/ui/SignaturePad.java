package tools.dynamia.zk.ui;

import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;

import java.io.IOException;
import java.util.Objects;

public class SignaturePad extends HtmlBasedComponent {

    private String value;
    private String penColor;
    private String minWidth;
    private String maxWidth;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            smartUpdate("value", value);
        }
    }

    public String getPenColor() {
        return penColor;
    }

    public void setPenColor(String penColor) {
        if (!Objects.equals(this.penColor, penColor)) {
            this.penColor = penColor;
            smartUpdate("penColor", penColor);
        }
    }

    public String getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(String minWidth) {
        if (!Objects.equals(this.minWidth, minWidth)) {
            this.minWidth = minWidth;
            smartUpdate("minWidth", minWidth);
        }
    }

    public String getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(String maxWidth) {
        if (!Objects.equals(this.maxWidth, maxWidth)) {
            this.maxWidth = maxWidth;
            smartUpdate("maxWidth", maxWidth);
        }
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        renderer.render("value", this.value);
        renderer.render("penColor", this.penColor);
        renderer.render("minWidth", this.minWidth);
        renderer.render("maxWidth", this.maxWidth);
    }
}
