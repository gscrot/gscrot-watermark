package gscrot.processor.watermark;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
	private JButton btnColor;
	private JTextPane textPane;
	
	public DialogSettings() {
		setTitle("Watermark Settings");
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton rdbtnImage = new JRadioButton("Image");
		group.add(rdbtnImage);
		
		JLabel lblFile = new JLabel("File:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		
		JRadioButton rdbtnLabel = new JRadioButton("Label");
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
		
		btnColor = new JButton("Color");
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WatermarkPlugin.setFont(textPane.getFont());
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnFont)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnColor))
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
					.addContainerGap(152, Short.MAX_VALUE))
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
						.addComponent(btnColor))
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addContainerGap())
		);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		getContentPane().setLayout(groupLayout);
		
	}
}
