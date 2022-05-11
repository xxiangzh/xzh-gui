package com.xzh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author 向振华
 * @date 2022/05/11 10:05
 */
public class Application {

    public static void main(String[] args) {

        JLabel label = new JLabel("选择文件：");

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 20));

        JButton button1 = new JButton("浏览");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showDialog(new JLabel(), "选择文件");

                File sourceFile = fileChooser.getSelectedFile();
                if (sourceFile != null && sourceFile.isFile()) {
                    textField.setText(sourceFile.getAbsolutePath());
                }
            }
        });

        JButton button2 = new JButton("执行");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File sourceFile = new File(textField.getText());
                // TODO
            }
        });

        JButton button3 = new JButton("下载");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(new JLabel(), "选择路径");

                File targetDirectory = fileChooser.getSelectedFile();
                if (targetDirectory != null && targetDirectory.isDirectory()) {
                    String targetDirectoryAbsolutePath = targetDirectory.getAbsolutePath();
                    // TODO
                }
            }
        });

        JFrameBuilder.init(label, textField, button1, button2, button3);
    }
}
