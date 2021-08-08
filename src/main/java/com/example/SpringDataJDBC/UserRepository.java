package com.example.SpringDataJDBC;

import com.example.SpringDataJDBC.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;


  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<User> findAll() {
//    Object list = jdbcTemplate.execute("select * from users");
    String sql="select * from users";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class) );
  }

  public Optional<User> findByUsername(String username) {
//    final Optional<User> empty = (Optional<User>) Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where id =  ?", new Object[]{username}, new BeanPropertyRowMapper(User.class)));
    return Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where username =  ?"
            ,new Object[] {username}
          , (rs, i) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("username"))));
  }

  public Optional<User> findById(Long id) {

      return Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where id =  ?"
          , new Object[]{id}
          , (rs, i) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("username"))));
  }

  public User update(User user) {
    return null;
  }

  public void deleteById(Long id) {
    jdbcTemplate.update("delete from users where id =  ?", id);
  }

}
