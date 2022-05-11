package com.xzh;

import javax.swing.*;
import java.awt.*;

/**
 * @author 向振华
 * @date 2022/05/11 10:05
 */
public class Application {

    public static void main(String[] args) {

        // 标签
        JLabel label = new JLabel();
        label.setText("标签");

        // 单行文本框
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 20));

        // 按钮
        JButton button = new JButton("按钮");

        JFrameBuilder.init(label, textField, button);
    }
}
