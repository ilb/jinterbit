/**
 * 
 */
package com.ipc.oce.junit;

import java.io.IOException;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

/**
 * @author Konovalov
 *
 */
public class Reconnect extends BasicTest {

	@Test
	public void doReconnect() throws JIException, IOException {
		app.reconnect();
	}
}
