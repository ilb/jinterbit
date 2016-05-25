/**
 * 
 */
package com.ipc.oce.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Konovalov
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	NewValue.class,
	TestJDBC.class,
	AltConnect.class,
	Reconnect.class,
	UnloadResultSetToLocal.class, 
	Enum_minus4.class, 
	JUInfoBaseUsers.class, 
	JUEnumMetadata.class,
	JUReport01.class,
	JUInfoReg.class,
	Test01.class,
	JUDocuments.class,
	JUFindByRef.class,
	GetSomeUUID.class,
	JUXDTO.class,
	JUCMO.class,
	SchemasDance.class,
	ErrorListenerTest.class,
	CatalogParent.class,
	TryToGetOwner.class})
public class Suite01 {

}
