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

	protected JLabel lblListMarkLabel;
	protected JLabel lblListDistance;
	protected JLabel lblListAddress;
	protected JPanel panelListMarkLabel;

	/**
	 * Create the panel.
	 */
	public ToiletPanel() {
		setBackground(new Color(230, 230, 250));
		setPreferredSize(new Dimension(300, 30));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		panelListMarkLabel = new JPanel();
		panelListMarkLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelListMarkLabel);
		panelListMarkLabel.setLayout(new BorderLayout(0, 0));
		
		lblListMarkLabel = new JLabel("라벨");
		panelListMarkLabel.add(lblListMarkLabel);
		lblListMarkLabel.setPreferredSize(new Dimension(40, 28));
		lblListMarkLabel.setForeground(Color.WHITE);
		lblListMarkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblListMarkLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		JPanel panelListDistance = new JPanel();
		panelListDistance.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListDistance.setBackground(new Color(255, 240, 245));
		add(panelListDistance);
		panelListDistance.setLayout(new BorderLayout(0, 0));
		
		lblListDistance = new JLabel("거리");
		panelListDistance.add(lblListDistance);
		lblListDistance.setPreferredSize(new Dimension(56, 28));
		lblListDistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblListDistance.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		lblListAddress = new JLabel("주소");
		lblListAddress.setPreferredSize(new Dimension(200, 30));
		lblListAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblListAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		add(lblListAddress);
	}
	
}
