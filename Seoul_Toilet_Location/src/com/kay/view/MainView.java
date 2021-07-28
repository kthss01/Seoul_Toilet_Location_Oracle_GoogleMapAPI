package com.kay.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.kay.common.GoogleMapTemplate;
import com.kay.common.LocationTemplate;
import com.kay.controller.MainController;
import com.kay.model.vo.GoogleMap;
import com.kay.model.vo.Toilet;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private TotalToiletRenderer totalToiletRender;
	private DefaultListModel<Toilet> findToiletModel;
	private FindToiletRenderer findToiletRenderer;
	private JTable table;
	
	private String[][] toiletDetailTable = new String[][] {
		{"상세제목1", "상세내용1"},
		{"상세제목2", "상세내용2"},
		{"상세제목3", "상세내용3"},
		{"상세제목4", "상세내용4"},
		{"상세제목5", "상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5"}
	};
	private String[] toiletDetailTableHeader = new String[] {"상세 제목", "상세 내용"};
	
	private JLabel lblGuName;
	private JLabel lblLocationName;
	private JLabel lblRoadAddress;
	private JLabel lblDistance;
	private JLabel lblNumAddress;
	private JLabel lblPhone;
	private JLabel lblUsingTime;
	private JLabel lblLocX;
	private JLabel lblLocY;
	private JLabel lblMarkerLabel;
	private JPanel panel_MarkerColor;
	private JLabel lblGoogleMap;
	private JScrollPane scrollPane;

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
		
		scrollPane = new JScrollPane();
		panel2.add(scrollPane, BorderLayout.CENTER);
		
		lblGoogleMap = new JLabel("");
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
				
				GoogleMapTemplate.CloneMap();
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
				
				updateFindToiletList();
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
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GoogleMapTemplate.isCloneMap()) {
					
					GoogleMapTemplate.RestoreMap();
					
					GoogleMapTemplate.Map().setChanged(true);
					ImageIcon icon = MainController.downloadGoogleMap();
					lblGoogleMap.setIcon(icon);
					
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
					
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
					scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
				}
			}
		});
		panel_5.add(btnNewButton_6, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
//		panel_6.setBackground(Color.MAGENTA);
		panel_6.setBounds(10, 470, 53, 189);
		panel_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JSlider slider = new JSlider();
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(slider.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setMaximum(20);
		slider.setMinimum(1);
		slider.setValue(GoogleMapTemplate.Map().getZoom());
		slider.setOrientation(SwingConstants.VERTICAL);
		
		panel_6.add(slider, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(25, 25));
		panel_6.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_4 = new JButton("+");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.setValue(slider.getValue() + 1);

				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(slider.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_10.add(btnNewButton_4, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(25, 25));
		panel_6.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_5 = new JButton("-");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.setValue(slider.getValue() - 1);

				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(slider.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnNewButton_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_11.add(btnNewButton_5, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(324, 768));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setPreferredSize(new Dimension(324, 368));
		panel.add(tabbedPane, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("검색 결과", null, scrollPane_1, null);
		
		JList<Toilet> list = new JList<Toilet>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1) {
					updateToiletInfo(list.getSelectedValue());
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		findToiletModel = new DefaultListModel<>();
		
		findToiletRenderer = new FindToiletRenderer();
		
		list.setModel(findToiletModel);
		list.setCellRenderer(findToiletRenderer);
		
		scrollPane_1.setViewportView(list);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("전체 결과", null, scrollPane_2, null);
		
		JList<Toilet> list_1 = new JList<Toilet>();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list_1.getSelectedIndex() != -1) {
					
					// distance 설정만 따로 해줌
					double lon1 = Double.parseDouble(list_1.getSelectedValue().getLocY());
					double lat1 = Double.parseDouble(list_1.getSelectedValue().getLocX());
					
					String[] center = GoogleMapTemplate.Map().getCenter().split(",");
					double lon2 = Double.parseDouble(center[0]); 
					double lat2 = Double.parseDouble(center[1]); 
					
					double distance = LocationTemplate.distance(lon1, lat1, lon2, lat2, "meter");
					
//					이상하게 두번 출력됨 왜그런지 모르겠음 이거 처리는 나중에 생각하자
//					System.out.printf("%f %f %f %f = %f\n", lon1, lat1, lon2, lat2, distance);
			
					list_1.getSelectedValue().setDistance((float) distance);
					
					updateToiletInfo(list_1.getSelectedValue());
					
					lblDistance.setText(String.format("%dm", (int)(distance)));
					
					// 뒤 바뀌어있음
					lblLocX.setText(String.format("%.6f", Double.parseDouble(list_1.getSelectedValue().getLocY())));
					lblLocY.setText(String.format("%.6f", Double.parseDouble(list_1.getSelectedValue().getLocX())));
				}
			}
		});
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<Toilet> totalToiletModel = new DefaultListModel<>();
		
		Collection<Toilet> totalToilet = LocationTemplate.getToilet().values();
		for (Toilet toilet : totalToilet) {
			totalToiletModel.addElement(toilet);
		}
		
		totalToiletRender = new TotalToiletRenderer();
		
		list_1.setModel(totalToiletModel);
		list_1.setCellRenderer(totalToiletRender);
		scrollPane_2.setViewportView(list_1);
		
		
		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setPreferredSize(new Dimension(324, 350));
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_19 = new JPanel();
		panel_19.setPreferredSize(new Dimension(324, 150));
		panel_1.add(panel_19, BorderLayout.NORTH);
		panel_19.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_20 = new JPanel();
		panel_19.add(panel_20);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_21.setPreferredSize(new Dimension(60, 50));
		panel_21.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_20.add(panel_21, BorderLayout.WEST);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		lblGuName = new JLabel("GU_NAME");
		lblGuName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblGuName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_21.add(lblGuName, BorderLayout.CENTER);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_20.add(panel_23, BorderLayout.CENTER);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		lblLocationName = new JLabel("LOCATION_NAME");
		lblLocationName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblLocationName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_23.add(lblLocationName, BorderLayout.CENTER);
		
		JPanel panel_22 = new JPanel();
		panel_19.add(panel_22);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_24 = new JPanel();
		panel_24.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_22.add(panel_24, BorderLayout.CENTER);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		lblRoadAddress = new JLabel("ROAD_ADDRESS");
		lblRoadAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoadAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_24.add(lblRoadAddress, BorderLayout.CENTER);
		
		JPanel panel_25 = new JPanel();
		panel_25.setPreferredSize(new Dimension(80, 50));
		panel_25.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_22.add(panel_25, BorderLayout.WEST);
		panel_25.setLayout(new BorderLayout(0, 0));
		
		lblDistance = new JLabel("DISTACNE");
		lblDistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistance.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_25.add(lblDistance, BorderLayout.CENTER);
		
		JPanel panel_26 = new JPanel();
		panel_19.add(panel_26);
		panel_26.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_28.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_26.add(panel_28, BorderLayout.CENTER);
		panel_28.setLayout(new BorderLayout(0, 0));
		
		lblNumAddress = new JLabel("NUM_ADDRESS");
		lblNumAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_28.add(lblNumAddress, BorderLayout.CENTER);
		
		JPanel panel_29 = new JPanel();
		panel_29.setPreferredSize(new Dimension(100, 50));
		panel_29.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_26.add(panel_29, BorderLayout.WEST);
		panel_29.setLayout(new BorderLayout(0, 0));
		
		lblPhone = new JLabel("PHONE");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_29.add(lblPhone, BorderLayout.CENTER);
		
		JPanel panel_27 = new JPanel();
		panel_19.add(panel_27);
		panel_27.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_32.setPreferredSize(new Dimension(50, 50));
		panel_27.add(panel_32, BorderLayout.EAST);
		panel_32.setLayout(new BorderLayout(0, 0));
		
		lblUsingTime = new JLabel("USING_TIME");
		lblUsingTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsingTime.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_32.add(lblUsingTime, BorderLayout.CENTER);
		
		JPanel panel_33 = new JPanel();
		panel_27.add(panel_33, BorderLayout.CENTER);
		panel_33.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_30 = new JPanel();
		panel_30.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_33.add(panel_30);
		panel_30.setLayout(new BorderLayout(0, 0));
		
		lblLocX = new JLabel("LOC_X");
		lblLocX.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocX.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_30.add(lblLocX, BorderLayout.CENTER);
		
		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_33.add(panel_31);
		panel_31.setLayout(new BorderLayout(0, 0));
		
		lblLocY = new JLabel("LOC_Y");
		lblLocY.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocY.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_31.add(lblLocY, BorderLayout.CENTER);
		
		panel_MarkerColor = new JPanel();
		panel_MarkerColor.setPreferredSize(new Dimension(40, 50));
		panel_MarkerColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_27.add(panel_MarkerColor, BorderLayout.WEST);
		panel_MarkerColor.setLayout(new BorderLayout(0, 0));
		
		lblMarkerLabel = new JLabel("Label");
		lblMarkerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarkerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_MarkerColor.add(lblMarkerLabel, BorderLayout.CENTER);

		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setPreferredSize(new Dimension(324, 200));
		panel_1.add(scrollPane_3, BorderLayout.SOUTH);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		table.setModel(new DefaultTableModel(toiletDetailTable, toiletDetailTableHeader));
		
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		table.setDefaultRenderer(Object.class, new ToiletDetailTableRenderer());
		
//		table.getColumn("상세 제목").setPreferredWidth(100);
		table.getColumn("상세 내용").setPreferredWidth(200);
//		table.getColumn("상세 내용").setCellRenderer(new ToiletDetailTableRenderer());
		
		table.setRowHeight(50);
		
		setTableColumnAlignCenter(table);
		
		scrollPane_3.setViewportView(table);
	}
	
	private void updateToiletInfo(Toilet toilet) {
		
		lblMarkerLabel.setText(toilet.getMarkerLabel());
		lblMarkerLabel.setForeground(Color.WHITE);
		panel_MarkerColor.setBackground(GoogleMapTemplate.HexToColor(toilet.getColor()));
		lblDistance.setText(String.format("%dm", (int)(toilet.getDistance() * 1000)));
		
		lblLocationName.setText(toilet.getLocationName());
		lblGuName.setText(toilet.getGuName());
		lblUsingTime.setText(toilet.getUsingTime());
		lblRoadAddress.setText(toilet.getRoadAddress());
		lblNumAddress.setText(toilet.getNumAddress());
		lblPhone.setText(toilet.getPhone());
		lblLocX.setText(String.format("%.6f", Double.parseDouble(toilet.getLocX())));
		lblLocY.setText(String.format("%.6f", Double.parseDouble(toilet.getLocY())));
		
		updateToiletDetailTable(toilet);
		
		GoogleMapTemplate.Map().setChanged(true);
		ImageIcon icon = MainController.setToiletMap(toilet);
		lblGoogleMap.setIcon(icon);
		
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
		scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
	}
	
	private void updateFindToiletList() {
		
		findToiletModel.clear();
		
		List<Toilet> list = LocationTemplate.getNearToilet();
		
		for (Toilet toilet : list) {
			findToiletModel.addElement(toilet);
		}
	}
	
	private void updateToiletDetailTable(Toilet toilet) {
		toiletDetailTable[0][0] = toilet.getDetailName1() == null ? "" : toilet.getDetailName1();
		toiletDetailTable[1][0] = toilet.getDetailName2() == null ? "" : toilet.getDetailName2();
		toiletDetailTable[2][0] = toilet.getDetailName3() == null ? "" : toilet.getDetailName3();
		toiletDetailTable[3][0] = toilet.getDetailName4() == null ? "" : toilet.getDetailName4();
		toiletDetailTable[4][0] = toilet.getDetailName5() == null ? "" : toilet.getDetailName5();
		
		toiletDetailTable[0][1] = toilet.getDatailContent1() == null ? "" : toilet.getDatailContent1();
		toiletDetailTable[1][1] = toilet.getDatailContent2() == null ? "" : toilet.getDatailContent2();
		toiletDetailTable[2][1] = toilet.getDatailContent3() == null ? "" : toilet.getDatailContent3();
		toiletDetailTable[3][1] = toilet.getDatailContent4() == null ? "" : toilet.getDatailContent4();
		toiletDetailTable[4][1] = toilet.getDatailContent5() == null ? "" : toilet.getDatailContent5();
		
		table.setModel(new DefaultTableModel(toiletDetailTable, toiletDetailTableHeader));
		setTableColumnAlignCenter(table);
		table.getColumn("상세 내용").setPreferredWidth(200);
	}
	
	// table column 가운데 정렬 시켜주는 메소드
	private void setTableColumnAlignCenter(JTable table) {
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);		
	}
	
//	private class ToiletTableModel extends DefaultTableModel {
//		
//		Class[] columnTypes = new Class[] { String.class, String.class };
//
//		private ToiletTableModel(Object[][] data, Object[] columnNames) {
//			super(data, columnNames);
//		}
//
//		public Class GetColumnClass(int columnIndex) {
//			return columnTypes[columnIndex];
//		}
//	}
	
	private class ToiletDetailTableRenderer extends DefaultTableCellRenderer {

		public ToiletDetailTableRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			JTextArea textArea = new JTextArea(value.toString());
			
			textArea.setLineWrap(true);
			textArea.setFont(table.getFont());
			textArea.setBorder(new EmptyBorder(5, 5, 0, 0));

			c = textArea;
			
			return c;
		}
		
	}

//	private class ToiletDetailTableRenderer extends JTextArea implements TableCellRenderer {
//		
//		public ToiletDetailTableRenderer() {
//			setLineWrap(true);
//			setWrapStyleWord(true);
//			setOpaque(true);
//			setEditable(false);
////			setPreferredSize(new Dimension(300, 100));
//			
//		}
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//				int row, int column) {
//			
//			setText(value.toString());
////			setText(String.format("%d : %d", row, column));
//			setFont(table.getFont());
//			
////			setBackground(Color.CYAN);
//			
//			setEditable(false);
//			
//			return this;
//		}
//
//	}
	
	private class FindToiletRenderer extends ToiletPanel implements ListCellRenderer<Toilet> {

		Toilet toilet = null;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Toilet> list, Toilet value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			toilet = value;
			
			panel_1.setBackground(GoogleMapTemplate.HexToColor(toilet.getColor()));
			
			lblNewLabel.setText(value.getMarkerLabel());
			lblNewLabel_1.setText(String.format("%dm", (int)(value.getDistance() * 1000)));
			lblNewLabel_2.setText(value.getLocationName());
			
			return this;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, getWidth(), getHeight());
					
//			super.paintComponent(g);
		}
		
	}
	
	private class TotalToiletRenderer extends JLabel implements ListCellRenderer<Toilet> {

		Toilet toilet = null;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Toilet> list, Toilet value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			toilet = value;
			
			setText(value.getLocationName());
//			setBackground(GoogleMapTemplate.HexToColor(toilet.getColor()));
//			setForeground(GoogleMapTemplate.HexToColor(toilet.getColor()));
//			setForeground(Color.WHITE);
			setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//			setHorizontalAlignment(SwingConstants.CENTER);
			
			setBorder(new EmptyBorder(0, 35, 0, 0));
			
			return this;
		}

		@Override
		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
			
			g.setColor(GoogleMapTemplate.HexToColor(toilet.getColor()));
			g.fillRect(0, 0, 30, getHeight());
			
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, getWidth(), getHeight());
					
			super.paintComponent(g);
		}
		
	}
}
