package practice.advance.mvc.view;

import practice.advance.mvc.common.GoogleMapTemplate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import practice.advance.mvc.controller.MainController;

public class MainView extends JFrame {
	private JTextField textField = new JTextField(30);
	private JButton button = new JButton("검색");
	private JButton optButton = new JButton("옵션");
	private JPanel panel = new JPanel();

	private JLabel googleMap = new JLabel();
	private final JScrollPane scrollPane = new JScrollPane(googleMap);

	private OptionDialog dialog = null;

	public MainView() {
		setTitle("Google Maps");
		this.setBounds(600, 300, 0, 0);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);

		scrollPane.setPreferredSize(new Dimension(400, 400));

		panel.add(textField);
		panel.add(button);
		panel.add(optButton);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GoogleMapTemplate.Map().setChanged(true);
				ImageIcon icon = MainController.setMap(textField.getText());
				googleMap.setIcon(icon);
			}
		});
		optButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (dialog == null) {
					dialog = new OptionDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

					dialog.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							if (GoogleMapTemplate.Map().isChanged()) {
								ImageIcon icon = MainController.setMap(GoogleMapTemplate.Map().getCenter());
								googleMap.setIcon(icon);
							}
							dialog = null;
						}

					});
				}
			}
		});

		getContentPane().add(BorderLayout.NORTH, panel);

		getContentPane().add(scrollPane, BorderLayout.CENTER);
		pack();
	}
}
