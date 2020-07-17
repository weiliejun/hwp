package com.hwp.common.model.cfcaTrustsignRecord.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.cfcaTrustsignRecord.bean.CfcaTrustsignRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CfcaTrustsignRecordMapper extends AbstractBaseDao {
    public CfcaTrustsignRecord addCfcaTrustSignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        insert("cfcaTrustSignRecord.insertCfcaTrustSignRecord", cfcaTrustSignRecord);
        return cfcaTrustSignRecord;
    }

    public void delCfcaTrustSignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        delete("cfcaTrustSignRecord.deleteByPrimaryKey", cfcaTrustSignRecord.getId());
    }

    public CfcaTrustsignRecord editCfcaTrustSignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        update("cfcaTrustSignRecord.updateCfcaTrustSignRecord", cfcaTrustSignRecord);
        return cfcaTrustSignRecord;
    }

    public CfcaTrustsignRecord findCfcaTrustSignRecordById(String id) {
        return (CfcaTrustsignRecord) queryForObject("cfcaTrustSignRecord.selectByPrimaryKey", id);
    }


    public List<CfcaTrustsignRecord> findCfcaTrustSignRecords(Map<String, Object> params, int rowStart, int rowEnd) {
        params.put("rowStart", rowStart);
        params.put("rowEnd", rowEnd);
        List<CfcaTrustsignRecord> results = queryForList("cfcaTrustSignRecord.findCfcaTrustSignRecords", params);
        return results;
    }

    public int findCfcaTrustSignRecordsTotalCount(Map<String, Object> params) {
        int totalCount = (Integer) queryForObject("cfcaTrustSignRecord.findCfcaTrustSignRecordsTotalCount", params);
        return totalCount;
    }
}