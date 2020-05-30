package com.hwp.admin.quarz;

import com.hwp.admin.app.service.dateInfo.DateInfoService;
import com.hwp.common.util.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 自动添加 当前年的日期信息 xsp 2017-06-20
 */
@Component(value = "autoAddDateInfo")
public class AutoAddDateInfo {

    @Autowired
    private DateInfoService dateInfoService;

    // 每年1月1日0点执行
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void execute() throws Exception {
        Map map = new HashMap();
        // 获取当前年的节假日
        List<Map<String, String>> list = Holiday.getWorkDays();
        map.put("dateInfoList", list);
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        // 先将当前年份的数据删除
        dateInfoService.deleteDateInfoByYear(String.valueOf(year));
        dateInfoService.addMoreDateInfo(map);
    }
}
