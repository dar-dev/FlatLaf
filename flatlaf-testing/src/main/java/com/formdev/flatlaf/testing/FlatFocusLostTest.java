/*
 * Copyright 2026 FormDev Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.formdev.flatlaf.testing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.*;

/**
 * @author Karl Tauber
 */
public class FlatFocusLostTest
	extends FlatTestPanel
{
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( () -> {
			FlatTestFrame frame = FlatTestFrame.create( args, "FlatFocusLostTest" );
			frame.showFrame( FlatFocusLostTest::new );
		} );
	}

	public FlatFocusLostTest() {
		initComponents();

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for( int i = 1; i <= 8; i++ )
			listModel.addElement( "item " + i );
		list1.setModel( listModel );
		list2.setModel( listModel );
		list3.setModel( listModel );
		list1.setSelectionInterval( 2, 5 );
		list2.setSelectionInterval( 2, 5 );
		list3.setSelectionInterval( 2, 5 );

		tree1.expandRow( 1 );
		tree2.expandRow( 1 );
		tree3.expandRow( 1 );
		tree1.addSelectionInterval( 2, 5 );
		tree2.addSelectionInterval( 2, 5 );
		tree3.addSelectionInterval( 2, 5 );

		DefaultTableModel tableModel = new DefaultTableModel( 8, 2 ) {
			@Override
			public Object getValueAt( int row, int column ) {
				return "item " + row + "," + column;
			}
		};
		table1.setModel( tableModel );
		table2.setModel( tableModel );
		table3.setModel( tableModel );
		table1.addRowSelectionInterval( 2, 5 );
		table2.addRowSelectionInterval( 2, 5 );
		table3.addRowSelectionInterval( 2, 5 );
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		JLabel label1 = new JLabel();
		list1 = new JList<>();
		JScrollPane scrollPane1 = new JScrollPane();
		list2 = new JList<>();
		JScrollPane scrollPane2 = new JScrollPane();
		list3 = new JList<>();
		JLabel label5 = new JLabel();
		tree1 = new JTree();
		JScrollPane scrollPane3 = new JScrollPane();
		tree2 = new JTree();
		JScrollPane scrollPane4 = new JScrollPane();
		tree3 = new JTree();
		JLabel label6 = new JLabel();
		table1 = new JTable();
		JScrollPane scrollPane5 = new JScrollPane();
		table2 = new JTable();
		JScrollPane scrollPane6 = new JScrollPane();
		table3 = new JTable();

		//======== this ========
		setLayout(new MigLayout(
			"insets dialog,hidemode 3",
			// columns
			"[fill]" +
			"[sizegroup 1,fill]" +
			"[sizegroup 1,fill]" +
			"[sizegroup 1,fill]",
			// rows
			"[]" +
			"[150,fill]" +
			"[150,fill]" +
			"[150,fill]"));

		//---- label2 ----
		label2.setText("no scroll pane");
		add(label2, "cell 1 0");

		//---- label3 ----
		label3.setText("scroll pane");
		add(label3, "cell 2 0");

		//---- label4 ----
		label4.setText("scroll pane with empty border");
		add(label4, "cell 3 0");

		//---- label1 ----
		label1.setText("JList:");
		add(label1, "cell 0 1");
		add(list1, "cell 1 1");

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(list2);
		}
		add(scrollPane1, "cell 2 1");

		//======== scrollPane2 ========
		{
			scrollPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
			scrollPane2.setViewportView(list3);
		}
		add(scrollPane2, "cell 3 1");

		//---- label5 ----
		label5.setText("JTree:");
		add(label5, "cell 0 2");
		add(tree1, "cell 1 2");

		//======== scrollPane3 ========
		{
			scrollPane3.setViewportView(tree2);
		}
		add(scrollPane3, "cell 2 2");

		//======== scrollPane4 ========
		{
			scrollPane4.setBorder(new EmptyBorder(5, 5, 5, 5));
			scrollPane4.setViewportView(tree3);
		}
		add(scrollPane4, "cell 3 2");

		//---- label6 ----
		label6.setText("JTable:");
		add(label6, "cell 0 3");

		//---- table1 ----
		table1.setPreferredScrollableViewportSize(new Dimension(100, 150));
		add(table1, "cell 1 3");

		//======== scrollPane5 ========
		{

			//---- table2 ----
			table2.setPreferredScrollableViewportSize(new Dimension(100, 150));
			scrollPane5.setViewportView(table2);
		}
		add(scrollPane5, "cell 2 3");

		//======== scrollPane6 ========
		{
			scrollPane6.setBorder(new EmptyBorder(5, 5, 5, 5));

			//---- table3 ----
			table3.setPreferredScrollableViewportSize(new Dimension(100, 150));
			scrollPane6.setViewportView(table3);
		}
		add(scrollPane6, "cell 3 3");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JList<String> list1;
	private JList<String> list2;
	private JList<String> list3;
	private JTree tree1;
	private JTree tree2;
	private JTree tree3;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
