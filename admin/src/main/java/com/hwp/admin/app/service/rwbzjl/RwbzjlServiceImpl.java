package com.hwp.admin.app.service.rwbzjl;

import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.rwbzjl.bean.Rwbzjl;
import com.hwp.common.model.rwbzjl.dao.RwbzjlMapper;
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
public class RwbzjlServiceImpl implements RwbzjlService {

    @Autowired
    private RwbzjlMapper rwbzjlMapper;

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
    public Rwbzjl addRwbzjl(Rwbzjl rwbzjl) {
        // Id
        rwbzjl.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        rwbzjl.setDataStatus(GlobalConstant.DATA_VALID);
        return rwbzjlMapper.addRwbzjl(rwbzjl);
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
    public Rwbzjl getRwbzjlById(String id) {
        return rwbzjlMapper.selectRwbzjlById(id);
    }

    @Override
    public Rwbzjl getRwbzjlByName(String name) {
        return rwbzjlMapper.selectRwbzjlByName(name);
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
    public void updateRwbzjl(Rwbzjl rwbzjl) {
        rwbzjlMapper.updateRwbzjlById(rwbzjl);
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
    public List<Rwbzjl> listRwbzjlByParams(Map<String, Object> params) {
        return rwbzjlMapper.listRwbzjlByParams(params);
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
    public List<Rwbzjl> listRwbzjlByModel(Rwbzjl rwbzjl) {
        // 获取时默认获取有效数据
        rwbzjl.setDataStatus(GlobalConstant.DATA_VALID);
        return rwbzjlMapper.selectRwbzjlList(rwbzjl);
    }
}
