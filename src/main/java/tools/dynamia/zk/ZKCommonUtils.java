package tools.dynamia.zk;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ZKCommonUtils {

    /**
     * Return current {@link Desktop} first page
     *
     * @return
     */
    public static Page getFirstPage() {
        Desktop desktop = Executions.getCurrent().getDesktop();
        return desktop.getFirstPage();
    }


    /**
     * Create a zk {@link Window} component
     *
     * @param title
     * @param closeable
     * @param maximizable
     * @param width
     * @param height
     * @param onClose
     * @return
     */
    public static Window createWindow(String title, boolean closeable, boolean maximizable, String width, String height, EventListener<Event> onClose) {
        final Window dialog = new Window();
        dialog.setTitle(title);
        dialog.setClosable(closeable);
        dialog.setMaximizable(maximizable);
        dialog.setBorder("normal");
        dialog.setPage(getFirstPage());
        dialog.setWidth(width);
        dialog.setHeight(height);

        dialog.addEventListener(Events.ON_CANCEL, e -> {
            if (dialog.isClosable()) {
                dialog.detach();
            }
        });

        if (onClose != null) {
            dialog.addEventListener(Events.ON_CLOSE, onClose);
        }
        return dialog;
    }

    /**
     * Create a ZK {@link Window} dialog and attached in the first current desktop page
     *
     * @param title
     * @param width
     * @param height
     * @param onClose
     * @return
     */
    public static Window createWindow(String title, String width, String height, EventListener<Event> onClose) {
        return createWindow(title, true, false, width, height, onClose);
    }

    public static Window createWindow(String title, String width, String height) {
        return createWindow(title, width, height, null);
    }

    /**
     * Show exception as error messages
     */

    public static void showErrorMessage(Exception e) {
        showErrorMessage(e.getMessage());
    }

    /**
     * Show a {@link Messagebox} error message
     *
     * @param message
     */
    public static void showErrorMessage(String message) {
        showErrorMessage(message, "Error");
    }

    /**
     * Show a {@link Messagebox} error message with title
     *
     * @param message
     * @param title
     */
    public static void showErrorMessage(String message, String title) {
        Messagebox.show(message, title, Messagebox.OK, Messagebox.ERROR);
    }

    /**
     * Show a {@link Messagebox} EXCLAMATION message
     *
     * @param message
     */
    public static void showWarningMessage(String message) {
        showWarningMessage(message, "Warning");
    }

    /**
     * Show a {@link Messagebox} EXCLAMATION message with title
     *
     * @param message
     * @param title
     */
    public static void showWarningMessage(String message, String title) {
        Messagebox.show(message, title, Messagebox.OK, Messagebox.EXCLAMATION);
    }
}
