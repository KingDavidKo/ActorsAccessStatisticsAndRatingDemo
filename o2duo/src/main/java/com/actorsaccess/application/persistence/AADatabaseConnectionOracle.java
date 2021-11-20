package com.actorsaccess.application.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.actorsaccess.application.config.AAReadFromConfigFile;

import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class AADatabaseConnectionOracle {
	PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
	final java.util.Properties prop = new java.util.Properties();
	Connection conn = null;
	
	public void setConnection(String url, String user, String password) throws SQLException {
		pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		pds.setURL(url);
		pds.setUser(user);
		pds.setPassword(password);

		pds.setInitialPoolSize(10);
		pds.setMinPoolSize(20);
		pds.setMaxPoolSize(50);
		pds.setPropertyCycle(20);
		pds.setMaxIdleTime(300);

		conn = pds.getConnection();
		System.out.println("\nConnection obtained from UniversalConnectionPool\n");
	}
	
	public Connection getDBConnection() {
		if(conn == null) {
			try {
				HashMap<String, String> conf = AAReadFromConfigFile.getConfig();
				
				setConnection(conf.get("DBDriverURL"), conf.get("DBSchemaUser"), conf.get("DBSchemaPassword"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
}
