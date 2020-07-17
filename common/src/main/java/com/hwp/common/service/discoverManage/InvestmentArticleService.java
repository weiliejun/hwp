package com.hwp.common.service.discoverManage;

import com.github.pagehelper.PageInfo;
import com.hwp.common.model.investmentArticle.bean.InvestmentArticle;

import java.util.List;
import java.util.Map;

public interface InvestmentArticleService {
    void addInvestmentArticle(InvestmentArticle investmentArticle);

    InvestmentArticle getInvestmentArticleById(String id);

    void updateInvestmentArticle(InvestmentArticle investmentArticle);

    List<InvestmentArticle> listInvestmentArticleByParams(Map<String, Object> params);

    List<InvestmentArticle> listInvestmentArticleByModel(InvestmentArticle investmentArticle);

    PageInfo<InvestmentArticle> listInvestmentArticleByPage(Map<String, Object> params);

}
