package com.hwp.common.model.xmxxgl.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class XmxxglMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Xmxxgl addXmxxgl(Xmxxgl record) {
        insert("XmxxglMapper.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据Id查找项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public Xmxxgl selectXmxxglById(String id) {
        return (Xmxxgl) queryForObject("XmxxglMapper.selectByPrimaryKey", id);
    }

    /**
     * @Description 获取项目信息列表
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public List<Xmxxgl> selectXmxxglList(Xmxxgl record) {
        return queryForList("XmxxglMapper.selectXmxxglList", record);
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 根据动态参数获取投资观点列表
     * @Date 2020/5/15 15:04
     * @Param
     **/
    public List<Xmxxgl> listXmxxglByParams(Map<String, Object> params) {
        return queryForList("XmxxglMapper.listXmxxglByParams", params);
    }

    /**
     * @Description 根据id修改项目信息
     * @Author 魏列军
     * @UpdateDate 2020/5/15 10:12
     */
    public int updateXmxxglById(Xmxxgl record) {
        return update("XmxxglMapper.updateByPrimaryKeySelective", record);
    }

}