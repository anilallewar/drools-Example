package com.anil.drools.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public final class XMLParseUtils {

	/**
	 * Private constructor to prohibit instantiation
	 */
	private XMLParseUtils() {
		super();
	}

	/**
	 * Parses the passed stream as an XML document and returns a Document object
	 * back.
	 * 
	 * @param xmlString
	 *            The XML document as a String.
	 * @return The XML document. Throws an RuntimeException if there is a parse
	 *         error.
	 */
	public static Document parse(final InputStream inStream) {
		Document ret = null;
		try {
			if (inStream == null) {
				throw new RuntimeException(
						"XML Input Stream for parsing is null");
			}
			final SAXReader saxReader = new SAXReader();
			ret = saxReader.read(inStream);
		} catch (final DocumentException exc) {
			throw new RuntimeException("XML Parsing error", exc);
		}
		return ret;
	}

	/**
	 * Parses the passed string as an XML document and returns a Document object
	 * back.
	 * 
	 * @param xmlString
	 *            The XML document as a String.
	 * @return The XML document. Throws an RuntimeException if there is a parse
	 *         error.
	 */
	public static Document parse(final String xmlString) {
		final InputStream inStream = StreamUtils.streamFromString(xmlString);
		return XMLParseUtils.parse(inStream);
	}
}