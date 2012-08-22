package org.gg.mood.service;

import org.gg.mood.service.Hello;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HelloServiceTest {
	
	Hello service;
	
	@Before
	public void setUp() {
		service = new Hello();
	}

	@Test
	public void testSayXMLHello() {
		Hello service = new Hello();
		assertEquals("<?xml version=\"1.0\"?><hello> Hello Jersey</hello>", service.sayXMLHello());
	}

	@Test
	public void testSayHtmlHello() {
		Hello service = new Hello();
		assertEquals("<html> <title>Hello Jersey</title><body><h1>Hello Jersey</body></h1></html> ", service.sayHtmlHello());
	}
	
	@Test
	public void testSayPlainTextHello() {
		Hello service = new Hello();
		assertEquals("Hello Jersey", service.sayPlainTextHello());
	}

}
