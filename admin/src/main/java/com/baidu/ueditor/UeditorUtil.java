package com.baidu.ueditor;


import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.util.CommonReadParam;

import java.io.*;

//@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
//@Component
public class UeditorUtil {

    public static String resourceUrl = null;

    static {
        CommonReadParam param = new CommonReadParam("config/resource");
        resourceUrl = param.getString("resourceServer.AccessURL");
    }

    //上传资源服务器
    public static State uploadResourceServer(String physicalPath, String fileName, InputStream is) {
        boolean uploadFlag = false;
        try {
            fileName = new String(fileName.getBytes("utf-8"));
            FileSynchronizer sync = new FileSynchronizer();
            uploadFlag = sync.syncFileByIs(physicalPath, fileName, is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        State storageState = null;
        //文件成功上传资源服务器
        if (uploadFlag) {
            storageState = new BaseState(true);
            storageState.putInfo("size", "");
            storageState.putInfo("title", "");
            storageState.putInfo("url", resourceUrl + physicalPath + "/" + fileName);
            String suffix = FileType.getSuffixByFilename(fileName);
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", fileName);
        } else {
            //文件上传失败
            return new BaseState(false, 202);
        }
        return storageState;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setProperty("sun.jnu.encoding", "utf-8");
        String physicalPath = "/upload";
        String fileName = "3周年庆活动.jpg";
        File f = new File("F:" + File.separator + "3周年庆活动.jpg");
        InputStream is = new FileInputStream(f);
        uploadResourceServer(physicalPath, fileName, is);
    }

    //    @Value("${resourceServer.AccessURL}")
    public void setResourceUrl(String resourceUrl) {
        UeditorUtil.resourceUrl = resourceUrl;
    }
}