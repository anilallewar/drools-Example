package com.anil.drools.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

public final class StreamUtils {
	private static final Logger LOGGER = Logger.getLogger(StreamUtils.class);

	/**
	 * Provided to prohibit instantiation.
	 */
	private StreamUtils() {
		super();
	}

	/**
	 * Given the classpath location, such as
	 * "com/fuelquest/mothra/masterdata/resource/foo.xml", will locate the
	 * resource and provide an input stream that can be used to read the content
	 * of the resource.
	 * 
	 * @param resourceClassPath
	 *            The location of the item in the classpath.
	 * @return An InputStream that can be used to read the resource, or null if
	 *         the resource could not be found.
	 */
	public static InputStream streamFromClasspathResource(
			final String resourceClassPath) {
		final Class<StreamUtils> clazz = StreamUtils.class;
		final ClassLoader clLoader = clazz.getClassLoader();
		final InputStream inStream = clLoader
				.getResourceAsStream(resourceClassPath);
		if (inStream == null) {
			LOGGER.debug(String.format("Resource %s NOT FOUND.",
					resourceClassPath));
		}
		return inStream;
	}

	/**
	 * Given a string, returns an InputStream that can be used to read the
	 * contents of the String in a stream fashion.
	 * 
	 * @param string
	 *            The string that will be read from the returned InputStream.
	 * 
	 * @return An InputStream that can be used to read the String contents.
	 */
	public static InputStream streamFromString(final String string) {
		return new ByteArrayInputStream(string.getBytes(Charset
				.defaultCharset()));
	}

}
