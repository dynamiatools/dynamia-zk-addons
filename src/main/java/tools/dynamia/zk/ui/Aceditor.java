/*
 * Copyright (c) 2014 Darren Scott - All Rights Reserved
 *
 * This program is distributed under LGPL Version 2.1 in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY.
 */
package tools.dynamia.zk.ui;

import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.Textbox;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * Aceditor Component extends Textbox and let use Ace Editor in ZK applications like any other zk component.
 */
public class Aceditor extends Textbox {

    @Serial
    private static final long serialVersionUID = 1L;

    protected List<Map<String, Object>> annotations;
    private boolean showGutter = true;
    private String mode;
    private String theme;
    private String fontSize;

    public Aceditor() {
        super();
        this.setMultiline(true);
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        render(renderer, "mode", this.mode);
        render(renderer, "theme", this.theme);
        render(renderer, "showgutter", this.showGutter);
        render(renderer, "fontSize", this.fontSize);
    }

    @Override
    public void setConstraint(String constr) {
        if (constr != null) {
            super.setConstraint(new GrammarConstraint(constr));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void updateByClient(String name, Object value) {
        if ("annotations".equals(name)) {
            this.annotations = (List<Map<String, Object>>) value;
        } else {
            super.updateByClient(name, value);
        }
    }


    public void setMode(String mode) {
        this.mode = mode;
        this.smartUpdate("mode", mode, true);
    }

    public String getMode() {
        return mode;
    }

    public void setTheme(String theme) {
        this.theme = theme;
        this.smartUpdate("theme", theme, true);
    }

    public String getTheme() {
        return theme;
    }

    public void setShowGutter(boolean showGutter) {
        if (this.showGutter != showGutter) {
            this.showGutter = showGutter;
            this.smartUpdate("showgutter", showGutter, true);
        }
    }

    public boolean getShowGutter() {
        return showGutter;
    }


    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {

        this.fontSize = fontSize;
        smartUpdate("fontSize", this.fontSize);

    }
}