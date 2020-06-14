package com.hwp.admin.app.service.xmxxgl;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.model.xmxxgl.dao.XmxxglMapper;
import com.hwp.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 项目信息管理Service实现
 *
 * @author 魏列军
 * @date 2020/5/15 16:39:02
 */
@Service
public class XmxxglServiceImpl implements XmxxglService {

    @Autowired
    private XmxxglMapper xmxxglMapper;
    @Autowired
    private MailSenderService mailSenderService;

    /**
     * 添加项目信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:41
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Xmxxgl addXmxxgl(Xmxxgl xmxxgl) {
        // Id
        xmxxgl.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        xmxxgl.setDataStatus(GlobalConstant.DATA_VALID);

        return xmxxglMapper.addXmxxgl(xmxxgl);
    }

    /**
     * 获取项目信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:42
     * @Param
     **/
    @Override
    public Xmxxgl getXmxxglById(String id) {
        return xmxxglMapper.selectXmxxglById(id);
    }

    /**
     * 修改项目信息管理
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 16:43
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXmxxgl(Xmxxgl xmxxgl) {
        xmxxglMapper.updateXmxxglById(xmxxgl);
    }

    /**
     * 根据动态参数获取项目信息管理列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:18
     * @Param
     **/
    @Override
    public List<Xmxxgl> listXmxxglByParams(Map<String, Object> params) {
        return xmxxglMapper.listXmxxglByParams(params);
    }

    /**
     * 根据项目信息管理pojo获取项目信息管理列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:18
     * @Param
     **/
    @Override
    public List<Xmxxgl> listXmxxglByModel(Xmxxgl xmxxgl) {
        // 获取时默认获取有效数据
        xmxxgl.setDataStatus(GlobalConstant.DATA_VALID);
        return xmxxglMapper.selectXmxxglList(xmxxgl);
    }
}
