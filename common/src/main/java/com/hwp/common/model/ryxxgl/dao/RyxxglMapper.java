package com.hwp.common.model.ryxxgl.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.ryxxgl.bean.RyxxglSelect;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class RyxxglMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Ryxxgl addRyxxgl(Ryxxgl record) {
        insert("RyxxglMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Ryxxgl selectRyxxglById(String id) {
        return (Ryxxgl) queryForObject("RyxxglMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 获取人员信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Ryxxgl> selectRyxxglList(Ryxxgl record) {
        return queryForList("RyxxglMapper.selectRyxxglList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取人员列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Ryxxgl> listRyxxglByParams(Map<String, Object> params) {
        return queryForList("RyxxglMapper.listRyxxglByParams", params);
    }

    /**
     * @Description 根据id修改人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateRyxxglById(Ryxxgl record) {
        return update("RyxxglMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取人员列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<RyxxglSelect> selectList(Map<String, Object> params) {
        return queryForList("RyxxglMapper.selectList", params);
    }
}