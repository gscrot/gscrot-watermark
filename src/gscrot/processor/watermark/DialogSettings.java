package gscrot.processor.watermark;

import gscrot.processor.watermark.WatermarkPlugin.Mode;

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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnFont)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnTextColor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBackgroundColor, 0, 0, Short.MAX_VALUE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)))
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
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addContainerGap())
		);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		getContentPane().setLayout(groupLayout);
		
	}
}
