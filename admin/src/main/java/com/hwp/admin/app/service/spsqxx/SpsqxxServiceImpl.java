package com.hwp.admin.app.service.spsqxx;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.spsqxx.bean.XmSpsqxx;
import com.hwp.common.model.spsqxx.dao.SpsqxxMapper;
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
public class SpsqxxServiceImpl implements SpsqxxService {

    @Autowired
    private SpsqxxMapper spsqxxMapper;
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
    public Spsqxx addSpsqxx(Spsqxx spsqxx) {
        // Id
        spsqxx.setId(RandomUtil.getSerialNumber());
        // 数据有效性 0 无效 1 有效
        spsqxx.setDataStatus(GlobalConstant.DATA_VALID);

        return spsqxxMapper.addSpsqxx(spsqxx);
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
    public Spsqxx getSpsqxxById(String id) {
        return spsqxxMapper.selectSpsqxxById(id);
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
    public void updateSpsqxx(Spsqxx spsqxx) {
        spsqxxMapper.updateSpsqxxById(spsqxx);
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
    public List<XmSpsqxx> listSpsqxxByParams(Map<String, Object> params) {
        return spsqxxMapper.listSpsqxxByParams(params);
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
    public List<XmSpsqxx> listSpsqxxByModel(Spsqxx spsqxx) {
        // 获取时默认获取有效数据
        spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
        return spsqxxMapper.selectSpsqxxList(spsqxx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvalidByGlId(String glId) {
        spsqxxMapper.updateInvalidByGlId(glId);
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
    public List<XmSpsqxx> listSpsqxxRwfhByParams(Map<String, Object> params) {
        return spsqxxMapper.listSpsqxxRwfhByParams(params);
    }

    @Override
    public List<XmSpsqxx> listSpsqxxRybgByParams(Map<String, Object> params) {
        return spsqxxMapper.listSpsqxxRybgByParams(params);
    }
}
