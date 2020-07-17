package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.util.CommonReadParam;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class Base64Uploader {
    public static State save(String content, Map<String, Object> conf) {
        byte[] data = decode(content);

        long maxSize = ((Long) conf.get("maxSize")).longValue();

        if (!validSize(data, maxSize)) {
            return new BaseState(false, 1);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"),
                (String) conf.get("filename"));

        savePath = savePath + suffix;
        // 特殊处理
        CommonReadParam param = new CommonReadParam("config/resource");
        String serverPath = param.getString("resourceServer.AccessURL");
        String originFileName = (String) conf.get("filename");
        String physicalPath = savePath.replace(suffix, "");
        FileSynchronizer sync = new FileSynchronizer();
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(data);
            sync.syncFileByIs(physicalPath, (String) conf.get("filename"), is);
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
        State storageState = new BaseState(true);
        storageState.putInfo("size", "");
        storageState.putInfo("title", "");
        storageState.putInfo("url", serverPath + physicalPath + "/" + (String) conf.get("filename"));
        storageState.putInfo("type", suffix);
        storageState.putInfo("original", originFileName + suffix);

        return storageState;
        /*String physicalPath = (String)conf.get("rootPath") + savePath;

        State storageState = StorageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;*/
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }
}