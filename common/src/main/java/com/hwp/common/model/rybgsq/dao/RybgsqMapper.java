package com.hwp.common.model.rybgsq.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.rybgsq.bean.Rybgsq;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RybgsqMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rybgsq addRybgsq(Rybgsq record) {
        insert("RybgsqMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找人员变更申请
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rybgsq selectRybgsqById(String id) {
        return (Rybgsq) queryForObject("RybgsqMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 根据name查找人员变更申请
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rybgsq selectRybgsqByName(String name) {
        return (Rybgsq) queryForObject("RybgsqMapper.selectRybgsqByName", name);
    }

    /**
     * @Description 获取人员变更申请列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Rybgsq> selectRybgsqList(Rybgsq record) {
        return queryForList("RybgsqMapper.selectRybgsqList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取人员列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Rybgsq> listRybgsqByParams(Map<String, Object> params) {
        return queryForList("RybgsqMapper.listRybgsqByParams", params);
    }

    /**
     * @Description 根据id修改人员变更申请
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateRybgsqById(Rybgsq record) {
        return update("RybgsqMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * @Description 根据ryId查找人员变更申请
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rybgsq selectRybgsqByRyId(String id) {
        return (Rybgsq) queryForObject("RybgsqMapper.selectByRyId", id);
    }
}