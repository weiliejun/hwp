package com.hwp.common.model.tenderLoan.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.tenderLoan.bean.TenderLoan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenderLoanMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public TenderLoan addTenderLoan(TenderLoan record) {
        insert("tenderMapper.addTenderLoan", record);
        return record;
    }

    /**
     * @Description 根据Id查找投标放款交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public TenderLoan selectTenderLoanById(String id) {
        return (TenderLoan) queryForObject("tenderMapper.selectTenderLoanById", id);
    }

    /**
     * @Description 获取投标放款交易信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<TenderLoan> selectTenderLoanList(TenderLoan record) {
        return queryForList("tenderMapper.selectTenderLoanList", record);
    }

    /**
     * @Description 根据id修改投标放款交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateTenderLoanById(TenderLoan record) {
        return update("tenderMapper.updateTenderLoanById", record);
    }

    /**
     * @Description 修改投标放款交易信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateDataStatusById(TenderLoan record) {
        return update("tenderMapper.updateDataStatusById", record);
    }

}