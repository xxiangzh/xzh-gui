package com.xzh;

import javax.swing.*;
import java.awt.*;

/**
 * @author 向振华
 * @date 2022/05/11 16:26
 */
public class JFrameBuilder {

    public static void init(Component... components) {
        init("文件处理", 400, 160, components);
    }

    public static void init(String title, int width, int height, Component... components) {
        // 面板
        JPanel panel = new JPanel();
        for (Component component : components) {
            panel.add(component);
        }

        // 窗口
        JFrame frame = new JFrame();
        // 设置窗口标题
        frame.setTitle(title);
        // 设置窗口显示尺寸
        frame.setSize(width, height);
        // 设置窗口是否可以调整大小
        frame.setResizable(false);
        // 设置窗口是否可见
        frame.setVisible(true);
        // 设置窗口是否可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口居中
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
        // 将面板添加到窗口
        frame.add(panel);
    }
}
