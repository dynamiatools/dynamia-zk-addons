package tools.dynamia.zk;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class ZKModelsUtils {


    /**
     * Fill combobox.
     *
     * @param combo the combo
     * @param data  the data
     * @param live  the live
     */
    public static void fillCombobox(Combobox combo, Collection data, boolean live) {
        fillCombobox(combo, data, null, live);
    }

    /**
     * Fill combobox.
     *
     * @param combo    the combo
     * @param data     the data
     * @param selected the selected
     * @param live     the live
     */
    public static void fillCombobox(Combobox combo, Collection data, Object selected, boolean live) {
        if (combo != null) {
            ListModelList model = new ListModelList<>(data);
            if (selected != null) {
                model.addToSelection(selected);
            }
            combo.setModel(model);
        }
    }

    /**
     * Fill combobox.
     *
     * @param combo the combo
     * @param data  the data
     */
    public static void fillCombobox(Combobox combo, List data) {
        fillCombobox(combo, data, true);
    }

    /**
     * Fill combobox.
     *
     * @param combo the combo
     * @param data  the data
     * @param live  the live
     */
    public static void fillCombobox(Combobox combo, List data, boolean live) {
        fillCombobox(combo, data, null, live);
    }

    /**
     * Fill combobox.
     *
     * @param combo the combo
     * @param data  the data
     * @param live  the live
     */
    public static void fillCombobox(Combobox combo, Object[] data, boolean live) {
        if (combo != null) {
            List dataList = Arrays.asList(data);
            fillCombobox(combo, dataList, live);
        }
    }

    /**
     * Fill listbox.
     *
     * @param listbox the listbox
     * @param data    the data
     * @param live    the live
     */
    public static void fillListbox(Listbox listbox, Collection data, boolean live) {
        if (listbox != null && data != null) {
            listbox.setModel((ListModel) null);
            if (data instanceof List) {
                listbox.setModel(new ListModelList<>((List) data, live));
            } else {
                listbox.setModel(new ListModelList<>(new ArrayList(data), live));
            }
        }
    }

    /**
     * Fill listbox.
     *
     * @param listbox the listbox
     * @param data    the data
     * @param live    the live
     */
    public static void fillListbox(Listbox listbox, List data, boolean live) {
        if (listbox != null && data != null) {
            listbox.setModel((ListModel) null);
            listbox.setModel(new ListModelList<>(data, live));
        }
    }

    /**
     * Fill listbox.
     *
     * @param listbox the listbox
     * @param data    the data
     * @param live    the live
     */
    public static void fillListbox(Listbox listbox, Object[] data, boolean live) {
        if (listbox != null && data != null) {
            List dataList = Arrays.asList(data);
            fillListbox(listbox, dataList, live);
        }
    }


}
