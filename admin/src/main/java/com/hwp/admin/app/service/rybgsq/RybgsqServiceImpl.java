package com.hwp.admin.app.service.rybgsq;

import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.rybgsq.bean.Rybgsq;
import com.hwp.common.model.rybgsq.dao.RybgsqMapper;
import com.hwp.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 人员信息管理Service实现
 *
 * @author 魏列军
 * @date 2020/5/15 16:39:02
 */
@Service
public class RybgsqServiceImpl implements RybgsqService {

    @Autowired
    private RybgsqMapper rybgsqMapper;

    /**
     * 添加人员信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:41
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Rybgsq addRybgsq(Rybgsq rybgsq) {
        // Id
        rybgsq.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        rybgsq.setDataStatus(GlobalConstant.DATA_VALID);
        return rybgsqMapper.addRybgsq(rybgsq);
    }

    /**
     * 获取人员信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:42
     * @Param
     **/
    @Override
    public Rybgsq getRybgsqById(String id) {
        return rybgsqMapper.selectRybgsqById(id);
    }

    @Override
    public Rybgsq getRybgsqByName(String name) {
        return rybgsqMapper.selectRybgsqByName(name);
    }

    /**
     * 修改人员信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:43
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRybgsq(Rybgsq rybgsq) {
        rybgsqMapper.updateRybgsqById(rybgsq);
    }

    /**
     * 根据动态参数获取人员信息管理列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:18
     * @Param
     **/
    @Override
    public List<Rybgsq> listRybgsqByParams(Map<String, Object> params) {
        return rybgsqMapper.listRybgsqByParams(params);
    }

    /**
     * 根据人员信息管理pojo获取人员信息管理列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:18
     * @Param
     **/
    @Override
    public List<Rybgsq> listRybgsqByModel(Rybgsq rybgsq) {
        // 获取时默认获取有效数据
        rybgsq.setDataStatus(GlobalConstant.DATA_VALID);
        return rybgsqMapper.selectRybgsqList(rybgsq);
    }

    @Override
    public Rybgsq selectRybgsqByRyId(String id) {
        return rybgsqMapper.selectRybgsqByRyId(id);
    }
}
