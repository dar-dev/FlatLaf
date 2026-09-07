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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.*;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.*;

/**
 * @author Karl Tauber
 */
public class FlatColorFunctionsTest
	extends FlatTestPanel
{
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( () -> {
			FlatTestFrame frame = FlatTestFrame.create( args, "FlatColorFunctionsTest" );
			frame.showFrame( FlatColorFunctionsTest::new );
		} );
	}

	FlatColorFunctionsTest() {
		initComponents();

		mixRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.mix( color1, color2, 1f - weight, ColorFunctions.RGB ) );
		mixLinearRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.mix( color1, color2, 1f - weight, ColorFunctions.LRGB ) );
		mixOklabPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.mix( color1, color2, 1f - weight, ColorFunctions.OKLAB ) );

		tintRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.tint( color1, weight, ColorFunctions.RGB ) );
		tintLinearRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.tint( color1, weight, ColorFunctions.LRGB ) );
		tintOklabPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.tint( color1, weight, ColorFunctions.OKLAB ) );

		shadeRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.shade( color1, weight, ColorFunctions.RGB ) );
		shadeLinearRGBPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.shade( color1, weight, ColorFunctions.LRGB ) );
		shadeOklabPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.shade( color1, weight, ColorFunctions.OKLAB ) );

		lightenPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.lighten( color1, weight ) );
		darkenPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.darken( color1, weight ) );

		saturatePreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.saturate( color1, weight ) );
		desaturatePreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.desaturate( color1, weight ) );

		spinPreview.setColorMethod( (color1, color2, weight) ->
			ColorFunctions.spin( color1, weight * 360 ) );

		color1Chooser.getSelectionModel().addChangeListener( e -> {
			Color color1 = color1Chooser.getColor();
			for( Component c : getComponents() ) {
				if( c instanceof GradientPreview )
					((GradientPreview)c).setColor1( color1 );
			}
		} );
		color2Chooser.getSelectionModel().addChangeListener( e -> {
			Color color2 = color2Chooser.getColor();
			for( Component c : getComponents() ) {
				if( c instanceof GradientPreview )
					((GradientPreview)c).setColor2( color2 );
			}
		} );
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JLabel mixLabel = new JLabel();
		JLabel label4 = new JLabel();
		mixRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JPanel panel1 = new JPanel();
		color1Chooser = new JColorChooser();
		color2Chooser = new JColorChooser();
		JLabel label5 = new JLabel();
		mixLinearRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel label6 = new JLabel();
		mixOklabPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel tintLabel = new JLabel();
		JLabel label1 = new JLabel();
		tintRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel label2 = new JLabel();
		tintLinearRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel label3 = new JLabel();
		tintOklabPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel shadeLabel = new JLabel();
		JLabel label7 = new JLabel();
		shadeRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel label8 = new JLabel();
		shadeLinearRGBPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel label9 = new JLabel();
		shadeOklabPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel lightenLabel = new JLabel();
		lightenPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel darkenLabel = new JLabel();
		darkenPreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel saturateLabel = new JLabel();
		saturatePreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel desaturateLabel = new JLabel();
		desaturatePreview = new FlatColorFunctionsTest.GradientPreview();
		JLabel spinLabel = new JLabel();
		spinPreview = new FlatColorFunctionsTest.GradientPreview();

		//======== this ========
		setLayout(new MigLayout(
			"insets dialog,hidemode 3",
			// columns
			"[76,fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]para" +
			"[]" +
			"[]" +
			"[]para" +
			"[]" +
			"[]" +
			"[]para" +
			"[]" +
			"[]para" +
			"[]" +
			"[]para" +
			"[]"));

		//---- mixLabel ----
		mixLabel.setText("mix");
		add(mixLabel, "cell 0 0");

		//---- label4 ----
		label4.setText("RGB");
		add(label4, "cell 0 0,alignx right,growx 0");
		add(mixRGBPreview, "cell 1 0");

		//======== panel1 ========
		{
			panel1.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[fill]",
				// rows
				"[]" +
				"[]"));
			panel1.add(color1Chooser, "cell 0 0");
			panel1.add(color2Chooser, "cell 0 1");
		}
		add(panel1, "cell 2 0 1 14");

		//---- label5 ----
		label5.setText("linear RGB");
		add(label5, "cell 0 1,alignx right,growx 0");
		add(mixLinearRGBPreview, "cell 1 1");

		//---- label6 ----
		label6.setText("Oklab");
		add(label6, "cell 0 2,alignx right,growx 0");
		add(mixOklabPreview, "cell 1 2");

		//---- tintLabel ----
		tintLabel.setText("tint");
		add(tintLabel, "cell 0 3");

		//---- label1 ----
		label1.setText("RGB");
		add(label1, "cell 0 3,alignx right,growx 0");
		add(tintRGBPreview, "cell 1 3");

		//---- label2 ----
		label2.setText("linear RGB");
		add(label2, "cell 0 4,alignx right,growx 0");
		add(tintLinearRGBPreview, "cell 1 4");

		//---- label3 ----
		label3.setText("Oklab");
		add(label3, "cell 0 5,alignx right,growx 0");
		add(tintOklabPreview, "cell 1 5");

		//---- shadeLabel ----
		shadeLabel.setText("shade");
		add(shadeLabel, "cell 0 6");

		//---- label7 ----
		label7.setText("RGB");
		add(label7, "cell 0 6,alignx right,growx 0");
		add(shadeRGBPreview, "cell 1 6");

		//---- label8 ----
		label8.setText("linear RGB");
		add(label8, "cell 0 7,alignx right,growx 0");
		add(shadeLinearRGBPreview, "cell 1 7");

		//---- label9 ----
		label9.setText("Oklab");
		add(label9, "cell 0 8,alignx right,growx 0");
		add(shadeOklabPreview, "cell 1 8");

		//---- lightenLabel ----
		lightenLabel.setText("lighten");
		add(lightenLabel, "cell 0 9");
		add(lightenPreview, "cell 1 9");

		//---- darkenLabel ----
		darkenLabel.setText("darken");
		add(darkenLabel, "cell 0 10");
		add(darkenPreview, "cell 1 10");

		//---- saturateLabel ----
		saturateLabel.setText("saturate");
		add(saturateLabel, "cell 0 11");
		add(saturatePreview, "cell 1 11");

		//---- desaturateLabel ----
		desaturateLabel.setText("desaturate");
		add(desaturateLabel, "cell 0 12");
		add(desaturatePreview, "cell 1 12");

		//---- spinLabel ----
		spinLabel.setText("spin");
		add(spinLabel, "cell 0 13");
		add(spinPreview, "cell 1 13");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private FlatColorFunctionsTest.GradientPreview mixRGBPreview;
	private JColorChooser color1Chooser;
	private JColorChooser color2Chooser;
	private FlatColorFunctionsTest.GradientPreview mixLinearRGBPreview;
	private FlatColorFunctionsTest.GradientPreview mixOklabPreview;
	private FlatColorFunctionsTest.GradientPreview tintRGBPreview;
	private FlatColorFunctionsTest.GradientPreview tintLinearRGBPreview;
	private FlatColorFunctionsTest.GradientPreview tintOklabPreview;
	private FlatColorFunctionsTest.GradientPreview shadeRGBPreview;
	private FlatColorFunctionsTest.GradientPreview shadeLinearRGBPreview;
	private FlatColorFunctionsTest.GradientPreview shadeOklabPreview;
	private FlatColorFunctionsTest.GradientPreview lightenPreview;
	private FlatColorFunctionsTest.GradientPreview darkenPreview;
	private FlatColorFunctionsTest.GradientPreview saturatePreview;
	private FlatColorFunctionsTest.GradientPreview desaturatePreview;
	private FlatColorFunctionsTest.GradientPreview spinPreview;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	//---- class GradientPreview ----------------------------------------------

	private static class GradientPreview
		extends JComponent
	{
		private Color color1 = Color.red;
		private Color color2 = Color.green;
		private ColorMethod colorMethod;

		private BufferedImage image;

		@Override
		public Dimension getPreferredSize() {
			return isPreferredSizeSet()
				? super.getPreferredSize()
				: UIScale.scale( new Dimension( 400, 40 ) );
		}

		void setColor1( Color color1 ) {
			if( Objects.equals( this.color1, color1 ) )
				return;

			this.color1 = color1;
			image = null;
			repaint();
		}

		void setColor2( Color color2 ) {
			if( Objects.equals( this.color2, color2 ) )
				return;

			this.color2 = color2;
			image = null;
			repaint();
		}

		void setColorMethod( ColorMethod colorMethod ) {
			if( this.colorMethod == colorMethod )
				return;

			this.colorMethod = colorMethod;
			image = null;
			repaint();
		}

		@Override
		protected void paintComponent( Graphics g ) {
			int width = getWidth();

			if( image == null || image.getWidth() != width ) {
				image = new BufferedImage( width, 1, BufferedImage.TYPE_INT_RGB );

				for( int i = 0; i < width; i++ ) {
					float weight = (float) i / width;
					Color result = (colorMethod != null)
						? colorMethod.apply( color1, color2, weight )
						: ColorFunctions.mix( color1, color2, 1f - weight );
					image.setRGB( i, 0, result.getRGB() );
				}
			}

			g.drawImage( image, 0, 0, width, getHeight(), null );
		}
	}

	//---- interface ColorMethod ----------------------------------------------

	private interface ColorMethod {
		Color apply( Color color1, Color color2, float weight );
	}
}
