package seung.spring.boot.conf.datasource;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;

@Slf4j
@Repository(value = "sMapperI")
public class SMapperI {

	@Resource(name = "sqlSessionTemplateI")
	private SqlSession sqlSession;
	
	public List<SLinkedHashMap> selectList(String statement) {
		log.debug("run");
		return sqlSession.selectList(statement);
	}
	
	public List<SLinkedHashMap> selectList(String statement, Object parameter) {
		log.debug("run");
		return sqlSession.selectList(statement, parameter);
	}
	
	public SLinkedHashMap selectOne(String statement) {
		log.debug("run");
		return sqlSession.selectOne(statement);
	}
	
	public SLinkedHashMap selectOne(String statement, Object parameter) {
		log.debug("run");
		return sqlSession.selectOne(statement, parameter);
	}
	
	public int insert(String statement) {
		log.debug("run");
		return sqlSession.insert(statement);
	}
	
	public int insert(String statement, Object parameter) {
		log.debug("run");
		return sqlSession.insert(statement, parameter);
	}
	
	public int update(String statement) {
		log.debug("run");
		return sqlSession.update(statement);
	}
	
	public int update(String statement, Object parameter) {
		log.debug("run");
		return sqlSession.update(statement, parameter);
	}
	
	public int delete(String statement) {
		log.debug("run");
		return sqlSession.delete(statement);
	}
	
	public int delete(String statement, Object parameter) {
		log.debug("run");
		return sqlSession.delete(statement, parameter);
	}
	
}
