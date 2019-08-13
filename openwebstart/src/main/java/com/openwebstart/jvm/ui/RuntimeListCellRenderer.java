package com.openwebstart.jvm.ui;

import com.openwebstart.jvm.runtimes.LocalJavaRuntime;
import com.openwebstart.jvm.os.OperationSystem;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Objects;
import java.util.Optional;

public class RuntimeListCellRenderer implements ListCellRenderer<LocalJavaRuntime> {

    private final JPanel cellContent;

    private final JLabel versionLabel;

    public RuntimeListCellRenderer() {
        cellContent = new JPanel();
        cellContent.setLayout(new BorderLayout(12, 12));

        versionLabel = new JLabel();

        final IconComponent actionsIcon = new IconComponent(new ImageIcon(IconComponent.class.getResource("settings-32.png")));

        cellContent.add(versionLabel, BorderLayout.CENTER);
        cellContent.add(actionsIcon, BorderLayout.EAST);

        cellContent.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    @Override
    public Component getListCellRendererComponent(final JList<? extends LocalJavaRuntime> list, final LocalJavaRuntime value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        versionLabel.setText(Optional.ofNullable(value).map(v -> v.getVersion()).orElse("UNKNOWN"));

        if (!isSelected) {
            if(index%2 == 0) {
                cellContent.setBackground(Color.WHITE);
            } else {
                cellContent.setBackground(new Color(246, 246, 246));
            }
        } else {
            cellContent.setBackground(Color.CYAN);
        }

        if(Optional.ofNullable(value).map(v -> v.isActive()).orElse(false)) {
            versionLabel.setForeground(Color.BLACK);
        } else {
            versionLabel.setForeground(Color.LIGHT_GRAY);
        }


        return cellContent;
    }
}
