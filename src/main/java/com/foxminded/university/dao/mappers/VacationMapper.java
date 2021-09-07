package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VacationMapper implements RowMapper<Vacation> {
    private final static String ID = "id";
    private final static String TIME_PERIOD = "time_period_id";
    private final static String DESCRIPTION = "description";
    private final static String MAPPER_EXCEPTION = "Exception in MapperClass";
    private TimePeriodDao timePeriodDao;

    @Autowired
    public VacationMapper(TimePeriodDao timePeriodDao) {
        this.timePeriodDao = timePeriodDao;
    }

    public Vacation mapRow(ResultSet resultSet, int i) {
        try {
            Vacation vacation = new Vacation();
            vacation.setId(resultSet.getLong(ID));
            vacation.setTimePeriod(timePeriodDao.getById(resultSet.getLong(TIME_PERIOD)));
            vacation.setDescription(resultSet.getString(DESCRIPTION));
            return vacation;
        } catch (SQLException e) {
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
