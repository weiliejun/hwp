package com.hwp.common.util;

import com.hwp.common.model.userTransaction.bean.UserTransaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 账单中心按月分组
 * 构建按条获取后按指定时间进行分组list数据
 *
 * @author 李洪斌
 * @date 2019/8/14 14:37
 */
public class BillUtil {


    /**
     * 构建按条获取后按指定时间进行分组list数据
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/8/14 14:39
     * @Param list调用分页获取list
     **/
    public static List<Map<String, Object>> toDataList(final List<UserTransaction> list) {

        // 定义返回List类型
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

        // 首次遍历获取List中月份存在在linkedHashSet中
        Set<Date> dateSet = new LinkedHashSet<Date>(16);
        // 定义Date格式只取年月
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        // 返回数据的月份时间格式
        DateFormat returnDateFormat = new SimpleDateFormat("yyyy年MM月");

        try {
            // 获取月份使用Set对月份时间进行去重
            for (UserTransaction transaction : list) {
                String dateStr = transaction.getCreateTime();

                Date monthDate = dateFormat.parse(dateStr);
                dateSet.add(monthDate);
            }

            // 没有获取到数据返回null
            if (dateSet.size() == 0) {
                return null;
            }

            // 遍历Set中的月份 根据月份取出对应的list
            for (Date date : dateSet) {
                // 定义小项Map key1为月份 key2为data value2为这个date月份对应的list
                Map<String, Object> listMap = new LinkedHashMap<String, Object>(16);

                listMap.put("month", returnDateFormat.format(date));

                List<UserTransaction> transactionList = new ArrayList<UserTransaction>();
                // 筛选符合的数据
                Iterator<UserTransaction> transactionIterator = list.iterator();

                while (transactionIterator.hasNext()) {
                    UserTransaction transaction = transactionIterator.next();
                    Date transactionData = dateFormat.parse(transaction.getCreateTime());
                    if (date.equals(transactionData)) {
                        // 处理返回的时间格式为 2019.09.10 14:22:00
                        transaction.setCreateTime(transaction.getCreateTime().replaceAll("-", "."));
                        transactionList.add(transaction);
                        // 移除掉匹配的数据是为了在下次循环中解析时间格式不会出问题
                        transactionIterator.remove();
                    }
                }
                listMap.put("data", transactionList);
                returnList.add(listMap);
            }

            return returnList;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
}
