/**
 * Copyright 2012 Sven Diedrichsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package de.jollyday.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author sven
 *
 */
public class ClassLoadingUtilTest {

	ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

	@Test
	public void testGetClassloader() {
		assertSame(Thread.currentThread().getContextClassLoader(),
				classLoadingUtil.getClassloader(),
				"Expected the current threads classloader.");
	}

	@Test
	public void testClassNotFound() throws ClassNotFoundException {
		assertThrows(ClassNotFoundException.class, () -> classLoadingUtil.loadClass(""));
	}

	@Test
	public void testClassloadingCorrect() throws Exception {
		assertSame(ClassLoadingUtil.class, classLoadingUtil.loadClass(ClassLoadingUtil.class.getName()));
	}

}