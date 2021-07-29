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
import java.awt.Image;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kay.common.GoogleMapTemplate;
import com.kay.common.LocationTemplate;
import com.kay.controller.MainController;
import com.kay.model.vo.GoogleMap;
import com.kay.model.vo.Toilet;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAddress;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnRoadAddress;
	private JRadioButton rdbtnXYAddress;
	private TotalToiletRenderer totalToiletRender;
	private DefaultListModel<Toilet> findToiletModel;
	private FindToiletRenderer findToiletRenderer;
	private JTable tableDetailResultContent;
	
	private String[][] toiletDetailTable = new String[][] {
		{"상세제목1", "상세내용1"},
		{"상세제목2", "상세내용2"},
		{"상세제목3", "상세내용3"},
		{"상세제목4", "상세내용4"},
		{"상세제목5", "상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5상세내용5"}
	};
	private String[] toiletDetailTableHeader = new String[] {"상세 제목", "상세 내용"};
	
	private String backgroundColor = "CED4DA";
	private String[] detailBgColor = {
			"CED4DA", "DEE2E6", "E9ECEF", "F8F9FA"
	};
	
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
	private JPanel panelMarkerColor;
	private JLabel lblGoogleMap;
	private JScrollPane scrollPane;

	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1024, 768);
		contentPane = new JPanel();
		
		contentPane.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSection = new JPanel();
		panelSection.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		panelSection.setPreferredSize(new Dimension(700, 768));
		contentPane.add(panelSection, BorderLayout.CENTER);
		panelSection.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelSection.add(scrollPane, BorderLayout.CENTER);
		
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

			
		JPanel panelNavigation = new JPanel();
		panelNavigation.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		panelNavigation.setPreferredSize(new Dimension(700, 50));
		panelSection.add(panelNavigation, BorderLayout.NORTH);
		panelNavigation.setLayout(null);
		
		JPanel panelMapTypeTerrain = new JPanel();
//		panel_7.setBackground(Color.RED);
		panelMapTypeTerrain.setBounds(607, 0, 65, 48);
		panelNavigation.add(panelMapTypeTerrain);
		panelMapTypeTerrain.setLayout(new BorderLayout(0, 0));
		
		JButton btnMapTypeTerrain = new JButton("");
		btnMapTypeTerrain.setIcon(new ImageIcon("./resources/images/terrain_maptype.png"));
		btnMapTypeTerrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[2]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnMapTypeTerrain.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelMapTypeTerrain.add(btnMapTypeTerrain, BorderLayout.CENTER);
		
		JPanel panelMapTypeSatellite = new JPanel();
//		panel_7_1.setBackground(Color.RED);
		panelMapTypeSatellite.setBounds(535, 0, 65, 48);
		panelNavigation.add(panelMapTypeSatellite);
		panelMapTypeSatellite.setLayout(new BorderLayout(0, 0));
		
		JButton btnMapTypeSatellite = new JButton("");
		btnMapTypeSatellite.setIcon(new ImageIcon("./resources/images/satellite_maptype.png"));
		btnMapTypeSatellite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[3]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnMapTypeSatellite.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelMapTypeSatellite.add(btnMapTypeSatellite, BorderLayout.CENTER);
		
		JPanel panelMapTypeRoadmap = new JPanel();
//		panel_7_2.setBackground(Color.RED);
		panelMapTypeRoadmap.setBounds(463, 0, 65, 48);
		panelNavigation.add(panelMapTypeRoadmap);
		panelMapTypeRoadmap.setLayout(new BorderLayout(0, 0));
		
		JButton btnMapTypeRoadmap = new JButton("");
		btnMapTypeRoadmap.setIcon(new ImageIcon("./resources/images/roadmap_maptype.png"));
		btnMapTypeRoadmap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateMapTypeMap(GoogleMap.getMaptypes()[0]);
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnMapTypeRoadmap.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelMapTypeRoadmap.add(btnMapTypeRoadmap, BorderLayout.CENTER);
		
		JPanel panelInputAddress = new JPanel();
		panelInputAddress.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		FlowLayout fl_panelInputAddress = (FlowLayout) panelInputAddress.getLayout();
		fl_panelInputAddress.setVgap(0);
//		panel_8.setBackground(Color.YELLOW);
		panelInputAddress.setBounds(12, 0, 250, 40);
		panelNavigation.add(panelInputAddress);
		
		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldAddress.setText("");
			}
		});
		textFieldAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		textFieldAddress.setText("도로명 주소 : 서울 구 도로명 번호");
		textFieldAddress.setPreferredSize(new Dimension(70, 40));
		panelInputAddress.add(textFieldAddress);
		textFieldAddress.setColumns(20);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(Color.WHITE);
//		panel_9.setBackground(Color.LIGHT_GRAY);
		panelSearch.setBounds(265, 0, 50, 40);
		panelNavigation.add(panelSearch);
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearch = new JButton("");
		btnSearch.setContentAreaFilled(false);
		
		btnSearch.setIcon(imageSetSize(
				new ImageIcon("./resources/images/outline_search_black_24dp.png"), 
				panelSearch.getWidth() - 15, panelSearch.getHeight() - 10));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(scrollPane.getVerticalScrollBar().getValue() + " " + scrollPane.getHorizontalScrollBar().getValue());
				
				String address = null;
				
				if (rdbtnRoadAddress.isSelected()) {
					address = new MainController().searchAddressSeoulLocation(textFieldAddress.getText());
				} else if (rdbtnXYAddress.isSelected()) {
					address = new MainController().searchXYSeoulLocation(textFieldAddress.getText());
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
		panelSearch.add(btnSearch, BorderLayout.CENTER);
		
		rdbtnRoadAddress = new JRadioButton("도로명 주소");
		rdbtnRoadAddress.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		rdbtnRoadAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAddress.setText("도로명 주소 : 서울 구 도로명 번호");
			}
		});
		rdbtnRoadAddress.setSelected(true);
		buttonGroup.add(rdbtnRoadAddress);
		rdbtnRoadAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rdbtnRoadAddress.setBounds(342, 0, 121, 23);
		panelNavigation.add(rdbtnRoadAddress);
		
		rdbtnXYAddress = new JRadioButton("좌표");
		rdbtnXYAddress.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		rdbtnXYAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAddress.setText("좌표 : 위도(Lat), 경도(Lng)");
			}
		});
		buttonGroup.add(rdbtnXYAddress);
		rdbtnXYAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rdbtnXYAddress.setBounds(342, 19, 121, 23);
		panelNavigation.add(rdbtnXYAddress);
		
		JPanel panelAside = new JPanel();
		panelAside.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		panelAside.setPreferredSize(new Dimension(75, 768));
		panelSection.add(panelAside, BorderLayout.EAST);
		panelAside.setLayout(null);
		
		JPanel panelLegend = new JPanel();
//		panel_4.setBackground(Color.ORANGE);
		panelLegend.setBounds(12, 10, 53, 390);
		panelAside.add(panelLegend);
		panelLegend.setLayout(new GridLayout(7, 1, 0, 2));
		
		JPanel panelLegendLegend = new JPanel();
		panelLegendLegend.setBackground(Color.WHITE);
		panelLegend.add(panelLegendLegend);
		panelLegendLegend.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendLegend = new JLabel("범례");
		lblLegendLegend.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblLegendLegend.setHorizontalAlignment(SwingConstants.CENTER);
		panelLegendLegend.add(lblLegendLegend, BorderLayout.CENTER);
		
		JPanel panelLegendToilet = new JPanel();
//		panel_12.setBackground(new Color(255, 0, 0));
		panelLegendToilet.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[0]));
		panelLegend.add(panelLegendToilet);
		panelLegendToilet.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendToilet = new JLabel("화장실");
		lblLegendToilet.setForeground(new Color(255, 255, 255));
		lblLegendToilet.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendToilet.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendToilet.add(lblLegendToilet, BorderLayout.CENTER);
		
		JPanel panelLegendPark = new JPanel();
//		panel_13.setBackground(new Color(50, 205, 50));
		panelLegendPark.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[1]));
		panelLegend.add(panelLegendPark);
		panelLegendPark.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendPark = new JLabel("공원");
		lblLegendPark.setForeground(new Color(255, 255, 255));
		lblLegendPark.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendPark.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendPark.add(lblLegendPark, BorderLayout.CENTER);
		
		JPanel panelLegendSubway = new JPanel();
//		panel_14.setBackground(new Color(255, 140, 0));
		panelLegendSubway.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[2]));
		panelLegend.add(panelLegendSubway);
		panelLegendSubway.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendSubway = new JLabel("지하철");
		lblLegendSubway.setForeground(new Color(255, 255, 255));
		lblLegendSubway.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendSubway.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendSubway.add(lblLegendSubway, BorderLayout.CENTER);
		
		JPanel panelLegendPublic = new JPanel();
//		panel_15.setBackground(new Color(30, 144, 255));
		panelLegendPublic.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[3]));
		panelLegend.add(panelLegendPublic);
		panelLegendPublic.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendPublic = new JLabel("공공시설");
		lblLegendPublic.setForeground(new Color(255, 255, 255));
		lblLegendPublic.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendPublic.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendPublic.add(lblLegendPublic, BorderLayout.CENTER);
		
		JPanel panelLegendStore = new JPanel();
//		panel_16.setBackground(new Color(165, 42, 42));
		panelLegendStore.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[4]));
		panelLegend.add(panelLegendStore);
		panelLegendStore.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendStore = new JLabel("상가");
		lblLegendStore.setForeground(new Color(255, 255, 255));
		lblLegendStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendStore.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendStore.add(lblLegendStore, BorderLayout.CENTER);
		
		JPanel panelLegendETC = new JPanel();
//		panel_17.setBackground(new Color(0, 0, 0));
		panelLegendETC.setBackground(GoogleMapTemplate.HexToColor(GoogleMap.getLegendColor()[5]));
		panelLegend.add(panelLegendETC);
		panelLegendETC.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLegendETC = new JLabel("기타");
		lblLegendETC.setForeground(new Color(255, 255, 255));
		lblLegendETC.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendETC.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelLegendETC.add(lblLegendETC, BorderLayout.CENTER);
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(Color.WHITE);
//		panel_5.setBackground(Color.CYAN);
		panelHome.setBounds(17, 415, 40, 40);
		panelAside.add(panelHome);
		panelHome.setLayout(new BorderLayout(0, 0));
		
		JButton btnHome = new JButton("");
		btnHome.setContentAreaFilled(false);
		
		btnHome.setIcon(imageSetSize(
				new ImageIcon("./resources/images/outline_home_black_24dp.png"), 
				panelHome.getWidth() - 5, panelHome.getHeight() - 5));
		
		btnHome.addActionListener(new ActionListener() {
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
		panelHome.add(btnHome, BorderLayout.CENTER);
		
		JPanel panelZoomLevel = new JPanel();
//		panel_6.setBackground(Color.MAGENTA);
		panelZoomLevel.setBounds(17, 470, 40, 189);
		panelAside.add(panelZoomLevel);
		panelZoomLevel.setLayout(new BorderLayout(0, 0));
		
		JSlider sliderZoomLevel = new JSlider();
		sliderZoomLevel.setBackground(Color.WHITE);
		sliderZoomLevel.setForeground(Color.BLACK);
		sliderZoomLevel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(sliderZoomLevel.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		sliderZoomLevel.setPaintTicks(true);
		sliderZoomLevel.setMinorTickSpacing(1);
		sliderZoomLevel.setMajorTickSpacing(5);
		sliderZoomLevel.setMaximum(20);
		sliderZoomLevel.setMinimum(1);
		sliderZoomLevel.setValue(GoogleMapTemplate.Map().getZoom());
		sliderZoomLevel.setOrientation(SwingConstants.VERTICAL);
		
		panelZoomLevel.add(sliderZoomLevel, BorderLayout.CENTER);
		
		JPanel panelPlusZoom = new JPanel();
		panelPlusZoom.setBackground(Color.WHITE);
		panelPlusZoom.setPreferredSize(new Dimension(30, 30));
		panelZoomLevel.add(panelPlusZoom, BorderLayout.NORTH);
		panelPlusZoom.setLayout(new BorderLayout(0, 0));
		
		JButton btnPlusZoom = new JButton("");
		btnPlusZoom.setContentAreaFilled(false);
		
		btnPlusZoom.setIcon(imageSetSize(
				new ImageIcon("./resources/images/outline_add_black_24dp.png"), 
				panelPlusZoom.getPreferredSize().width - 5, panelPlusZoom.getPreferredSize().height - 5));
		
		btnPlusZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sliderZoomLevel.setValue(sliderZoomLevel.getValue() + 1);

				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(sliderZoomLevel.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnPlusZoom.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelPlusZoom.add(btnPlusZoom, BorderLayout.CENTER);
		
		JPanel panelMinusZoom = new JPanel();
		panelMinusZoom.setBackground(Color.WHITE);
		panelMinusZoom.setPreferredSize(new Dimension(30, 30));
		panelZoomLevel.add(panelMinusZoom, BorderLayout.SOUTH);
		panelMinusZoom.setLayout(new BorderLayout(0, 0));
		
		JButton btnMinusZoom = new JButton("");
		btnMinusZoom.setContentAreaFilled(false);
		
		btnMinusZoom.setIcon(imageSetSize(
				new ImageIcon("./resources/images/outline_remove_black_24dp.png"), 
				panelMinusZoom.getPreferredSize().width - 5, panelMinusZoom.getPreferredSize().height - 5));
		
		btnMinusZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sliderZoomLevel.setValue(sliderZoomLevel.getValue() - 1);

				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.updateZoomLevelMap(sliderZoomLevel.getValue());
				lblGoogleMap.setIcon(icon);
				
//				System.out.println(scrollPane.getVerticalScrollBar().getMaximum() + " " + scrollPane.getHorizontalScrollBar().getMaximum());
				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum() / 2 - 400);
				scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum() / 2 - 300);
			}
		});
		btnMinusZoom.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelMinusZoom.add(btnMinusZoom, BorderLayout.CENTER);
		
		JPanel panelInfoAside = new JPanel();
		panelInfoAside.setBackground(GoogleMapTemplate.HexToColor(backgroundColor));
		panelInfoAside.setPreferredSize(new Dimension(324, 768));
		contentPane.add(panelInfoAside, BorderLayout.WEST);
		panelInfoAside.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPaneResult = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneResult.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPaneResult.setBackground(Color.WHITE);
		tabbedPaneResult.setPreferredSize(new Dimension(324, 368));
		panelInfoAside.add(tabbedPaneResult, BorderLayout.NORTH);
		
		JScrollPane scrollPaneSearchResult = new JScrollPane();
		tabbedPaneResult.addTab("검색 결과", null, scrollPaneSearchResult, null);
		
		JList<Toilet> listSearchResult = new JList<Toilet>();
		listSearchResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listSearchResult.getSelectedIndex() != -1) {
					updateToiletInfo(listSearchResult.getSelectedValue());
				}
			}
		});
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		findToiletModel = new DefaultListModel<>();
		
		findToiletRenderer = new FindToiletRenderer();
		
		listSearchResult.setModel(findToiletModel);
		listSearchResult.setCellRenderer(findToiletRenderer);
		
		scrollPaneSearchResult.setViewportView(listSearchResult);
		
		JScrollPane scrollPaneSearchTotalResult = new JScrollPane();
		tabbedPaneResult.addTab("전체 결과", null, scrollPaneSearchTotalResult, null);
		
		JList<Toilet> listSearchTotalResult = new JList<Toilet>();
		listSearchTotalResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listSearchTotalResult.getSelectedIndex() != -1) {
					
					// distance 설정만 따로 해줌
					double lon1 = Double.parseDouble(listSearchTotalResult.getSelectedValue().getLocY());
					double lat1 = Double.parseDouble(listSearchTotalResult.getSelectedValue().getLocX());
					
					String[] center = GoogleMapTemplate.Map().getCenter().split(",");
					double lon2 = Double.parseDouble(center[0]); 
					double lat2 = Double.parseDouble(center[1]); 
					
					double distance = LocationTemplate.distance(lon1, lat1, lon2, lat2, "meter");
					
//					이상하게 두번 출력됨 왜그런지 모르겠음 이거 처리는 나중에 생각하자
//					System.out.printf("%f %f %f %f = %f\n", lon1, lat1, lon2, lat2, distance);
			
					listSearchTotalResult.getSelectedValue().setDistance((float) distance);
					
					updateToiletInfo(listSearchTotalResult.getSelectedValue());
					
					lblDistance.setText(String.format("%dm", (int)(distance)));
					
					// 뒤 바뀌어있음
					lblLocX.setText(String.format("%.6f", Double.parseDouble(listSearchTotalResult.getSelectedValue().getLocY())));
					lblLocY.setText(String.format("%.6f", Double.parseDouble(listSearchTotalResult.getSelectedValue().getLocX())));
				}
			}
		});
		
		listSearchTotalResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<Toilet> totalToiletModel = new DefaultListModel<>();
		
		Collection<Toilet> totalToilet = LocationTemplate.getToilet().values();
		for (Toilet toilet : totalToilet) {
			totalToiletModel.addElement(toilet);
		}
		
		totalToiletRender = new TotalToiletRenderer();
		
		listSearchTotalResult.setModel(totalToiletModel);
		listSearchTotalResult.setCellRenderer(totalToiletRender);
		scrollPaneSearchTotalResult.setViewportView(listSearchTotalResult);
		
		
		JPanel panelResultContent = new JPanel();
//		panel_1.setBackground(Color.LIGHT_GRAY);
		panelResultContent.setPreferredSize(new Dimension(324, 350));
		panelInfoAside.add(panelResultContent, BorderLayout.SOUTH);
		panelResultContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBasicResultContent = new JPanel();
		panelBasicResultContent.setPreferredSize(new Dimension(324, 150));
		panelResultContent.add(panelBasicResultContent, BorderLayout.NORTH);
		panelBasicResultContent.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelResultContent1 = new JPanel();
		panelBasicResultContent.add(panelResultContent1);
		panelResultContent1.setLayout(new BorderLayout(0, 0));
		
		JPanel panelGuName = new JPanel();
		panelGuName.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[1]));
		panelGuName.setPreferredSize(new Dimension(60, 50));
		panelGuName.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent1.add(panelGuName, BorderLayout.WEST);
		panelGuName.setLayout(new BorderLayout(0, 0));
		
		lblGuName = new JLabel("GU_NAME");
		lblGuName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblGuName.setHorizontalAlignment(SwingConstants.CENTER);
		panelGuName.add(lblGuName, BorderLayout.CENTER);
		
		JPanel panelLocationName = new JPanel();
		panelLocationName.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[0]));
		panelLocationName.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent1.add(panelLocationName, BorderLayout.CENTER);
		panelLocationName.setLayout(new BorderLayout(0, 0));
		
		lblLocationName = new JLabel("LOCATION_NAME");
		lblLocationName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblLocationName.setHorizontalAlignment(SwingConstants.CENTER);
		panelLocationName.add(lblLocationName, BorderLayout.CENTER);
		
		JPanel panelResultContent2 = new JPanel();
		panelBasicResultContent.add(panelResultContent2);
		panelResultContent2.setLayout(new BorderLayout(0, 0));
		
		JPanel panelRoadAddress = new JPanel();
		panelRoadAddress.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[1]));
		panelRoadAddress.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent2.add(panelRoadAddress, BorderLayout.CENTER);
		panelRoadAddress.setLayout(new BorderLayout(0, 0));
		
		lblRoadAddress = new JLabel("ROAD_ADDRESS");
		lblRoadAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoadAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelRoadAddress.add(lblRoadAddress, BorderLayout.CENTER);
		
		JPanel panelDistance = new JPanel();
		panelDistance.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[2]));
		panelDistance.setPreferredSize(new Dimension(80, 50));
		panelDistance.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent2.add(panelDistance, BorderLayout.WEST);
		panelDistance.setLayout(new BorderLayout(0, 0));
		
		lblDistance = new JLabel("DISTACNE");
		lblDistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistance.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelDistance.add(lblDistance, BorderLayout.CENTER);
		
		JPanel panelResultContent3 = new JPanel();
		panelBasicResultContent.add(panelResultContent3);
		panelResultContent3.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNumAddress = new JPanel();
		panelNumAddress.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[2]));
		panelNumAddress.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent3.add(panelNumAddress, BorderLayout.CENTER);
		panelNumAddress.setLayout(new BorderLayout(0, 0));
		
		lblNumAddress = new JLabel("NUM_ADDRESS");
		lblNumAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelNumAddress.add(lblNumAddress, BorderLayout.CENTER);
		
		JPanel panelPhone = new JPanel();
		panelPhone.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[3]));
		panelPhone.setPreferredSize(new Dimension(100, 50));
		panelPhone.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent3.add(panelPhone, BorderLayout.WEST);
		panelPhone.setLayout(new BorderLayout(0, 0));
		
		lblPhone = new JLabel("PHONE");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelPhone.add(lblPhone, BorderLayout.CENTER);
		
		JPanel panelResultContent4 = new JPanel();
		panelBasicResultContent.add(panelResultContent4);
		panelResultContent4.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUsingTime = new JPanel();
		panelUsingTime.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[3]));
		panelUsingTime.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelUsingTime.setPreferredSize(new Dimension(50, 50));
		panelResultContent4.add(panelUsingTime, BorderLayout.EAST);
		panelUsingTime.setLayout(new BorderLayout(0, 0));
		
		lblUsingTime = new JLabel("USING_TIME");
		lblUsingTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsingTime.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelUsingTime.add(lblUsingTime, BorderLayout.CENTER);
		
		JPanel panelLocXY = new JPanel();
		panelLocXY.setBackground(Color.WHITE);
		panelResultContent4.add(panelLocXY, BorderLayout.CENTER);
		panelLocXY.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLocX = new JPanel();
		panelLocX.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[3]));
		panelLocX.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLocXY.add(panelLocX);
		panelLocX.setLayout(new BorderLayout(0, 0));
		
		lblLocX = new JLabel("LOC_X");
		lblLocX.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocX.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelLocX.add(lblLocX, BorderLayout.CENTER);
		
		JPanel panelLocY = new JPanel();
		panelLocY.setBackground(GoogleMapTemplate.HexToColor(detailBgColor[3]));
		panelLocY.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLocXY.add(panelLocY);
		panelLocY.setLayout(new BorderLayout(0, 0));
		
		lblLocY = new JLabel("LOC_Y");
		lblLocY.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocY.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panelLocY.add(lblLocY, BorderLayout.CENTER);
		
		panelMarkerColor = new JPanel();
		panelMarkerColor.setBackground(Color.WHITE);
		panelMarkerColor.setPreferredSize(new Dimension(40, 50));
		panelMarkerColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResultContent4.add(panelMarkerColor, BorderLayout.WEST);
		panelMarkerColor.setLayout(new BorderLayout(0, 0));
		
		lblMarkerLabel = new JLabel("Label");
		lblMarkerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarkerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panelMarkerColor.add(lblMarkerLabel, BorderLayout.CENTER);

		
		JScrollPane scrollPaneDetailResultContent = new JScrollPane();
		scrollPaneDetailResultContent.setPreferredSize(new Dimension(324, 200));
		panelResultContent.add(scrollPaneDetailResultContent, BorderLayout.SOUTH);
		
		tableDetailResultContent = new JTable();
		tableDetailResultContent.setFillsViewportHeight(true);
		
		tableDetailResultContent.setModel(new DefaultTableModel(toiletDetailTable, toiletDetailTableHeader));
		
		tableDetailResultContent.setRowSelectionAllowed(false);
		tableDetailResultContent.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		tableDetailResultContent.setDefaultRenderer(Object.class, new ToiletDetailTableRenderer());
		
//		table.getColumn("상세 제목").setPreferredWidth(100);
		tableDetailResultContent.getColumn("상세 내용").setPreferredWidth(200);
//		table.getColumn("상세 내용").setCellRenderer(new ToiletDetailTableRenderer());
		
		tableDetailResultContent.setRowHeight(50);
		
		setTableColumnAlignCenter(tableDetailResultContent);
		
		scrollPaneDetailResultContent.setViewportView(tableDetailResultContent);
	}
	
	private ImageIcon imageSetSize(ImageIcon icon, int w, int h) {
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(w, h,Image.SCALE_SMOOTH);
		return new ImageIcon(newImage);
	}
	
	private void updateToiletInfo(Toilet toilet) {
		
		lblMarkerLabel.setText(toilet.getMarkerLabel());
		lblMarkerLabel.setForeground(Color.WHITE);
		panelMarkerColor.setBackground(GoogleMapTemplate.HexToColor(toilet.getColor()));
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
		
		tableDetailResultContent.setModel(new DefaultTableModel(toiletDetailTable, toiletDetailTableHeader));
		setTableColumnAlignCenter(tableDetailResultContent);
		tableDetailResultContent.getColumn("상세 내용").setPreferredWidth(200);
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
			
			this.panelListMarkLabel.setBackground(GoogleMapTemplate.HexToColor(toilet.getColor()));
			
			this.lblListMarkLabel.setText(value.getMarkerLabel());
			this.lblListDistance.setText(String.format("%dm", (int)(value.getDistance() * 1000)));
			this.lblListAddress.setText(value.getLocationName());
			
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
