package com.xzh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 向振华
 * @date 2022/05/11 10:05
 */
public class Application {

    public static void main(String[] args) {

        JLabel label1 = new JLabel("文件夹路径：");

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


        JButton button2 = new JButton("确认重命名");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String sourceFolderDirectory = textField1.getText();

                String msg = "已完成！";
                try {
                    rename(sourceFolderDirectory);
                } catch (Exception e) {
                    msg = e.getMessage();
                }

                JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JFrameBuilder.init(label1, textField1, button1, button2);
    }

    private static void rename(String sourceFolderDirectory) {
        if (sourceFolderDirectory == null || sourceFolderDirectory.isEmpty()) {
            return;
        }
        List<File> fileList = getFileList(sourceFolderDirectory);
        for (File file : fileList) {
            String dateTime = MetadataUtils.getDateTime(file, "yyyy_MM_dd_HH_mm_ss");
            if (dateTime == null || dateTime.isEmpty()) {
                continue;
            }
            rename(file, dateTime);
        }
    }

    public static List<File> getFileList(String sourceFolderDirectory) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(sourceFolderDirectory);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files == null) {
            return fileList;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                List<File> innerFileList = getFileList(file.getAbsolutePath());
                if (!innerFileList.isEmpty()) {
                    fileList.addAll(innerFileList);
                }
            } else if (file.isFile()) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public static void rename(File file, String newName) {
        String[] fileNames = splitFileName(file.getAbsolutePath());
        fileNames[1] = newName != null ? newName : fileNames[1];
        String newAbsolutePath = fileNames[0] + fileNames[1] + fileNames[2];
        // 重命名
        file.renameTo(new File(newAbsolutePath));
    }

    public static String[] splitFileName(String sourceAbsolutePath) {
        String folderName = sourceAbsolutePath.substring(0, sourceAbsolutePath.lastIndexOf(File.separator) + 1);
        String fileName = sourceAbsolutePath.substring(sourceAbsolutePath.lastIndexOf(File.separator) + 1);
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            return new String[]{folderName, fileName.substring(0, i), fileName.substring(i)};
        } else {
            return new String[]{folderName, fileName, ""};
        }
    }
}
