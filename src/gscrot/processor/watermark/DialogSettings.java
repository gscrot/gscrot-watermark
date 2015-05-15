package gscrot.processor.watermark;

import gscrot.processor.watermark.WatermarkPlugin.Mode;
import gscrot.processor.watermark.WatermarkPlugin.Position;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class DialogSettings extends JDialog {
	
	private JTextField textField;
	private JButton btnFont;
	private JButton btnTextColor;
	private JTextPane textPane;
	private JButton btnBackgroundColor;
	private JRadioButton rdbtnLabel;
	private JRadioButton rdbtnImage;
	private JPanel panelPosition;
	private JRadioButton rdbtnTopRight;
	private JRadioButton rdbtnBottomRight;
	private JRadioButton rdbtnBottomLeft;
	private JRadioButton rdbtnTopLeft;
	
	public DialogSettings() {
		setTitle("Watermark Settings");
		
		ButtonGroup group = new ButtonGroup();
		
		rdbtnImage = new JRadioButton("Image");
		group.add(rdbtnImage);
		
		JLabel lblFile = new JLabel("File:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		
		rdbtnLabel = new JRadioButton("Label");
		group.add(rdbtnLabel);
		rdbtnLabel.setSelected(true);
		
		JLabel lblText = new JLabel("Text:");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		btnFont = new JButton("Font");
		btnFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFontChooser j = new JFontChooser();
				j.showDialog(DialogSettings.this);
				textPane.setFont(j.getSelectedFont());
			}
		});
		
		btnTextColor = new JButton("Text Color");
		btnTextColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(DialogSettings.this, "Select Foreground Color", WatermarkPlugin.foreground);
				textPane.setForeground(color);
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WatermarkPlugin.font = textPane.getFont();
				WatermarkPlugin.foreground = textPane.getForeground();
				WatermarkPlugin.background = textPane.getBackground();
				WatermarkPlugin.mode = rdbtnLabel.isSelected() ? Mode.TEXT : Mode.IMAGE;
				
				if (rdbtnTopRight.isSelected()) {
					WatermarkPlugin.position = Position.TOPRIGHT;
				} else if (rdbtnTopLeft.isSelected()) {
					WatermarkPlugin.position = Position.TOPLEFT;
				} else if (rdbtnBottomRight.isSelected()) {
					WatermarkPlugin.position = Position.BOTTOMRIGHT;
				} else if (rdbtnBottomLeft.isSelected()) {
					WatermarkPlugin.position = Position.BOTTOMLEFT;
				}
				
				WatermarkPlugin.save();
			}
		});
		
		btnBackgroundColor = new JButton("Background");
		btnBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(DialogSettings.this, "Select Background Color", WatermarkPlugin.background);
				textPane.setBackground(color);
			}
		});
		
		group = new ButtonGroup();
		
		panelPosition = new JPanel();
		
		rdbtnTopRight = new JRadioButton("");
		group.add(rdbtnTopRight);
		rdbtnTopRight.setSelected(WatermarkPlugin.position == Position.TOPRIGHT);
		
		rdbtnBottomRight = new JRadioButton("");
		group.add(rdbtnBottomRight);
		rdbtnBottomRight.setSelected(WatermarkPlugin.position == Position.BOTTOMRIGHT);

		rdbtnBottomLeft = new JRadioButton("");
		group.add(rdbtnBottomLeft);
		rdbtnBottomLeft.setSelected(WatermarkPlugin.position == Position.BOTTOMLEFT);
		
		rdbtnTopLeft = new JRadioButton("");
		group.add(rdbtnTopLeft);
		rdbtnTopLeft.setSelected(WatermarkPlugin.position == Position.TOPLEFT);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblText)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(rdbtnTopLeft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
												.addComponent(rdbtnBottomLeft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(panelPosition, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnFont)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnTextColor)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnBottomRight, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(btnBackgroundColor, 0, 0, Short.MAX_VALUE)
											.addComponent(rdbtnTopRight))))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOk)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(rdbtnImage)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblFile)
								.addGap(12)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBrowse))))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnImage)
								.addComponent(lblFile)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBrowse))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnLabel)
							.addComponent(lblText))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFont)
						.addComponent(btnTextColor)
						.addComponent(btnBackgroundColor))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panelPosition, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(btnOk)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnTopLeft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
									.addComponent(rdbtnBottomLeft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnTopRight)
									.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
									.addComponent(rdbtnBottomRight, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGap(51))))
		);
		
		textPane = new JTextPane();
		textPane.setForeground(WatermarkPlugin.foreground);
		textPane.setBackground(WatermarkPlugin.background);
		textPane.setFont(WatermarkPlugin.font);
		scrollPane.setViewportView(textPane);
		getContentPane().setLayout(groupLayout);
		
	}
}
