package com.xzh;

import javax.swing.*;

/**
 * @author 向振华
 * @date 2022/05/11 10:05
 */
public class Application {

    public static void main(String[] args) {

        // 窗口
        JFrame frame = new JFrame();
        // 设置窗口标题
        frame.setTitle("窗口");
        // 设置窗口显示尺寸
        frame.setSize(400, 200);
        // 设置窗口是否可见
        frame.setVisible(true);
        // 设置窗口是否可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 面板
        JPanel panel = new JPanel();
        // 将面板添加到窗口
        frame.add(panel);

        // 标签
        JLabel label = new JLabel();
        label.setText("标签");
        // 将标签添加到面板
        panel.add(label);

    }
}
