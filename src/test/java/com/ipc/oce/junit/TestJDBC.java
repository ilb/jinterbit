/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Konovalov
 * 
 */
public class TestJDBC {

	@Test
	public void wrongURLPref() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:och:dcom");
			assertTrue(con == null);
		} catch (Exception e) {
			
		} 
	}
	
	@Test
	public void wrongLoginPassword() {
		Connection con = null;
		try {
			con = DriverManager
			.getConnection(
					"jdbc:oce:dcom://192.168.10.142:Konovalov1@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
					"Култашев", "frkmgxh3");
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		} 
	}
	
	@Test
	public void connectionViaURLAndUserPassword() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
							"Култашев", "frkmgxh3");
			assertNotNull(con);
			assertFalse(con.isClosed());
			con.close();
			assertTrue(con.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Test
	public void connectionViaFullURL() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection("jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true;user=Култашев;password=frkmgxh3");
			assertNotNull(con);
			assertFalse(con.isClosed());
			con.close();
			assertTrue(con.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public void connectionViaFullURL2() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection("jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true;oce.1c.user=Култашев;oce.1c.password=frkmgxh3");
			assertNotNull(con);
			assertFalse(con.isClosed());
			con.close();
			assertTrue(con.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public void connectionViaProperties() {
		Properties conProperties = new Properties();
		conProperties.put("oce.host", "192.168.10.142");
		conProperties.put("oce.driver", "V81Driver");
		conProperties.put("oce.host.user", "Konovalov");
		conProperties.put("oce.host.password", "dlheu0");
		conProperties.put("oce.1c.dbpath",
				"C:\\Developer\\Temp\\Cognos\\Cognos");
		conProperties.put("user", "Култашев");
		conProperties.put("password", "frkmgxh3");

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oce:dcom", conProperties);
			assertNotNull(con);
			assertFalse(con.isClosed());
			con.close();
			assertTrue(con.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public void connectionViaProperties2() {
		Properties conProperties = new Properties();
		conProperties.put("oce.host", "192.168.10.142");
		conProperties.put("oce.driver", "V81Driver");
		conProperties.put("oce.host.user", "Konovalov");
		conProperties.put("oce.host.password", "dlheu0");
		conProperties.put("oce.1c.dbpath",
				"C:\\Developer\\Temp\\Cognos\\Cognos");
		conProperties.put("oce.1c.user", "Култашев");
		conProperties.put("oce.1c.password", "frkmgxh3");

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oce:dcom", conProperties);
			assertNotNull(con);
			assertFalse(con.isClosed());
			con.close();
			assertTrue(con.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void connectionCallMethods() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection("jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true;oce.1c.user=Култашев;oce.1c.password=frkmgxh3");
			
			con.clearWarnings();
			con.commit();
			con.rollback();
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public void simpleExecuteQuery() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
							"Култашев", "frkmgxh3");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Catalog.Валюты");
			rs.close();
			stat.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@Test
	public void simpleExecuteQueryAndIterate() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
							"Култашев", "frkmgxh3");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Catalog.Валюты");
			int counter = 0;
			while (rs.next()) {
				counter++;
			}
			assertTrue(counter > 0);
			rs.close();
			stat.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Test
	public void iterateAndCheckGetByNumberAndName() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
							"Култашев", "frkmgxh3");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT Code, Description FROM Catalog.Валюты");
			boolean getOK = rs.next();
			String code = rs.getString(1);
			String desc = rs.getString("Description");
			
			assertTrue(getOK);
			assertTrue(code!=null && code.length()>0);
			assertTrue(desc!=null && desc.length()>0);
			
			rs.close();
			stat.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void iterateAndCheckGetSimpleTypes() {
		Connection con = null;
		try {
			con = DriverManager
					.getConnection(
							"jdbc:oce:dcom://192.168.10.142:Konovalov@dlheu0;oce.1c.dbpath=C:\\Developer\\Temp\\Cognos\\Cognos;oce.driver=V81Driver;autoRegistration=true",
							"Култашев", "frkmgxh3");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT Комментарий, КратностьВзаиморасчетов, СуммаВключаетНДС, Date FROM Document.СчетНаОплатуПокупателю");
			boolean getOK = rs.next();
			assertTrue("Result is empty.",getOK);
			
			String address = rs.getString("Комментарий");
			int div = rs.getInt(2);
			boolean summIncNDS = rs.getBoolean("СуммаВключаетНДС");
			java.sql.Date date = rs.getDate(4);
			//assertTrue(date instanceof java.sql.Date);
			
			/*System.out.println("Комментарий = " + address);
			System.out.println("КратностьВзаиморасчетов = " + div);
			System.out.println("СуммаВключаетНДС = " + summIncNDS);
			System.out.println("Date = " + date);*/
			
			Object f1 = rs.getObject(1);
			Object f2 = rs.getObject("КратностьВзаиморасчетов");
			Object f3 = rs.getObject("СуммаВключаетНДС");
			Object f4 = rs.getObject("Date");
			
			assertTrue(f1 instanceof String);
			assertTrue(f2 instanceof Integer);
			assertTrue(f3 instanceof Integer); // because boolan not real sql type
			assertTrue(f4 instanceof java.sql.Date);
			
			f4 = rs.getObject(4);
			assertTrue(f4 instanceof java.sql.Date);
			
			rs.close();
			stat.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					assertTrue(con.isClosed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
