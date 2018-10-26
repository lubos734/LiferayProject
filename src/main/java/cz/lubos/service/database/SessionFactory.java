package cz.lubos.service.database;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


/**
 * Singleton providing Mybatis SqlSessions for supported banks
 */
public class SessionFactory {
	
	private static final String DB_CONFIGS_FOLDER = "cz/lubos/database-config/";
	     
	private static final Log log = LogFactoryUtil.getLog(SessionFactory.class);
	
	/* POSMAN - write */
	private SqlSessionFactory postgreSessionFactory;
	
	private static SessionFactory instance = new SessionFactory();
	
	
	
	/**
	 * Constructor - create session factories for all banks
	 */
	private SessionFactory() {
		try {
			// postgre
			postgreSessionFactory = createFactory("postgre-database-config.xml");
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	/**
	 * Return session for accessing db POSTGRE
	 * @return new session
	 */
	public static SqlSession getSessionPostgre() {
		return instance.postgreSessionFactory.openSession();
	}
	
	/**
	 * Create SqlSessionFactory for one bank
	 * @param fileName configuration file name
	 * @return new session factory
	 * @throws IOException thrown when error occurs with configuration file
	 */
	private SqlSessionFactory createFactory(String fileName) throws IOException {
		return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(DB_CONFIGS_FOLDER+fileName));
	}
}
