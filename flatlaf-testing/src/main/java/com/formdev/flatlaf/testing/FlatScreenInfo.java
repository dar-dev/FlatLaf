/*
 * Copyright 2020 FormDev Software GmbH
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

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Displays information about screens connected to a computer.
 *
 * This is a single-file program that can be compiled/run without any other
 * FlatLaf code or dependencies.
 *
 * Since Java 11, you can run this program from source with:
 *     java FlatScreenInfo.java
 *
 * @author Karl Tauber
 */
public class FlatScreenInfo
{
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( () -> {
			printScreenInfo();
		} );
	}

	private static void printScreenInfo() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreenDevice = graphicsEnvironment.getDefaultScreenDevice();
		GraphicsDevice[] screenDevices = graphicsEnvironment.getScreenDevices();

		System.out.print( "Scale factors:  " );
		for( GraphicsDevice gd : screenDevices ) {
			GraphicsConfiguration gc = gd.getDefaultConfiguration();

			if( gd != screenDevices[0] )
				System.out.print( " / " );

			System.out.print( (int) (gc.getDefaultTransform().getScaleX() * 100) );
			System.out.print( "%" );
		}
		System.out.println();

		System.out.println( "Java version:   " + System.getProperty( "java.version" ) );
		System.out.println( "Java vendor:    " + System.getProperty( "java.vendor" ) );
		System.out.println( "OS name:        " + System.getProperty( "os.name" ) );
		System.out.println( "OS version:     " + System.getProperty( "os.version" ) );
		System.out.println( "OS arch:        " + System.getProperty( "os.arch" ) );

		for( GraphicsDevice gd : screenDevices ) {
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			DisplayMode displayMode = gd.getDisplayMode();
			Rectangle bounds = gc.getBounds();
			int width = displayMode.getWidth();
			int height = displayMode.getHeight();
			double boundsScaleX = (bounds.width > width)
				? (double) bounds.width / (double) width
				: -((double) width / (double) bounds.width);
			double boundsScaleY = (bounds.height > height)
				? (double) bounds.height / (double) height
				: -((double) height / (double) bounds.height);
			boundsScaleX = Math.round( boundsScaleX * 1000. ) / 1000.;
			boundsScaleY = Math.round( boundsScaleY * 1000. ) / 1000.;
			Insets screenInsets = toolkit.getScreenInsets( gc );
			AffineTransform defaultTransform = gc.getDefaultTransform();
			double scaleX = defaultTransform.getScaleX();
			double scaleY = defaultTransform.getScaleY();

			System.out.println();
			System.out.print( "ID:      " + gd.getIDstring() );
			if( gd == defaultScreenDevice )
				System.out.print( " (main)" );
			System.out.println();

			System.out.printf( "Size:    %d x %d / %d Bit / %d Hz%n",
				displayMode.getWidth(), displayMode.getHeight(),
				displayMode.getBitDepth(), displayMode.getRefreshRate() );
			System.out.printf( "Bounds:  %d x %d / x %d / y %d",
				bounds.width, bounds.height, bounds.x, bounds.y );
			if( Math.abs( boundsScaleX ) != 1 || Math.abs( boundsScaleY ) != 1 )
				System.out.printf( "   (scale %s)", toString( boundsScaleX, boundsScaleY ) );
			System.out.println();
			System.out.printf( "Insets:  left %d / right %d / top %d / bottom %d%n",
				screenInsets.left, screenInsets.right, screenInsets.top, screenInsets.bottom );
			System.out.println( "Scale:   " + toString( scaleX, scaleY ) );

			// report warning if screen bounds intersects with another screen
			// https://github.com/JFormDesigner/FlatLaf/issues/177
			for( GraphicsDevice gd2 : screenDevices ) {
				if( gd2 == gd )
					continue;

				Rectangle bounds2 = gd2.getDefaultConfiguration().getBounds();
				if( bounds2.intersects( bounds ) ) {
					System.out.println( "Warning: bounds of this screen intersect with bounds of " + gd2.getIDstring() );
					System.out.println( "         this can lead to misplaced popups" );
				}
			}
		}

		System.out.println();
		System.out.println();

		// system properties
		System.out.println( "sun.java2d.uiScale:               " + System.getProperty( "sun.java2d.uiScale" ) );
		System.out.println( "sun.java2d.uiScale.enabled:       " + System.getProperty( "sun.java2d.uiScale.enabled" ) );
		System.out.println( "flatlaf.uiScale:                  " + System.getProperty( "flatlaf.uiScale" ) );
		System.out.println( "flatlaf.uiScale.enabled:          " + System.getProperty( "flatlaf.uiScale.enabled" ) );
		System.out.println( "flatlaf.uiScale.allowScaleDown:   " + System.getProperty( "flatlaf.uiScale.allowScaleDown" ) );
		System.out.println( "flatlaf.uiScale.fontSizeDivider:  " + System.getProperty( "flatlaf.uiScale.fontSizeDivider" ) );
		System.out.println();

		// environment variables
		System.out.println( "XDG_CURRENT_DESKTOP:       " + System.getenv( "XDG_CURRENT_DESKTOP" ) );
		System.out.println( "GNOME_DESKTOP_SESSION_ID:  " + System.getenv( "GNOME_DESKTOP_SESSION_ID" ) );
		System.out.println( "KDE_FULL_SESSION:          " + System.getenv( "KDE_FULL_SESSION" ) );
		System.out.println( "GDK_SCALE:                 " + System.getenv( "GDK_SCALE" ) );
		System.out.println( "J2D_UISCALE:               " + System.getenv( "J2D_UISCALE" ) );
		System.out.println();

		// desktop properties
		System.out.println( "win.messagebox.font:  " + toolkit.getDesktopProperty( "win.messagebox.font" ) );
		System.out.println( "win.defaultGUI.font:  " + toolkit.getDesktopProperty( "win.defaultGUI.font" ) );
		System.out.println( "gnome.Gtk/FontName:   " + toolkit.getDesktopProperty( "gnome.Gtk/FontName" ) );
		System.out.println();

		// KDE
		List<String> kdeglobals = readConfig( "kdeglobals" );
		List<String> kcmfonts = readConfig( "kcmfonts" );
		List<String> kwinrc = readConfig( "kwinrc" );
		String generalFont = getConfigEntry( kdeglobals, "General", "font" );
		String forceFontDPI = getConfigEntry( kcmfonts, "General", "forceFontDPI" );
		String scale = getConfigEntry( kwinrc, "Xwayland", "Scale" );
		String screenScaleFactors = getConfigEntry( kdeglobals, "KScreen", "ScreenScaleFactors" );
		String xwaylandClientsScale = getConfigEntry( kdeglobals, "KScreen", "XwaylandClientsScale" );
		System.out.println( "kdeglobals: [General]  font:                  " + generalFont );
		System.out.println( "kcmfonts:   [General]  forceFontDPI:          " + forceFontDPI );
		System.out.println( "kwinrc:     [Xwayland] Scale:                 " + scale );
		System.out.println( "kdeglobals: [KScreen]  ScreenScaleFactors:    " + screenScaleFactors );
		System.out.println( "kdeglobals: [KScreen]  XwaylandClientsScale:  " + xwaylandClientsScale );
	}

	private static String toString( double scaleX, double scaleY ) {
		return (scaleX == scaleY)
			? String.valueOf( scaleX )
			: scaleX + " / " + scaleY;
	}

	// copy of LinuxFontPolicy.readConfig()
	@SuppressWarnings( "MixedMutabilityReturnType" ) // Error Prone
	private static List<String> readConfig( String filename ) {
		File userHome = new File( System.getProperty( "user.home" ) );

		// search for config file
		String[] configDirs = {
			".config", // KDE 5
			".kde4/share/config", // KDE 4
			".kde/share/config"// KDE 3
		};
		File file = null;
		for( String configDir : configDirs ) {
			file = new File( userHome, configDir + "/" + filename );
			if( file.isFile() )
				break;
		}
		if( !file.isFile() )
			return Collections.emptyList();

		// read config file
		System.out.println( "read " + file );
		ArrayList<String> lines = new ArrayList<>( 200 );
		try( BufferedReader reader = new BufferedReader( new InputStreamReader(
			new FileInputStream( file ), StandardCharsets.US_ASCII ) ) )
		{
			String line;
			while( (line = reader.readLine()) != null )
				lines.add( line );
		} catch( IOException ex ) {
			System.err.println( "FlatLaf: Failed to read '" + filename + "'." );
			ex.printStackTrace();
		}
		return lines;
	}

	// copy of LinuxFontPolicy.getConfigEntry()
	private static String getConfigEntry( List<String> config, String group, String key ) {
		int groupLength = group.length();
		int keyLength = key.length();
		boolean inGroup = false;
		for( String line : config ) {
			if( !inGroup ) {
				if( line.length() >= groupLength + 2 &&
					line.charAt( 0 ) == '[' &&
					line.charAt( groupLength + 1 ) == ']' &&
					line.indexOf( group ) == 1 )
				{
					inGroup = true;
				}
			} else {
				if( line.startsWith( "[" ) )
					return null;

				if( line.length() >= keyLength + 2 &&
					line.charAt( keyLength ) == '=' &&
					line.startsWith( key ) )
				{
					return line.substring( keyLength + 1 );
				}
			}
		}
		return null;
	}
}
