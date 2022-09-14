package io.csra.wily.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * An out-of-the-box configuration for working with a Mongo Database. Just extend this in your application and you'll
 * be able to inject the mongoDatabase into your Repository implementation and make calls to the mongo database
 * specified using mongo.uri and mongo.database in any properties file on your classpath/.
 *
 * @author ndimola
 *
 */
public class MongoDatabaseConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public MongoClient mongoClient() {
		String mongoUri = environment.getRequiredProperty("mongo.uri");

		if (StringUtils.isNotBlank(mongoUri)) {
			return new MongoClient(new MongoClientURI(mongoUri));
		}

		return null;
	}

	@Bean
	public MongoDatabase mongoDatabase(MongoClient mongoClient) {
		String mongoDatabase = environment.getRequiredProperty("mongo.database");

		if (StringUtils.isNotBlank(mongoDatabase)) {
			return mongoClient.getDatabase(mongoDatabase);
		}

		return null;
	}

	@Bean
	public DataSource dataSource() {
		return new DataSource() {

			@Override
			public PrintWriter getLogWriter() {
				return null;
			}

			@Override
			public void setLogWriter(PrintWriter out) {
				// Default behavior for this method is a no-op.
			}

			@Override
			public void setLoginTimeout(int seconds) {
				// Default behavior for this method is a no-op.
			}

			@Override
			public int getLoginTimeout() {
				return 0;
			}

			@Override
			public Logger getParentLogger() {
				return null;
			}

			@Override
			public <T> T unwrap(Class<T> iface) {
				return null;
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) {
				return false;
			}

			@Override
			public Connection getConnection() {
				return null;
			}

			@Override
			public Connection getConnection(String username, String password) {
				return null;
			}

		};
	}

}
