package com.hwp.admin.app.service.ryxxgl;

import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.ryxxgl.bean.RyxxglSelect;
import com.hwp.common.model.ryxxgl.dao.RyxxglMapper;
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
public class RyxxglServiceImpl implements RyxxglService {

    @Autowired
    private RyxxglMapper ryxxglMapper;

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
    public Ryxxgl addRyxxgl(Ryxxgl ryxxgl) {
        // Id
        ryxxgl.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        ryxxgl.setDataStatus(GlobalConstant.DATA_VALID);
        return ryxxglMapper.addRyxxgl(ryxxgl);
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
    public Ryxxgl getRyxxglById(String id) {
        return ryxxglMapper.selectRyxxglById(id);
    }

    @Override
    public Ryxxgl getRyxxglByName(String name) {
        return ryxxglMapper.selectRyxxglByName(name);
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
    public void updateRyxxgl(Ryxxgl ryxxgl) {
        ryxxglMapper.updateRyxxglById(ryxxgl);
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
    public List<Ryxxgl> listRyxxglByParams(Map<String, Object> params) {
        return ryxxglMapper.listRyxxglByParams(params);
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
    public List<Ryxxgl> listRyxxglByModel(Ryxxgl ryxxgl) {
        // 获取时默认获取有效数据
        ryxxgl.setDataStatus(GlobalConstant.DATA_VALID);
        return ryxxglMapper.selectRyxxglList(ryxxgl);
    }

    @Override
    public List<RyxxglSelect> selectList(Map<String, Object> params) {
        return ryxxglMapper.selectList(params);
    }
}
