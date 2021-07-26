package com.kay.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

public class ToiletPanel extends JPanel {

	protected JLabel lblNewLabel;
	protected JLabel lblNewLabel_1;
	protected JLabel lblNewLabel_2;
	protected JPanel panel_1;

	/**
	 * Create the panel.
	 */
	public ToiletPanel() {
		setBackground(new Color(230, 230, 250));
		setPreferredSize(new Dimension(324, 30));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("라벨");
		panel_1.add(lblNewLabel);
		lblNewLabel.setPreferredSize(new Dimension(48, 28));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 240, 245));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("거리");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setPreferredSize(new Dimension(68, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		lblNewLabel_2 = new JLabel("주소");
		lblNewLabel_2.setPreferredSize(new Dimension(204, this.getPreferredSize().height));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		add(lblNewLabel_2);
	}
	
}
