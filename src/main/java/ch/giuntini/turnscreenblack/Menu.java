package ch.giuntini.turnscreenblack;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.io.IOException;

public class Menu extends PopupMenu {

    public Menu(TrayIco ico) {
        MenuItem item0 = new MenuItem("turn screen black");
        item0.addActionListener(e -> turnScreenBlack());

        MenuItem item1 = new MenuItem("exit");
        item1.addActionListener(e -> {
            ico.close();
            JIntellitype.getInstance().unregisterHotKey(0);
            JIntellitype.getInstance().cleanUp();
        });

        add(item0);
        addSeparator();
        add(item1);

        if (JIntellitype.isJIntellitypeSupported()) {
            JIntellitype.getInstance().registerHotKey(0, JIntellitypeConstants.MOD_CONTROL, 44);
            JIntellitype.getInstance().addHotKeyListener(i -> {
                if (i == 0) {
                    turnScreenBlack();
                }
            });
        }

        //reduce memory usage
        Runtime.getRuntime().gc();
    }

    public void turnScreenBlack() {
        try {
            Runtime.getRuntime().exec("scrnsave.scr /s");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
