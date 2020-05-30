package com.hwp.admin.app.service.investmentArticle;

import com.hwp.common.model.investmentArticle.bean.InvestmentArticle;
import com.hwp.common.model.investmentArticle.dao.InvestmentArticleMapper;
import com.hwp.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 投资观点Service实现
 *
 * @author 李洪斌
 * @date 2019/7/18 16:39:02
 */
@Service
public class InvestmentArticleServiceImpl implements InvestmentArticleService {

    @Autowired
    private InvestmentArticleMapper investmentArticleMapper;

    /**
     * 添加投资观点
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 16:41
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addInvestmentArticle(InvestmentArticle investmentArticle) {
        // Id
        investmentArticle.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        investmentArticle.setDataStatus("1");
        investmentArticleMapper.addInvestmentArticle(investmentArticle);
    }

    /**
     * 获取投资观点
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 16:42
     * @Param
     **/
    @Override
    public InvestmentArticle getInvestmentArticleById(String id) {
        return investmentArticleMapper.selectInvestmentArticleById(id);
    }

    /**
     * 修改投资观点
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 16:43
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvestmentArticle(InvestmentArticle investmentArticle) {
        investmentArticleMapper.updateInvestmentArticleById(investmentArticle);
    }

    /**
     * 根据动态参数获取投资观点列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 17:18
     * @Param
     **/
    @Override
    public List<InvestmentArticle> listInvestmentArticleByParams(Map<String, Object> params) {
        return investmentArticleMapper.listInvestmentArticleByParams(params);
    }

    /**
     * 根据投资观点pojo获取投资观点列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 17:18
     * @Param
     **/
    @Override
    public List<InvestmentArticle> listInvestmentArticleByModel(InvestmentArticle investmentArticle) {
        // 获取时默认获取有效数据
        investmentArticle.setDataStatus("1");
        return investmentArticleMapper.selectInvestmentArticleList(investmentArticle);
    }
}
