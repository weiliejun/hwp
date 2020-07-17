package com.hwp.common.model.rwbzjl.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.rwbzjl.bean.Rwbzjl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RwbzjlMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwbzjl addRwbzjl(Rwbzjl record) {
        insert("RwbzjlMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwbzjl selectRwbzjlById(String id) {
        return (Rwbzjl) queryForObject("RwbzjlMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 根据name查找人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Rwbzjl selectRwbzjlByName(String name) {
        return (Rwbzjl) queryForObject("RwbzjlMapper.selectRwbzjlByName", name);
    }

    /**
     * @Description 获取人员信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Rwbzjl> selectRwbzjlList(Rwbzjl record) {
        return queryForList("RwbzjlMapper.selectRwbzjlList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取人员列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Rwbzjl> listRwbzjlByParams(Map<String, Object> params) {
        return queryForList("RwbzjlMapper.listRwbzjlByParams", params);
    }

    /**
     * @Description 根据id修改人员信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateRwbzjlById(Rwbzjl record) {
        return update("RwbzjlMapper.updateByPrimaryKeySelective", record);
    }

}