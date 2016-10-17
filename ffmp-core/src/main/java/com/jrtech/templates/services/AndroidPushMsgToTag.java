package com.jrtech.templates.services;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.*;

import java.util.List;

/**
 * Created by jiangliang on 2016/10/13.
 */
public class AndroidPushMsgToTag {
    private final String apiKey = "xxxxxxxxxxxxxxxxxxxx";
    private final String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * 普通组播
     * @param tagName 设备id
     * @param msg 消息
     * @throws PushClientException
     * @throws PushServerException
     */
    public void pushMsgToTag (String tagName,String msg) throws PushClientException, PushServerException {
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                            // .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    .addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}")
                    .addDeviceType(3);
            // 5. http request
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    /**
     * 创建用户自定义的标签组。
     * @param tagName 标签名称
     * @throws PushClientException
     * @throws PushServerException
     */
    public void createTag(String tagName) throws PushClientException, PushServerException {
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            // 4. specify request arguments
            CreateTagRequest request = new CreateTagRequest().addTagName(
                    tagName).addDeviceType(3);
            // 5. http request
            CreateTagResponse response = pushClient.createTag(request);
            System.out.println(String.format("tagName: %s, result: %d",
                    response.getTagName(), response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    /**
     * 向标签组中批量添加设备。
     * @throws PushClientException
     * @throws PushServerException
     */
    public void addDevicesToTag(String tagName,String[] channelIds) throws PushClientException, PushServerException {
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            // 4. specify request arguments
            AddDevicesToTagRequest request = new AddDevicesToTagRequest()
                    .addTagName(tagName).addChannelIds(channelIds)
                    .addDeviceType(3);
            // 5. http request
            AddDevicesToTagResponse response = pushClient
                    .addDevicesToTag(request);
            // Http请求返回值解析
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInTag：{");
                List<?> devicesInfo = response.getDevicesInfoAfterAdded();
                for (int i = 0; i < devicesInfo.size(); i++) {
                    Object object = devicesInfo.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    if (object instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) object;
                        strBuilder.append("{channelId:"
                                + deviceInfo.getChannelId() + ",result:"
                                + deviceInfo.getResult() + "}");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }
}
