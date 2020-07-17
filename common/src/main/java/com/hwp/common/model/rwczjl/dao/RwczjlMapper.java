package com.hwp.common.model.rwczjl.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.rwczjl.bean.Rwczjl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class RwczjlMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwczjl addRwczjl(Rwczjl record) {
        insert("RwczjlMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwczjl selectRwczjlById(String id) {
        return (Rwczjl) queryForObject("RwczjlMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 根据name查找人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwczjl selectRwczjlByName(String name) {
        return (Rwczjl) queryForObject("RwczjlMapper.selectRwczjlByName", name);
    }

    /**
     * @Description 获取人员信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Rwczjl> selectRwczjlList(Rwczjl record) {
        return queryForList("RwczjlMapper.selectRwczjlList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取人员列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Rwczjl> listRwczjlByParams(Map<String, Object> params) {
        return queryForList("RwczjlMapper.listRwczjlByParams", params);
    }

    /**
     * @Description 根据id修改人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateRwczjlById(Rwczjl record) {
        return update("RwczjlMapper.updateByPrimaryKeySelective", record);
    }

}