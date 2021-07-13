package practice.advance.mvc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import practice.advance.mvc.common.GoogleMapTemplate;
import practice.advance.mvc.controller.MainController;
import practice.advance.mvc.model.vo.GoogleMap;
import practice.advance.mvc.model.vo.Marker;

public class OptionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldLocation;
	private JTextField textFieldSizeX;
	private JTextField textFieldSizeY;

	private JSpinner spinnerZoomLevel;
	private SpinnerNumberModel spinModel;

	private JComboBox<String> comboBoxMaptype;

	private JList<String> listMarker;

	private JComboBox<String> comboBoxMarkerSize;
	private JComboBox<String> comboBoxMarkerColor;
	private JComboBox<String> comboBoxMarkerLabel;

	private JCheckBox chckbxShowAllMarkers;

	private JTextField textFieldMarkerLocation;
	private JTextField textFieldMarkerColor;

	private JTextArea textAreaMarker;

	private JButton btnMarkerDelete;
	private JButton btnMarkerAdd;
	private JButton btnMarekrsClear;
	private DefaultComboBoxModel<String> maptypeModel;
	private DefaultComboBoxModel<String> markerSizeModel;
	private DefaultComboBoxModel<String> markerColorModel;
	private DefaultComboBoxModel<String> markerLabelModel;
	private DefaultListModel<String> markerListModel;

	public OptionDialog() {
		setBounds(100, 100, 430, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(12, 23, 57, 15);
		contentPanel.add(lblLocation);

		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(81, 20, 126, 21);
		contentPanel.add(textFieldLocation);
		textFieldLocation.setColumns(10);

		JLabel lblZoomLevel = new JLabel("Zoom Level");
		lblZoomLevel.setBounds(12, 102, 73, 15);
		contentPanel.add(lblZoomLevel);

		spinnerZoomLevel = new JSpinner();
		spinModel = new SpinnerNumberModel(0, 0, 21, 1);
		spinnerZoomLevel.setModel(spinModel);
		spinnerZoomLevel.setBounds(93, 99, 45, 22);
		contentPanel.add(spinnerZoomLevel);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(12, 62, 57, 15);
		contentPanel.add(lblSize);

		textFieldSizeX = new JTextField();
		textFieldSizeX.setBounds(81, 58, 57, 21);
		contentPanel.add(textFieldSizeX);
		textFieldSizeX.setColumns(10);

		textFieldSizeY = new JTextField();
		textFieldSizeY.setColumns(10);
		textFieldSizeY.setBounds(150, 58, 57, 21);
		contentPanel.add(textFieldSizeY);

		comboBoxMaptype = new JComboBox<String>();

		maptypeModel = new DefaultComboBoxModel<String>(GoogleMap.getMaptypes());
		comboBoxMaptype.setModel(maptypeModel);
		comboBoxMaptype.setBounds(81, 138, 126, 21);
		contentPanel.add(comboBoxMaptype);

		JLabel lblMaptype = new JLabel("Maptype");
		lblMaptype.setBounds(12, 141, 57, 15);
		contentPanel.add(lblMaptype);

		listMarker = new JList<String>();
		markerListModel = new DefaultListModel<String>();
		listMarker.setModel(markerListModel);
		listMarker.setBounds(245, 241, 156, 177);
		listMarker.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!chckbxShowAllMarkers.isSelected() && listMarker.getSelectedIndex() != -1) {
					GoogleMap map = GoogleMapTemplate.Map();
					ArrayList<Marker> markers = map.getMarkers().getMarkers();
					textAreaMarker.setText(markers.get(listMarker.getSelectedIndex()).toString());
				}
			}
		});
		contentPanel.add(listMarker);

		JPanel panelMarkerParameter = new JPanel();
		panelMarkerParameter.setBounds(235, 10, 166, 188);
		contentPanel.add(panelMarkerParameter);
		panelMarkerParameter.setLayout(null);

		JLabel lblMarkerLocation = new JLabel("Location");
		lblMarkerLocation.setBounds(12, 10, 57, 15);
		panelMarkerParameter.add(lblMarkerLocation);

		textFieldMarkerLocation = new JTextField();
		textFieldMarkerLocation.setBounds(81, 7, 73, 21);
		panelMarkerParameter.add(textFieldMarkerLocation);
		textFieldMarkerLocation.setColumns(10);

		JLabel lblMarkerSize = new JLabel("Size");
		lblMarkerSize.setBounds(12, 43, 57, 15);
		panelMarkerParameter.add(lblMarkerSize);

		comboBoxMarkerSize = new JComboBox<String>();
		markerSizeModel = new DefaultComboBoxModel<String>(GoogleMap.getMarkerSize());
		comboBoxMarkerSize.setModel(markerSizeModel);
		comboBoxMarkerSize.setBounds(81, 42, 73, 21);
		panelMarkerParameter.add(comboBoxMarkerSize);

		JLabel lblMarkerColor = new JLabel("Color");
		lblMarkerColor.setBounds(12, 71, 57, 15);
		panelMarkerParameter.add(lblMarkerColor);

		textFieldMarkerColor = new JTextField();
		textFieldMarkerColor.setBounds(97, 85, 57, 21);
		panelMarkerParameter.add(textFieldMarkerColor);
		textFieldMarkerColor.setColumns(10);

		JLabel lblMarkerLabel = new JLabel("Label");
		lblMarkerLabel.setBounds(12, 127, 57, 15);
		panelMarkerParameter.add(lblMarkerLabel);

		comboBoxMarkerColor = new JComboBox<String>();
		comboBoxMarkerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				textFieldMarkerColor.setText((String) cb.getSelectedItem());
			}
		});
		markerColorModel = new DefaultComboBoxModel<String>(GoogleMap.getMarkerColor());
		comboBoxMarkerColor.setModel(markerColorModel);
		comboBoxMarkerColor.setBounds(12, 87, 65, 21);
		panelMarkerParameter.add(comboBoxMarkerColor);

		comboBoxMarkerLabel = new JComboBox<String>();
		markerLabelModel = new DefaultComboBoxModel<String>(GoogleMap.getMarkerLabel());
		comboBoxMarkerLabel.setModel(markerLabelModel);
		comboBoxMarkerLabel.setBounds(107, 124, 47, 21);
		panelMarkerParameter.add(comboBoxMarkerLabel);

		btnMarkerAdd = new JButton("Add");
		btnMarkerAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = textFieldMarkerLocation.getText();
				String size = (String) comboBoxMarkerSize.getSelectedItem();
				String color = textFieldMarkerColor.getText();
				String label = (String) markerLabelModel.getSelectedItem();

				MainController.addMarker(location, size, color, label);

				updateMarkerList();
			}
		});
		btnMarkerAdd.setBounds(81, 155, 73, 23);
		panelMarkerParameter.add(btnMarkerAdd);

		textAreaMarker = new JTextArea();
		textAreaMarker.setLineWrap(true);
		textAreaMarker.setBounds(12, 208, 212, 210);
		contentPanel.add(textAreaMarker);

		chckbxShowAllMarkers = new JCheckBox("Show All Markers");
		chckbxShowAllMarkers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowAllMarkers.isSelected()) {
					textAreaMarker.setText(GoogleMapTemplate.Map().getMarkers().toString());
				}
			}
		});
		chckbxShowAllMarkers.setBounds(8, 179, 133, 23);
		contentPanel.add(chckbxShowAllMarkers);

		btnMarkerDelete = new JButton("Delete");
		btnMarkerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listMarker.getSelectedIndex();
				if (index != -1) {
					GoogleMapTemplate.Map().getMarkers().getMarkers().remove(index);
					updateMarkerList();
				}

			}
		});
		btnMarkerDelete.setBounds(329, 208, 73, 23);
		contentPanel.add(btnMarkerDelete);

		btnMarekrsClear = new JButton("Clear");
		btnMarekrsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoogleMapTemplate.Map().getMarkers().getMarkers().clear();
				updateMarkerList();
			}
		});
		btnMarekrsClear.setBounds(245, 208, 72, 23);
		contentPanel.add(btnMarekrsClear);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateGoogleMap();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		initGoogleMap();
	}

	protected void updateGoogleMap() {
		String location = textFieldLocation.getText();
		String sizeX = textFieldSizeX.getText();
		String sizeY = textFieldSizeY.getText();

		String zoomLevel = String.valueOf(spinModel.getValue());

		String maptype = (String) maptypeModel.getSelectedItem();

		MainController.updateMap(location, sizeX, sizeY, zoomLevel, maptype);
	}

	public void initGoogleMap() {
		GoogleMap map = GoogleMapTemplate.Map();

		textFieldLocation.setText(map.getCenter());
		textFieldSizeX.setText(String.valueOf(map.getSizeX()));
		textFieldSizeY.setText(String.valueOf(map.getSizeY()));

		spinModel.setValue(map.getZoom());

		maptypeModel.setSelectedItem(map.getMaptype().equals("") ? GoogleMap.getMaptypes()[0] : map.getMaptype()); // 기본값이
																													// roadmap

		markerSizeModel.setSelectedItem(GoogleMap.getMarkerSize()[0]);
		markerColorModel.setSelectedItem(GoogleMap.getMarkerColor()[0]);
		markerLabelModel.setSelectedItem(GoogleMap.getMarkerLabel()[0]);

		updateMarkerList();
	}

	private void updateMarkerList() {
		markerListModel.clear();

		GoogleMap map = GoogleMapTemplate.Map();

		ArrayList<Marker> markers = map.getMarkers().getMarkers();
		// 마커가 있으면 처리
		if (markers.size() > 0) {
			for (int i = 0; i < markers.size(); i++) {
				markerListModel.addElement(String.format("markers_%d", i));
			}
		}

		if (!chckbxShowAllMarkers.isSelected())
			textAreaMarker.setText("");
	}
}
