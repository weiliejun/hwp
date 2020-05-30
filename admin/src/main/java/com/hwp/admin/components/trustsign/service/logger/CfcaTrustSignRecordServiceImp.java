package com.hwp.admin.components.trustsign.service.logger;

import com.hwp.common.model.cfcaTrustsignRecord.bean.CfcaTrustsignRecord;
import com.hwp.common.model.cfcaTrustsignRecord.dao.CfcaTrustsignRecordMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cfcaTrustSignRecordService")
public class CfcaTrustSignRecordServiceImp implements CfcaTrustSignRecordService {

    private final Logger logger = Logger.getLogger(CfcaTrustSignRecordServiceImp.class);

    @Autowired
    private CfcaTrustsignRecordMapper cfcaTrustSignRecordRepository;

    @Override
    public CfcaTrustsignRecord addCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        return cfcaTrustSignRecordRepository.addCfcaTrustSignRecord(cfcaTrustSignRecord);
    }

    @Override
    public void delCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        cfcaTrustSignRecordRepository.delCfcaTrustSignRecord(cfcaTrustSignRecord);

    }

    @Override
    public CfcaTrustsignRecord editCfcaTrustsignRecord(CfcaTrustsignRecord cfcaTrustSignRecord) {
        return cfcaTrustSignRecordRepository.editCfcaTrustSignRecord(cfcaTrustSignRecord);
    }

    @Override
    public CfcaTrustsignRecord findCfcaTrustsignRecordById(String id) {
        return cfcaTrustSignRecordRepository.findCfcaTrustSignRecordById(id);
    }

    @Override
    public List<CfcaTrustsignRecord> findCfcaTrustsignRecords(Map<String, Object> params, int rowStart, int rowEnd) {
        return cfcaTrustSignRecordRepository.findCfcaTrustSignRecords(params, rowStart, rowEnd);
    }

    @Override
    public int findCfcaTrustsignRecordsTotalCount(Map<String, Object> params) {
        return cfcaTrustSignRecordRepository.findCfcaTrustSignRecordsTotalCount(params);
    }

}