package com.hwp.common.model.investmentArticle.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.investmentArticle.bean.InvestmentArticle;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InvestmentArticleMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public InvestmentArticle addInvestmentArticle(InvestmentArticle record) {
        insert("InvestmentArticleMapper.addInvestmentArticle", record);
        return record;
    }

    /**
     * @Description 根据Id查找投资观点信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public InvestmentArticle selectInvestmentArticleById(String id) {
        return (InvestmentArticle) queryForObject("InvestmentArticleMapper.selectInvestmentArticleById", id);
    }

    /**
     * @Description 获取投资观点信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<InvestmentArticle> selectInvestmentArticleList(InvestmentArticle record) {
        return queryForList("InvestmentArticleMapper.selectInvestmentArticleList", record);
    }

    /**
     * @Description 根据id修改投资观点信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateInvestmentArticleById(InvestmentArticle record) {
        return update("InvestmentArticleMapper.updateInvestmentArticleById", record);
    }

    /**
     * @Description 修改投资观点信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateDataStatusById(InvestmentArticle record) {
        return update("InvestmentArticleMapper.updateDataStatusById", record);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取投资观点列表
     * @Date 2019/7/19 15:04
     * @Param
     **/
    public List<InvestmentArticle> listInvestmentArticleByParams(Map<String, Object> params) {
        return queryForList("InvestmentArticleMapper.listInvestmentArticleByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 按时间倒序获取上架的投资观点信息列表
     * @Date 2019/7/30 10:33
     * @Param
     **/
    public List<InvestmentArticle> listInvestmentArticleByPage(Map<String, Object> params) {
        return queryForList("InvestmentArticleMapper.listInvestmentArticleByPage", params);
    }
}