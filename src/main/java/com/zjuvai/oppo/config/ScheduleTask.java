package com.zjuvai.oppo.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableScheduling
public class ScheduleTask {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(fixedRate = 100)
    public void mockPersonData() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i = 0; i < 10000; i++) {
            String sql = "insert into person select 'yxy" + i + "', 21, '" + dateFormat.format(date) + "'";
            logger.info("execute insert sql: " + sql);
            jdbcTemplate.execute(sql);
        }
    }
}
