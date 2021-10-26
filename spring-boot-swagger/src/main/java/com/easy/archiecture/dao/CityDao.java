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
public class CityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void flushCitys() {
        String sql = "delete from citys";
        jdbcTemplate.update(sql);
    }

    public void insertCity(String city_code, String city_name) {
        String sql = "insert into citys(city_name,city_code) values(?,?)";
        jdbcTemplate.update(sql, city_code, city_name);
    }

    public List<City> findCodeByName(String city_name) {
        String sql = "select * from citys where city_name=?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<City>>() {
            @Override
            public List<City> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<City> list = new ArrayList<>();
                while (rs.next()) {
                    City c = new City();
                    if (rs.getString("city_code") == null || rs.getString("city_code").length() != 9)
                        continue;
                    c.setCity_name(rs.getString("city_name"));
                    c.setCity_code(rs.getString("city_code"));
                    list.add(c);
                }
                return list;
            }
        }, city_name);
    }
}
