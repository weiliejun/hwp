package com.hwp.common.model.spsqxx.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.spsqxx.bean.XmSpsqxx;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SpsqxxMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Spsqxx addSpsqxx(Spsqxx record) {
        insert("SpsqxxMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Spsqxx selectSpsqxxById(String id) {
        return (Spsqxx) queryForObject("SpsqxxMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 获取项目信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<XmSpsqxx> selectSpsqxxList(Spsqxx record) {
        return queryForList("SpsqxxMapper.selectSpsqxxList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取审批信息列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<XmSpsqxx> listSpsqxxByParams(Map<String, Object> params) {
        return queryForList("SpsqxxMapper.listSpsqxxByParams", params);
    }

    /**
     * @Description 根据id修改项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateSpsqxxById(Spsqxx record) {
        return update("SpsqxxMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * @Description 根据id修改项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateInvalidByGlId(String glId) {
        return update("SpsqxxMapper.updateInvalidByGlId", glId);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取审批信息列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<XmSpsqxx> listSpsqxxRwfhByParams(Map<String, Object> params) {
        return queryForList("SpsqxxMapper.listSpsqxxRwfhByParams", params);
    }

    public List<XmSpsqxx> listSpsqxxRybgByParams(Map<String, Object> params) {
        return queryForList("SpsqxxMapper.listSpsqxxRybgByParams", params);
    }
}