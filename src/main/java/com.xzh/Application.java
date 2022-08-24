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

        JLabel label1 = new JLabel("来源路径：");

        JTextField textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(200, 20));

        JButton button1 = new JButton("浏览");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(new JLabel(), "选择文件夹");

                File sourceFile = fileChooser.getSelectedFile();
                if (sourceFile != null) {
                    textField1.setText(sourceFile.getAbsolutePath());
                }
            }
        });

        JLabel label2 = new JLabel("目标路径：");

        JTextField textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(200, 20));

        JButton button2 = new JButton("浏览");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(new JLabel(), "选择文件夹");

                File sourceFile = fileChooser.getSelectedFile();
                if (sourceFile != null) {
                    textField2.setText(sourceFile.getAbsolutePath());
                }
            }
        });

        JLabel label3 = new JLabel("关键字：");

        JTextField textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(280, 20));

        JButton button3 = new JButton("根据关键字复制");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sourceFolderDirectory = textField1.getText();
                String targetFolderDirectory = textField2.getText();
                String key = textField3.getText();
                FileUtils.copyByKey(sourceFolderDirectory, targetFolderDirectory, key);
                JOptionPane.showMessageDialog(null, "已完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton button4 = new JButton("排除关键字复制");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String sourceFolderDirectory = textField1.getText();
                String targetFolderDirectory = textField2.getText();
                String key = textField3.getText();
                FileUtils.copyWithoutKey(sourceFolderDirectory, targetFolderDirectory, key);
                JOptionPane.showMessageDialog(null, "已完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JFrameBuilder.init(label1, textField1, button1, label2, textField2, button2, label3, textField3, button3, button4);
    }
}
