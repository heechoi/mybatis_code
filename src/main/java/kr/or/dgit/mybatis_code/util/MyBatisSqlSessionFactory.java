package kr.or.dgit.mybatis_code.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSqlsessionfactory() { //getter에서 생성
		if(sqlSessionFactory==null) { //null이면 한번도 호출을 하지 않았다는 의미 
			try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml");){//같은 main폴더에 존재하는 것이므로 바로 접근이 가능하다.
				//자동으로 close 한다. 
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("mybatis 설정 확인 요망");
				throw new RuntimeException(e.getCause());//runtimeexception은 try-catch로 묶어도 되고 안해도 된다.
			}  
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession() { //sql문장을 인식하기 위해서 여는거
		return sqlSessionFactory.openSession();
	}
}
