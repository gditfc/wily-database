package io.csra.wily.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * An out-of-the-box configuration for working with a Mongo Database. Just extend this in your application and you'll
 * be able to inject the mongoDatabase into your Repository implementation and make calls to the mongo database
 * specified using mongo.uri and mongo.database in any properties file on your classpath/.
 *
 * @author ndimola
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
            public PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return null;
            }

        };
    }

}
