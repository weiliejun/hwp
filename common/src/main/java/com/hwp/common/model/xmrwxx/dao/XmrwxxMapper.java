package com.hwp.common.model.xmrwxx.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class XmrwxxMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Xmrwxx addXmrwxx(Xmrwxx record) {
        insert("XmrwxxMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Xmrwxx selectXmrwxxById(String id) {
        return (Xmrwxx) queryForObject("XmrwxxMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 获取项目信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Xmrwxx> selectXmrwxxList(Xmrwxx record) {
        return queryForList("XmrwxxMapper.selectXmrwxxList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取投资观点列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Xmrwxx> listXmrwxxByParams(Map<String, Object> params) {
        return queryForList("XmrwxxMapper.listXmrwxxByParams", params);
    }

    /**
     * @Description 根据id修改项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateXmrwxxById(Xmrwxx record) {
        return update("XmrwxxMapper.updateByPrimaryKeySelective", record);
    }

}