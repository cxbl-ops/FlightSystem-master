package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowerCardSender extends JFrame {
    private final JComboBox<String> flowerComboBox;
    private final JTextField recipientTextField;

    public FlowerCardSender() {
        setTitle("送花祝福");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // 设置布局为边界布局
        setLayout(new BorderLayout());

        // 创建标题面板
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("六一儿童节送花祝福");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        // 创建表单面板
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel flowerLabel = new JLabel("花朵类型:");
        flowerComboBox = new JComboBox<>(new String[]{"玫瑰花", "康乃馨", "百合花"});
        JLabel recipientLabel = new JLabel("接收者:");
        recipientTextField = new JTextField();
        formPanel.add(flowerLabel);
        formPanel.add(flowerComboBox);
        formPanel.add(recipientLabel);
        formPanel.add(recipientTextField);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sendButton = new JButton("发送");
        sendButton.setPreferredSize(new Dimension(100, 40));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendFlowerCard();
            }
        });
        buttonPanel.add(sendButton);

        // 添加面板到主窗口
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void sendFlowerCard() {
        String flowerType = (String) flowerComboBox.getSelectedItem();
        String recipient = recipientTextField.getText();

        // TODO: 使用邮件或短信服务发送花卡片
        // 在这里添加你的代码

        JOptionPane.showMessageDialog(this, "花卡片已发送给" + recipient + "，祝福已传达！");

        // 清空表单
        recipientTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlowerCardSender();
            }
        });
    }
}