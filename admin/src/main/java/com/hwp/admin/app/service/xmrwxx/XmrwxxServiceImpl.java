package com.hwp.admin.app.service.xmrwxx;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmrwxx.dao.XmrwxxMapper;
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
public class XmrwxxServiceImpl implements XmrwxxService {

    @Autowired
    private XmrwxxMapper xmrwxxMapper;
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
    public Xmrwxx addXmrwxx(Xmrwxx xmrwxx) {
        // Id
        xmrwxx.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        xmrwxx.setDataStatus(GlobalConstant.DATA_VALID);

        return xmrwxxMapper.addXmrwxx(xmrwxx);
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
    public Xmrwxx getXmrwxxById(String id) {
        return xmrwxxMapper.selectXmrwxxById(id);
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
    public void updateXmrwxx(Xmrwxx xmrwxx) {
        xmrwxxMapper.updateXmrwxxById(xmrwxx);
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
    public List<Xmrwxx> listXmrwxxByParams(Map<String, Object> params) {
        return xmrwxxMapper.listXmrwxxByParams(params);
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
    public List<Xmrwxx> listXmrwxxByModel(Xmrwxx xmrwxx) {
        // 获取时默认获取有效数据
        xmrwxx.setDataStatus(GlobalConstant.DATA_VALID);
        return xmrwxxMapper.selectXmrwxxList(xmrwxx);
    }
}
