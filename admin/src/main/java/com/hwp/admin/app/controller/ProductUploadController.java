package com.hwp.admin.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwp.admin.app.service.product.ProductService;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.util.FileHelper;
import com.hwp.common.util.ImageHelper;
import com.hwp.common.util.RandomUtil;
import com.hwp.common.util.StringHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 附件，app配置图片上传
 * @Author lvjian
 * @UpdateDate 2019/7/30 14:11
 */
@Controller
public class ProductUploadController extends AbstractBaseController {

    private final Logger logger = Logger.getLogger(ProductUploadController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private FileSynchronizer fileSynchronizer;

    @Autowired
    private ProductOtherAttachFileService productOtherAttachFileService;

    private static boolean deleteDir(File dir) {
        if (dir.isFile()) {
            if (dir.isDirectory()) {
                String[] children = dir.list();// 递归删除目录中的子目录下
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * @Description 并将文件上传到网络端
     * @Author lvjian
     * @UpdateDate 2019/6/19 16:19
     */
    @RequestMapping("/fujian")
    public String accountRealAuth(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/6附件";
    }

    /**
     * 异步校验--产品相关信息是否重复
     *
     * @Author lvjian
     * @Date 2019/9/19 15:15
     **/
    @PostMapping(value = "/jiaoYanRename")
    @ResponseBody
    public Map<String, Object> listUserAndFundAndTransaction(Product product, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //只做重名校验
//        if (StringUtils.isBlank(product.getName())
//                ||StringUtils.isBlank(product.getGeName())
//                ||StringUtils.isBlank(product.getGeCode())
//                ||StringUtils.isBlank(product.getCode())
//                ||StringUtils.isBlank(product.getContractNo())){
//            if (StringUtils.isBlank(product.getName())){
//                resultMap.put("errName", "name");
//            }
//            if (StringUtils.isBlank(product.getGeName())){
//                resultMap.put("errGeName", "geName");
//            }
//            if (StringUtils.isBlank(product.getGeCode())){
//                resultMap.put("errGeCode", "geCode");
//            }
//            if (StringUtils.isBlank(product.getCode())){
//                resultMap.put("errCode", "code");
//            }
//            if (StringUtils.isBlank(product.getContractNo())){
//                resultMap.put("errContractNo", "contractNo");
//            }
//            resultMap.put("flag", "false");
//            resultMap.put("res", "notFind");
//            resultMap.put("msg", "产品编号,姓名,金交所编号,名称,合同编号不能为空");
//            return resultMap;
//        }
        //查看是否重复------------------------------------
        if (product.getName() != null && product.getName().length() > 0) {
            Product product1 = new Product();
            product1.setName(product.getName());
            List<Product> products = productService.selectProductListByRePeat(product1);
            if (products != null && products.size() > 0) {
                resultMap.put("flag", "false");
                resultMap.put("res", "reName");
                resultMap.put("msg", "产品名称重复");
                return resultMap;
            }
        }
        if (product.getCode() != null && product.getCode().length() > 0) {
            Product product1 = new Product();
            product1.setCode(product.getCode());
            List<Product> products = productService.selectProductListByRePeat(product1);
            if (products != null && products.size() > 0) {
                resultMap.put("flag", "false");
                resultMap.put("res", "reCode");
                resultMap.put("msg", "产品编号重复");
                return resultMap;
            }
        }
        if (product.getGeName() != null && product.getGeName().length() > 0) {
            Product product1 = new Product();
            product1.setGeName(product.getGeName());
            List<Product> products = productService.selectProductListByRePeat(product1);
            if (products != null && products.size() > 0) {
                resultMap.put("flag", "false");
                resultMap.put("res", "reGeName");
                resultMap.put("msg", "金交所产品名称重复");
                return resultMap;
            }
        }
        if (product.getGeCode() != null && product.getGeCode().length() > 0) {
            Product product1 = new Product();
            product1.setGeCode(product.getGeCode());
            List<Product> products = productService.selectProductListByRePeat(product1);
            if (products != null && products.size() > 0) {
                resultMap.put("flag", "false");
                resultMap.put("res", "reGeCode");
                resultMap.put("msg", "金交所产品编号重复");
                return resultMap;
            }
        }
        if (product.getContractNo() != null && product.getContractNo().length() > 0) {
            Product product1 = new Product();
            product1.setContractNo(product.getContractNo());
            List<Product> products = productService.selectProductListByRePeat(product1);
            if (products != null && products.size() > 0) {
                resultMap.put("flag", "false");
                resultMap.put("res", "reContractNo");
                resultMap.put("msg", "产品合同号重复");
                return resultMap;
            }
        }
        resultMap.put("flag", "true");
        resultMap.put("msg", "没毛病");
        return resultMap;
    }

    /**
     * @param request
     * @param model
     * @return
     * @throws IOException
     * @description 多文件上传
     * @version v1.0
     * @author 吕剑
     * @update 2019年6月24日 上午14:56:50
     */
    @RequestMapping("/fuJian/upload")
    public @ResponseBody
    Map<String, Object> uploadFuJ(HttpServletRequest request, Model model)
            throws IOException {
        Map<String, Object> map = new HashMap<>();

        String realPath = request.getSession().getServletContext()
                .getRealPath(File.separator);

        //拿到文件集合 并对文件进行处理
        Map<String, MultipartFile> formMultipartFile = getFormMultipartFile(request);
        if (formMultipartFile == null) {
            map.put("code", "false");
            map.put("msg", "不能上传空的文件");
        }
        //将每次文件的相关数据都存入jsonArray
        JSONArray jsonAry = null;
        for (Map.Entry<String, MultipartFile> entry : formMultipartFile.entrySet()) {
            MultipartFile uploadFile = entry.getValue();
            String fileName = FileHelper.disposeFileName(uploadFile
                    .getOriginalFilename());
            String extension = StringHelper.unqualify(fileName).toLowerCase();
            int type = ImageHelper.IMAGE_UNKNOWN;
            if (uploadFile.getSize() > 1048576) {
                map.put("code", "big");
                map.put("tmpFileName", fileName);
            } else if (extension.equals("jpg") || extension.equals("jpeg")
                    || extension.equals("gif") || extension.equals("png")
                    || "doc".equals(extension) ||
                    "docx".equals(extension) ||
                    "pdf".equals(extension) ||
                    "xls".equals(extension) ||
                    "xlsx".equals(extension) ||
                    "ppt".equals(extension) ||
                    "pptx".equals(extension)) {

                if (extension.equals("jpg") || extension.equals("jpeg")) {
                    type = ImageHelper.IMAGE_JPEG;
                } else if (extension.equals("gif") || extension.equals("png")) {
                    type = ImageHelper.IMAGE_PNG;
                    if (extension.toLowerCase().equals("gif")) {
                        extension = "png";// We cannot handle gifs
                    }
                }
                try {
                    // 临时文件名称不能有汉字，统一重命名
                    String tmpFileName = RandomUtil.getSerialNumber() + "." + extension;
                    String tmpPath = GlobalConstant.APP_QIYEBAO_TEMP_USER_PHOTO_PATH + File.separator;
                    if (uploadFile != null && !uploadFile.isEmpty()) {
                        logger.info("原临时图片不存在或已删除！");
                        logger.info("tmpPath=======================" + tmpPath);
                        File tmpFile = new File(realPath + tmpPath);
                        if (!tmpFile.exists()) {
                            tmpFile.mkdirs();
                        }
                        String tmpFilePath = realPath + tmpPath + File.separator + tmpFileName;
                        if (extension.equals("jpg") || extension.equals("jpeg")
                                || extension.equals("gif") || extension.equals("png")) {
                            //保存图片
                            BufferedImage image = ImageHelper.resizeImage(
                                    uploadFile.getInputStream(), type, 226, 150);
                            ImageHelper.saveImage(image, tmpFilePath, type);
                        } else {
                            uploadFile.transferTo(new File(tmpFilePath));
                        }
                        File file = new File(tmpFilePath);
                        //数据库-资源服务器地址
                        String savePath = GlobalConstant.APP_QIYEBAO_USER_PHOTO_PATH + File.separator;
                        boolean b = fileSynchronizer.syncFile(file, savePath, file.getName());// 上传到网站端-资源服务器
                        if (b == false) {
                            map.put("tmpFileName", tmpFileName);
                            map.put("code", "lose");
                        } else {
                            jsonAry = new JSONArray();
                            JSONObject uploadFileInfo = new JSONObject();
                            uploadFileInfo.put("name", file.getName());
                            uploadFileInfo.put("path", FileSynchronizer.getReceiverUrl() + savePath + file.getName());
                            uploadFileInfo.put("type", file.getName().substring(file.getName().indexOf(".") + 1, file.getName().length()));
                            uploadFileInfo.put("thumbnail", "null");
                            jsonAry.add(uploadFileInfo);
                            logger.info("tempFilePath==" + File.separator + tmpFileName);
                            map.put("tmpFilePath", tmpPath + File.separator
                                    + tmpFileName);
                            map.put("code", "0");
                            map.put("oldFileName", fileName);
                            map.put("tmpFileName", tmpFileName);
                        }
                    } else {
                        map.put("tmpFileName", tmpFileName);
                        map.put("code", "1");
                    }
                } catch (Exception e) {
                    map.put("code", "2");
                    map.put("message", "服务忙碌请稍后重试");
                    e.printStackTrace();
                }
            } else {
                map.put("code", "false");
                map.put("message", "请选择正确格式的文件");
            }
        }
        if (jsonAry == null) {
            return map;
        }
        map.put("attachFile", jsonAry.toJSONString());
        return map;
    }

    /**
     * @param request
     * @return
     * @throws IOException
     * @description 上传图片
     * @version v1.0
     * @author 吕剑
     * @update 2019年9月02日 上午14:56:50
     */
    @RequestMapping("/upload/logoImg")
    public @ResponseBody
    Map<String, Object> uploadAppLunBoTu(HttpServletRequest request, Model model)
            throws IOException {
        Map<String, Object> map = new HashMap<>();

        String realPath = request.getSession().getServletContext()
                .getRealPath(File.separator);

        //拿到文件集合 并对文件进行处理
        Map<String, MultipartFile> formMultipartFile = getFormMultipartFile(request);
        if (formMultipartFile == null) {
            map.put("code", "false");
            map.put("msg", "不能上传空的文件");
        }
        for (Map.Entry<String, MultipartFile> entry : formMultipartFile.entrySet()) {
            MultipartFile uploadFile = entry.getValue();
            String fileName = FileHelper.disposeFileName(uploadFile
                    .getOriginalFilename());
            String extension = StringHelper.unqualify(fileName).toLowerCase();
            int type = ImageHelper.IMAGE_UNKNOWN;
            if (uploadFile.getSize() > 1048576) {
                map.put("code", "big");
                map.put("tmpFileName", fileName);
            } else if (extension.equals("jpg") || extension.equals("jpeg")
                    || extension.equals("gif") || extension.equals("png")) {

                if (extension.equals("jpg") || extension.equals("jpeg")) {
                    type = ImageHelper.IMAGE_JPEG;
                } else if (extension.equals("gif") || extension.equals("png")) {
                    type = ImageHelper.IMAGE_PNG;
                    if (extension.toLowerCase().equals("gif")) {
                        extension = "png";// We cannot handle gifs
                    }
                }
                try {
                    // 临时文件名称不能有汉字，统一重命名
                    String tmpFileName = RandomUtil.getSerialNumber() + "." + extension;
                    String tmpPath = GlobalConstant.APP_QIYEBAO_TEMP_USER_PHOTO_PATH + File.separator;
                    if (uploadFile != null && !uploadFile.isEmpty()) {
                        logger.info("原临时图片不存在或已删除！");
                        logger.info("tmpPath=======================" + tmpPath);
                        File tmpFile = new File(realPath + tmpPath);
                        if (!tmpFile.exists()) {
                            tmpFile.mkdirs();
                        }
                        String tmpFilePath = realPath + tmpPath + File.separator + tmpFileName;
                        if (extension.equals("jpg") || extension.equals("jpeg")
                                || extension.equals("gif") || extension.equals("png")) {
                            //保存图片
                            BufferedImage image = ImageHelper.resizeImage(
                                    uploadFile.getInputStream(), type, 226, 150);
                            ImageHelper.saveImage(image, tmpFilePath, type);
                        } else {
                            uploadFile.transferTo(new File(tmpFilePath));
                        }
                        File file = new File(tmpFilePath);
                        //数据库-资源服务器地址
                        String savePath = GlobalConstant.APP_QIYEBAO_USER_PHOTO_PATH + File.separator;
                        boolean b = fileSynchronizer.syncFile(file, savePath, file.getName());// 上传到网站端-资源服务器
                        if (b == false) {
                            map.put("tmpFileName", tmpFileName);
                            map.put("code", "lose");
                        } else {
                            map.put("name", file.getName());
                            map.put("path", FileSynchronizer.getReceiverUrl() + savePath + file.getName());
                            map.put("tmpFilePath", tmpPath + File.separator
                                    + tmpFileName);
                            map.put("code", "0");
                            map.put("oldFileName", fileName);
                            map.put("tmpFileName", tmpFileName);
                            logger.info("tempFilePath==" + File.separator + tmpFileName);
                        }
                    } else {
                        map.put("tmpFileName", tmpFileName);
                        map.put("code", "1");
                    }
                } catch (Exception e) {
                    map.put("code", "2");
                    map.put("message", "服务忙碌请稍后重试");
                    e.printStackTrace();
                }
            } else {
                map.put("code", "false");
                map.put("message", "请选择正确格式的文件");
            }
        }
        return map;
    }
}
