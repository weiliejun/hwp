package com.hwp.admin.components.trustsign.service.logger;


import com.hwp.common.model.cfcaTrustsignRecord.bean.CfcaTrustsignRecord;

import java.util.List;
import java.util.Map;


public interface CfcaTrustSignRecordService {

    CfcaTrustsignRecord addCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord);

    void delCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord);

    CfcaTrustsignRecord editCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord);

    CfcaTrustsignRecord findCfcaTrustsignRecordById(String Id);

    List<CfcaTrustsignRecord> findCfcaTrustsignRecords(Map<String, Object> params, int rowStart, int rowEnd);

    int findCfcaTrustsignRecordsTotalCount(Map<String, Object> params);
}