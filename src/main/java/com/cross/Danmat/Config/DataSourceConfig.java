	package com.cross.Danmat.Config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.cross.Danmat.Manager.Dao.ManagerDao;
import com.cross.Danmat.Manager.Service.ManagerServiceImpl;
import com.cross.Danmat.TwentyHills.Dao.TwentyHillsDao;
import com.cross.Danmat.TwentyHills.Service.TwentyHillsServiceImpl;
import com.cross.Danmat.User.Dao.UserDao;
import com.cross.Danmat.User.Service.UserServiceImpl;
import com.cross.Danmat.WordRelay.Dao.WordRelayDao;
import com.cross.Danmat.WordRelay.Service.WordRelayServiceImpl;
import com.cross.Danmat.board.dao.BoardDao;
import com.cross.Danmat.board.service.BoardServiceImpl;
import com.cross.Danmat.crossWord.dao.CrossDao;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;
import com.cross.Danmat.crossWord.service.MakeCW;

@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/danmat?serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull");
		ds.setUsername("danmat");
		ds.setPassword("danmat");
		ds.setInitialSize(2); 				//커넥션 풀 초기화시 생성할 초기 커넥션 갯수(기본값 10개)
		ds.setMaxActive(10);				//풀에서 가져올 수 있는 최대 커넥션 갯수(기본값 100개)
		ds.setMaxIdle(10);					//풀에 유지할 수 있는 최대 커넥션 수(기본값은 maxActive와 동일)
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDao(dataSource());
	}
	
	@Bean
	public UserServiceImpl userService() {
		return new UserServiceImpl(userDao());
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao(dataSource());
	}
	
	@Bean
	public BoardServiceImpl boardService() {
		return new BoardServiceImpl(boardDao()); 
	}
	
	@Bean
	public ManagerDao managerDao() {
		return new ManagerDao(dataSource());
	}
	
	@Bean
	public ManagerServiceImpl managerService() {
		return new ManagerServiceImpl(managerDao());
	}
	
	@Bean
	public TwentyHillsDao twentyHillsDao() {
		return new TwentyHillsDao(dataSource());
	}
	
	@Bean
	public TwentyHillsServiceImpl twentyHillsService() {
		return new TwentyHillsServiceImpl(twentyHillsDao());
	}
	
	@Bean
	public WordRelayDao wordRelayDao() {
		return new WordRelayDao(dataSource());
	}
	
	@Bean
	public WordRelayServiceImpl wordRelayService() {
		return new WordRelayServiceImpl(wordRelayDao());
	}
	
	@Bean
	public CrossDao crossDao() {
		return new CrossDao(dataSource());
	}
	
	@Bean
	public CrossServiceImpl crossServiceImpl() {
		return new CrossServiceImpl(crossDao());
	}
	
	@Bean
	public MakeCW makeCW() {
		return new MakeCW(crossServiceImpl());
	}
	
	
	
}
