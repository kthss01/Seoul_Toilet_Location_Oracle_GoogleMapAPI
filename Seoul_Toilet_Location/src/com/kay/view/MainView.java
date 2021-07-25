package com.kay.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kay.controller.MainController;

import com.kay.model.vo.GoogleMap;

import com.kay.common.GoogleMapTemplate;

import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ButtonGroup;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(700, 768));
		contentPane.add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel2.add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblGoogleMap = new JLabel("");
		lblGoogleMap.setPreferredSize(new Dimension(GoogleMapTemplate.Map().getSizeX(), GoogleMapTemplate.Map().getSizeY()));
		scrollPane.setViewportView(lblGoogleMap);
		
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("초기 맵 설정 스레드 시작");
//				
//				GoogleMapTemplate.Map().setChanged(true);
//				ImageIcon icon = MainController.setMap(GoogleMapTemplate.Map().getCenter());
//				lblGoogleMap.setIcon(icon);
//				
//				System.out.println("초기 맵 설정 완료");
//			}
//		});
//		
//		thread.start();

			
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(700, 50));
		panel2.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(null);
		
		JPanel panel_7 = new JPanel();
//		panel_7.setBackground(Color.RED);
		panel_7.setBounds(607, 0, 65, 40);
		panel_2.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_3 = new JButton("지형");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[2]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_7.add(btnNewButton_3, BorderLayout.CENTER);
		
		JPanel panel_7_1 = new JPanel();
//		panel_7_1.setBackground(Color.RED);
		panel_7_1.setBounds(535, 0, 65, 40);
		panel_2.add(panel_7_1);
		panel_7_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("위성");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[3]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_7_1.add(btnNewButton_2, BorderLayout.CENTER);
		
		JPanel panel_7_2 = new JPanel();
//		panel_7_2.setBackground(Color.RED);
		panel_7_2.setBounds(463, 0, 65, 40);
		panel_2.add(panel_7_2);
		panel_7_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("일반");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[0]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_7_2.add(btnNewButton_1, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setVgap(0);
//		panel_8.setBackground(Color.YELLOW);
		panel_8.setBounds(12, 0, 250, 40);
		panel_2.add(panel_8);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
		});
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		textField.setText("도로명 주소 : 서울 구 도로명 번호");
		textField.setPreferredSize(new Dimension(70, 40));
		panel_8.add(textField);
		textField.setColumns(20);
		
		JPanel panel_9 = new JPanel();
//		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(274, 0, 60, 40);
		panel_2.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(scrollPane.getVerticalScrollBar().getValue() + " " + scrollPane.getHorizontalScrollBar().getValue());
				
				String address = null;
				
				if (rdbtnNewRadioButton.isSelected()) {
					address = new MainController().searchAddressSeoulLocation(textField.getText());
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					address = new MainController().searchXYSeoulLocation(textField.getText());
				}
				
				GoogleMapTemplate.Map().setChanged(true);
//				ImageIcon icon = MainController.setMap(address);
				ImageIcon icon = MainController.findToiletMap(address);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_9.add(btnSearch, BorderLayout.CENTER);
		
		rdbtnNewRadioButton = new JRadioButton("도로명 주소");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("도로명 주소 : 서울 구 도로명 번호");
			}
		});
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(342, 0, 121, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("좌표");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("좌표 : 위도(Lat), 경도(Lng)");
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rdbtnNewRadioButton_1.setBounds(342, 19, 121, 23);
		panel_2.add(rdbtnNewRadioButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(75, 768));
		panel2.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
//		panel_4.setBackground(Color.ORANGE);
		panel_4.setBounds(10, 10, 53, 390);
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(7, 1, 0, 2));
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		panel_4.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("범례");
		lblNewLabel_7.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_18.add(lblNewLabel_7, BorderLayout.CENTER);
		
		JPanel panel_12 = new JPanel();
//		panel_12.setBackground(new Color(255, 0, 0));
		panel_12.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[0]));
		panel_4.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("화장실");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_12.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_13 = new JPanel();
//		panel_13.setBackground(new Color(50, 205, 50));
		panel_13.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[1]));
		panel_4.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("공원");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_13.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
//		panel_14.setBackground(new Color(255, 140, 0));
		panel_14.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[2]));
		panel_4.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("지하철");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_14.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JPanel panel_15 = new JPanel();
//		panel_15.setBackground(new Color(30, 144, 255));
		panel_15.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[3]));
		panel_4.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("공공시설");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_15.add(lblNewLabel_4, BorderLayout.CENTER);
		
		JPanel panel_16 = new JPanel();
//		panel_16.setBackground(new Color(165, 42, 42));
		panel_16.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[4]));
		panel_4.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("상가");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_16.add(lblNewLabel_5, BorderLayout.CENTER);
		
		JPanel panel_17 = new JPanel();
//		panel_17.setBackground(new Color(0, 0, 0));
		panel_17.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[5]));
		panel_4.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("기타");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_17.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
//		panel_5.setBackground(Color.CYAN);
		panel_5.setBounds(15, 415, 40, 40);
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_6 = new JButton("");
		panel_5.add(btnNewButton_6, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
//		panel_6.setBackground(Color.MAGENTA);
		panel_6.setBounds(10, 470, 53, 189);
		panel_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JSlider slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
		panel_6.add(slider, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(25, 25));
		panel_6.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_4 = new JButton("+");
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_10.add(btnNewButton_4, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(25, 25));
		panel_6.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_5 = new JButton("-");
		btnNewButton_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_11.add(btnNewButton_5, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(324, 768));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setPreferredSize(new Dimension(324, 368));
		panel.add(tabbedPane, BorderLayout.NORTH);
		
		JList list = new JList();
		tabbedPane.addTab("결과", null, list, null);
		
		JList list_1 = new JList();
		tabbedPane.addTab("전체", null, list_1, null);
		
		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setPreferredSize(new Dimension(324, 350));
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		panel_1.add(table, BorderLayout.CENTER);	
	}
}
