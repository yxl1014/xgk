package com.easy.archiecture.dao;

import com.easy.archiecture.entity.City;
import com.easy.archiecture.entity.Dev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insert(Dev dev) {
        String sql = "insert into devs(name,temperature,city_name) values(?,?,?)";
        int ok = jdbcTemplate.update(sql, dev.getName(), dev.getTemperature(), dev.getCity_name());
        return ok == 1;
    }

    public Dev findById(int id) {
        String sql = "select * from devs where id=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Dev.class), id);
    }

    public int selectByName(String name) {
        String sql = "select count(1) from devs where name=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name);
    }

    public int findIdByName(String name) {
        String sql = "select id from devs where name=?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt("id");
            }
        }, name);
    }

    public void deletebyid(int id) {
        String sql = "delete from devs where id=?";
        jdbcTemplate.update(sql, id);
    }

    public void replace(int id, float temp) {
        String sql = "update devs set temperature=? where id=?";
        jdbcTemplate.update(sql, temp, id);
    }

    public void setCitycodeByid(String citycode, int id) {
        String sql = "update devs set city_code=? where id=?";
        jdbcTemplate.update(sql, citycode, id);
    }


    public Dev getById(int id) {
        String sql = "select * from devs where id=?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Dev>() {
            @Override
            public Dev extractData(ResultSet rs) throws SQLException, DataAccessException {
                Dev dev = new Dev();
                while (rs.next()) {
                    dev.setId(rs.getInt("id"));
                    dev.setName(rs.getString("name"));
                    dev.setTemperature(rs.getFloat("temperature"));
                    dev.setCity_code(rs.getString("city_code"));
                    dev.setCity_name(rs.getString("city_name"));
                }
                return dev;
            }
        }, id);
    }

    public List<String> getAll() {
        String sql = "select * from devs";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<String> list = new ArrayList<>();
                while (rs.next()) {
                    Dev dev = new Dev();
                    dev.setId(rs.getInt("id"));
                    dev.setName(rs.getString("name"));
                    dev.setTemperature(rs.getFloat("temperature"));
                    dev.setCity_code(rs.getString("city_code"));
                    dev.setCity_name(rs.getString("city_name"));
                    list.add(dev.toString());
                }
                return list;
            }
        });
    }
}
