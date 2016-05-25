/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.metadata.collection.OCMetadataRoleCollection;
import com.ipc.oce.metadata.objects.OCRoleMetadataObject;
import com.ipc.oce.objects.OCInfoBaseUser;
import com.ipc.oce.objects.OCInfoBaseUsersManager;
import com.ipc.oce.objects.OCUserArray;
import com.ipc.oce.objects.OCUserRoles;

/**
 * @author Konovalov
 *
 */
public class JUInfoBaseUsers extends BasicTest {

	@Test
	public void getUsers() throws JIException {
		OCInfoBaseUsersManager manager = app.getInfoBaseUserManager();
		OCInfoBaseUser user = manager.getCurrentUser();
		System.out.println("getFullName: " + user.getFullName());
		System.out.println("getName: " + user.getName());
		System.out.println("getOSUser: " + user.getOSUser());
		System.out.println("getLanguage: " + user.getLanguage());
		System.out.println("getUUID: " + user.getUUID());
		System.out.println("isCannotChangePassword: " + user.isCannotChangePassword());
		System.out.println("isOSAuthentication: " + user.isOSAuthentication());
	}
	
	@Test 
	public void listUserRoles() throws JIException {
		OCInfoBaseUsersManager manager = app.getInfoBaseUserManager();
		OCInfoBaseUser user = manager.getCurrentUser();
		user = manager.findByName("Алтухова");
		OCUserRoles roles = user.getRoles();
		System.out.println("Roles ============================");
		for (OCRoleMetadataObject metaObject : roles) {
			System.out.println(metaObject.getName() + ", " + metaObject.getFullName() + ", " + metaObject.getPresentation());
		}
	}
	
	@Test 
	public void listUsers() throws JIException {
		OCInfoBaseUsersManager manager = app.getInfoBaseUserManager();
		OCUserArray array = manager.getUsers();
		OCInfoBaseUser[] userArray = array.toArray();
		for (OCInfoBaseUser user : userArray) {
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void checkContains() throws JIException {
		OCMetadataRoleCollection allRoles = app.getMetadata().getRoles();
		OCInfoBaseUsersManager manager = app.getInfoBaseUserManager();
		OCInfoBaseUser user = manager.getCurrentUser();
		OCUserRoles userRoles = user.getRoles();
		System.out.println("---------------------------");
		System.out.println("User " + user.getFullName() + " has following roles:");
		for (OCRoleMetadataObject role : allRoles) {
			System.out.println(role.getName() + ": " + userRoles.contains(role));
		}
	}
}
