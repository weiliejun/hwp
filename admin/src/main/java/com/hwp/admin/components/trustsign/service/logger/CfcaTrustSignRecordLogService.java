package com.hwp.admin.components.trustsign.service.logger;

import com.hwp.common.model.cfcaTrustsignRecord.bean.CfcaTrustsignRecord;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author 张可乐
 * @version 1.0
 * @description 调用安心签接口记录
 * @update 2017-3-17 下午4:41:45
 */
@Aspect
@Component
public class CfcaTrustSignRecordLogService {
    private final Logger logger = Logger.getLogger(CfcaTrustSignRecordServiceImp.class);
    @Autowired
    private CfcaTrustSignRecordService cfcaTrustSignRecordService;

    @Pointcut("execution (* com.hwp.admin.components.trustsign.service.TrustSignService.*(..))")
    public void pointCut() {
    }

    public void doAfter(JoinPoint jp) throws ClassNotFoundException, NotFoundException {
        logger.info("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    /**
     * @param pjp
     * @return
     * @throws Throwable
     * @description 保存调用安心签接口的参数
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-17 下午4:44:15
     */
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (!ArrayUtils.isEmpty(args)) {
            String txCode = (String) args[0];
            String url = (String) args[1];
            String req = (String) args[2];
            String signature = (String) args[3];
            CfcaTrustsignRecord cfcaTrustSignRecord = new CfcaTrustsignRecord();
            if (req != null && StringHelper.length(req) > 2000) {
                cfcaTrustSignRecord.setReq(req.substring(0, 2000));
            } else {
                cfcaTrustSignRecord.setReq(req);
            }
            cfcaTrustSignRecord.setTxCode(txCode);

            if (signature != null && StringHelper.length(signature) > 2000) {
                cfcaTrustSignRecord.setSignatrue(signature.substring(0, 2000));
            } else {
                cfcaTrustSignRecord.setSignatrue(signature);
            }
            cfcaTrustSignRecord.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            cfcaTrustSignRecord.setDataStatus("valid");
            // 保存安心签调用记录
            CfcaTrustsignRecord cfcaTrustSignRecord2 = cfcaTrustSignRecordService.addCfcaTrustsignRecord(cfcaTrustSignRecord);
            Object retVal = pjp.proceed(args);
            CfcaTrustsignRecord temp = new CfcaTrustsignRecord();
            // 保存安心签调用返回结果json字符串
            temp.setId(cfcaTrustSignRecord2.getId());

            if (retVal != null && StringHelper.length(retVal.toString()) > 2000) {
                temp.setRes((String) retVal.toString().substring(0, 2000));
            } else {
                temp.setRes((String) retVal);
            }

            cfcaTrustSignRecordService.editCfcaTrustsignRecord(temp);
            return retVal;
        }
        return null;
    }

    public void doBefore(JoinPoint jp) {
        logger.info("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void doThrowing(JoinPoint jp, Throwable e) {
        String message = "invoke method: " + jp.getTarget().getClass().getName() +
                "." + jp.getSignature().getName() + " throw exception";
        logger.error(message, e);
    }
}