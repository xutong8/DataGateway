package com.zjuvai.oppo.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableScheduling
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    private int loop_times = 200;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * Date date = new Date();
     * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     * for (int i = 0; i < 10000; i++) {
     * String sql = "insert into person select 'yxy" + i + "', 21, '" +
     * dateFormat.format(date) + "'";
     * logger.info("thread3 execute insert sql: " + sql);
     * jdbcTemplate.execute(sql);
     * }
     */
    @Scheduled(fixedRate = 500)
    public void mockPersonData() {
        int loop_times = this.loop_times;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sql = "insert into person ";
        for (int i = 0; i < loop_times; i++) {
            if (i < loop_times - 1) {
                sql += "select 'yxy" + i + "', 21, '" + dateFormat.format(date) + "' union all ";
            } else {
                sql += "select 'yxy" + i + "', 21, '" + dateFormat.format(date) + "'";
            }
        }
        // String sql = "insert into person select 'yxy',21,'2022-01-02' union all
        // select 'xt',19,'2022-01-03'";
        logger.info("execute insert sql: " + sql);
        jdbcTemplate.execute(sql);
    }
}
