package de.openflorian.data.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Abstract class providing functions to obtain the datasource from the application server jndi context.
 * 
 * @author ceth
 */
@Component
public abstract class DatabaseConnector {

	private static final Logger log = LoggerFactory.getLogger(DatabaseConnector.class);

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/**
	 * Obtain jdbc datasource
	 * 
	 * @return
	 */
	protected static DataSource getDataSource() {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			return (DataSource) ctx.lookup("java:comp/env/jdbc/openflorian");
		}
		catch (NamingException e) {
			log.error(e.getMessage(), e);
			throw new IllegalStateException("Openflorian DataSource not found!", e);
		}
	}

}
