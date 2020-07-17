package com.hwp.common.model.cfcaContractnoRecord.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.cfcaContractnoRecord.bean.CfcaContractnoRecord;
import com.hwp.common.util.DateHelper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CfcaContractnoRecordMapper extends AbstractBaseDao {
    public CfcaContractnoRecord addCfcaContractnoRecord(CfcaContractnoRecord cfcaContractnoRecord) {
        insert("cfcaContractnoRecord.insertCfcaContractnoRecord", cfcaContractnoRecord);
        return cfcaContractnoRecord;
    }

    public CfcaContractnoRecord editCfcaContractnoRecord(CfcaContractnoRecord cfcaContractnoRecord) {
        update("cfcaContractnoRecord.updateCfcaContractnoRecord", cfcaContractnoRecord);
        return cfcaContractnoRecord;
    }

    public CfcaContractnoRecord addOrUpdateCfcaContractnoRecord(CfcaContractnoRecord cfcaContractnoRecord) {
        // 原有数据
        CfcaContractnoRecord cfcaContractnoRecordOld = findCfcaContractnoRecordBybusinessIdAndBusinessType(cfcaContractnoRecord.getBusinessId(), cfcaContractnoRecord.getBusinessType());
        if (cfcaContractnoRecordOld == null) {// 未签
            cfcaContractnoRecord.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            cfcaContractnoRecord.setDataStatus("1");
            addCfcaContractnoRecord(cfcaContractnoRecord);
        } else {
            cfcaContractnoRecord.setId(cfcaContractnoRecordOld.getId());
            cfcaContractnoRecord.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            editCfcaContractnoRecord(cfcaContractnoRecord);
        }
        return cfcaContractnoRecord;
    }

    public CfcaContractnoRecord findCfcaContractnoRecordBybusinessIdAndBusinessType(String businessId, String businessType) {
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("businessId", businessId);
        params.put("businessType", businessType);
        return (CfcaContractnoRecord) queryForObject("cfcaContractnoRecord.findCfcaContractnoRecordByParams", params);
    }

    public int findCfcaContractnoRecordsTotalCount(String businessId, String businessType) {
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("businessId", businessId);
        params.put("businessType", businessType);
        int totalCount = (Integer) queryForObject("cfcaContractnoRecord.findCfcaContractnoRecordCount", params);
        return totalCount;
    }

    public Map findProductCFCARecordsCount(String productId) {
        return (Map) queryForObject("cfcaContractnoRecord.findProductCFCARecordsCount", productId);
    }
}