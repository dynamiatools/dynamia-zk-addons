/*
 * Copyright (C) 2023 Dynamia Soluciones IT S.A.S - NIT 900302344-1
 * Colombia / South America
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tools.dynamia.zk.ui;

import org.zkoss.util.Locales;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import tools.dynamia.zk.ZKModelsUtils;

import java.util.*;

public class LocaleCombobox extends Combobox {

    /**
     *
     */
    private static final long serialVersionUID = 4710970528102748639L;

    private String selected;

    public LocaleCombobox() {
        setReadonly(true);
        initModel();

    }

    private void initModel() {

        setItemRenderer((item, data, index) -> {
            Locale locale = (Locale) data;
            item.setLabel(locale.getDisplayName(Locales.getCurrent()));
            item.setValue(locale.toLanguageTag());
        });

        List<Locale> locales = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));

        locales.sort(Comparator.comparing(Locale::getDisplayName));

        ZKModelsUtils.fillCombobox(this, locales, true);

        String defaultLocale = Locale.getDefault().toLanguageTag();
        setSelected(defaultLocale);

    }

    public String getSelected() {
        selected = null;
        if (getSelectedItem() != null) {
            selected = getSelectedItem().getValue();
        }
        return selected;
    }

    public void setSelected(String selected) {
        if (selected != this.selected) {
            this.selected = selected;
            try {
                String[] parts = selected.split("-");

                Locale locale = new Locale(parts[0]);
                if (parts.length == 2) {
                    locale = new Locale(parts[0], parts[1]);
                }
                ListModelList model = (ListModelList) getModel();
                //noinspection unchecked
                model.addToSelection(locale);
            } catch (Exception e) {
                // ignore
            }
        }
    }

}
