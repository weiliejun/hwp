package com.hwp.admin.app.service.rwczjl;

import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.rwczjl.bean.Rwczjl;
import com.hwp.common.model.rwczjl.dao.RwczjlMapper;
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
public class RwczjlServiceImpl implements RwczjlService {

    @Autowired
    private RwczjlMapper rwczjlMapper;

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
    public Rwczjl addRwczjl(Rwczjl rwczjl) {
        // Id
        rwczjl.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        rwczjl.setDataStatus(GlobalConstant.DATA_VALID);
        return rwczjlMapper.addRwczjl(rwczjl);
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
    public Rwczjl getRwczjlById(String id) {
        return rwczjlMapper.selectRwczjlById(id);
    }

    @Override
    public Rwczjl getRwczjlByName(String name) {
        return rwczjlMapper.selectRwczjlByName(name);
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
    public void updateRwczjl(Rwczjl rwczjl) {
        rwczjlMapper.updateRwczjlById(rwczjl);
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
    public List<Rwczjl> listRwczjlByParams(Map<String, Object> params) {
        return rwczjlMapper.listRwczjlByParams(params);
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
    public List<Rwczjl> listRwczjlByModel(Rwczjl rwczjl) {
        // 获取时默认获取有效数据
        rwczjl.setDataStatus(GlobalConstant.DATA_VALID);
        return rwczjlMapper.selectRwczjlList(rwczjl);
    }
}
