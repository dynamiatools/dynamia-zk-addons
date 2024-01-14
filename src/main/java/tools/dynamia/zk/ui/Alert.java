package tools.dynamia.zk.ui;

import org.zkoss.zhtml.H4;
import org.zkoss.zhtml.I;
import org.zkoss.zhtml.Text;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;


public class Alert extends Div {

    private H4 title;
    private Text titleValue;
    private I icon;
    private String iconSclass;
    private boolean closeable;
    private Button closeableBtn;

    private String type;
    private Text value;


    public Alert() {
        initUI();
    }

    protected void initUI() {
        setZclass("alert alert-dismissible");


        closeableBtn = new Button("x");
        closeableBtn.setZclass("close");
        closeableBtn.setClientDataAttribute("dismiss", "alert");
        closeableBtn.setClientAttribute("aria-hidden", "true");
        appendChild(closeableBtn);


        title = new H4();
        title.setStyle("font-size: 110%");
        icon = new I();
        icon.setSclass("icon");
        title.appendChild(icon);

        titleValue = new Text();
        title.appendChild(titleValue);
        appendChild(title);

        value = new Text();
        appendChild(value);
        setType(type);

    }


    public void setTitle(String title) {
        titleValue.setValue(title);
    }

    public String getTitle() {
        return titleValue.getValue();
    }

    public void setValue(String value) {
        this.value.setValue(value);
    }

    public String getValue() {
        return value.getValue();
    }

    public String getIconSclass() {
        return iconSclass;
    }

    public void setIconSclass(String iconSclass) {
        this.iconSclass = iconSclass;
        icon.setSclass("icon " + iconSclass);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;

            if (type.equalsIgnoreCase("danger")) {
                type = "ERROR";
            } else if (type.equalsIgnoreCase("success")) {
                type = "NORMAL";
            }

            String sclass = "";
            String icon = null;

            switch (type.toUpperCase()) {
                case "NORMAL", "SUCCESS" -> {
                    sclass = "alert-success";
                    icon = "fas fa-check";
                }
                case "ERROR", "CRITICAL", "DANGER" -> {
                    sclass = "alert-danger";
                    icon = "fas fa-ban";
                }
                case "WARNING" -> {
                    sclass = "alert-warning";
                    icon = "fas fa-exclamation-triangle";
                }
                case "INFO" -> {
                    sclass = "alert-info";
                    icon = "fas fa-info";
                }
                case "SPECIAL" -> {
                    sclass = "bg-puple";
                    icon = "fas fa-smile";
                }
                default -> sclass = "alert-default";
            }

            setZclass("alert alert-dismissible " + sclass);
            setIconSclass(icon);
        }
    }


    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;

        closeableBtn.setVisible(this.closeable);

    }

    public Button getCloseableBtn() {
        return closeableBtn;
    }
}
