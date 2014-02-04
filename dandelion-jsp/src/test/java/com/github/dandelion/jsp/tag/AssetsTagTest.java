/*
 * [The "BSD licence"]
 * Copyright (c) 2013-2014 Dandelion
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.dandelion.jsp.tag;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.dandelion.core.config.StandardConfigurationLoader;
import com.github.dandelion.jsp.PhantomJsTest;

public class AssetsTagTest extends PhantomJsTest {

	@BeforeClass
	public static void setup() {
		String propertiesPath = new File("src/test/resources/dandelion/").getAbsolutePath();
		System.setProperty(StandardConfigurationLoader.DANDELION_CONFIGURATION, propertiesPath);
	}

	@Test
	public void assets_scopes() {
		goTo("/assets_scopes.jsp");
		assertThat(text("link")).hasSize(1);
		assertThat(text("script")).hasSize(2);
	}

	@Test
	public void assets_excludedScopes() {
		goTo("/assets_excludedScopes.jsp");
		assertThat(text("script")).hasSize(0);
	}

	@Test
	public void assets_excludedAssets() {
		goTo("/assets_excludedAssets.jsp");
		assertThat(text("script")).hasSize(1);
	}

	@AfterClass
	public static void tearDown() {
		System.clearProperty(StandardConfigurationLoader.DANDELION_CONFIGURATION);
	}
}
