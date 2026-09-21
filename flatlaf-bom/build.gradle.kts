/*
 * Copyright 2026 FormDev Software, Karl Tauber
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

plugins {
	`java-platform`
	`flatlaf-publish`
}

// necessary to get correct version numbers from font projects
evaluationDependsOn( ":flatlaf-fonts-inter" )
evaluationDependsOn( ":flatlaf-fonts-jetbrains-mono" )
evaluationDependsOn( ":flatlaf-fonts-roboto" )
evaluationDependsOn( ":flatlaf-fonts-roboto-mono" )

dependencies {
	constraints {
		api( "com.formdev:flatlaf:${version}" )

		api( "com.formdev:flatlaf-extras:${version}" )
		api( "com.formdev:flatlaf-intellij-themes:${version}" )
		api( "com.formdev:flatlaf-jide-oss:${version}" )
		api( "com.formdev:flatlaf-swingx:${version}" )

		// fonts
		val fonts = arrayOf(
			"flatlaf-fonts-inter",
			"flatlaf-fonts-jetbrains-mono",
			"flatlaf-fonts-roboto",
			"flatlaf-fonts-roboto-mono",
		)
		for( font in fonts ) {
			val fontProject = project( ":$font" )
			// remove trailing "-SNAPSHOT" from font versions because font projects are not built with FlatLaf snapshots
			val fontVersion = fontProject.version.toString().removeSuffix( "-SNAPSHOT" )
			api( "com.formdev:$font:$fontVersion" )
		}
	}
}

flatlafPublish {
	artifactId = "flatlaf-bom"
	name = "FlatLaf BOM"
	description = "Flat Look and Feel BOM"
}
