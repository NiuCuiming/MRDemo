package com.anu.logmr.utils;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LogBean implements WritableComparable<LogBean> {

    public String user;
    public String sessionId = "0000";
    public String ip;
    public String requestTimestamp;
    public String requestType;
    public String requestPath;
    public String requestProtocol;
    public String respondCode;
    public String stayTime;
    public String referalUrl="000";
    public String requestDevice;
    public String step = "000";

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public LogBean() {
    }

    public LogBean(String[] split) {

        this.ip = split[0];
        this.requestTimestamp = split[1];
        this.requestType = split[2];
        this.requestPath = split[3];
        this.requestProtocol = split[4];
        this.respondCode = split[5];
        this.stayTime = split[6];
        this.referalUrl = split[7].trim();
        this.requestDevice = split[8];

        //用户名的指定
        this.user = ip + requestDevice;
    }


    public String getUser() {
        return user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getIp() {
        return ip;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getRequestProtocol() {
        return requestProtocol;
    }

    public String getRespondCode() {
        return respondCode;
    }

    public String getStayTime() {
        return stayTime;
    }

    public String getReferalUrl() {
        return referalUrl;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public void setRequestProtocol(String requestProtocol) {
        this.requestProtocol = requestProtocol;
    }

    public void setRespondCode(String respondCode) {
        this.respondCode = respondCode;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public void setReferalUrl(String referalUrl) {
        this.referalUrl = referalUrl;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    @Override
    public String toString() {
        return user + '|' + sessionId + '|' + ip
                + '|' + requestTimestamp + '|' + requestType
                + '|' + requestPath + '|' + requestProtocol
                + '|' + respondCode + '|' + stayTime
                + '|' + referalUrl + '|' + requestDevice + '|' + step;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(user);
        dataOutput.writeUTF(sessionId);
        dataOutput.writeUTF(ip);
        dataOutput.writeUTF(requestTimestamp);
        dataOutput.writeUTF(requestType);
        dataOutput.writeUTF(requestPath);
        dataOutput.writeUTF(requestProtocol);
        dataOutput.writeUTF(respondCode);
        dataOutput.writeUTF(stayTime);
        dataOutput.writeUTF(referalUrl);
        dataOutput.writeUTF(requestDevice);
        dataOutput.writeUTF(step);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        user = dataInput.readUTF();
        sessionId = dataInput.readUTF();
        ip = dataInput.readUTF();
        requestTimestamp = dataInput.readUTF();
        requestType = dataInput.readUTF();
        requestPath = dataInput.readUTF();
        requestProtocol = dataInput.readUTF();
        respondCode = dataInput.readUTF();
        stayTime = dataInput.readUTF();
        referalUrl = dataInput.readUTF();
        requestDevice = dataInput.readUTF();
        step = dataInput.readUTF();
    }

    @Override
    public int compareTo(LogBean o) {

        int i = (user).compareTo(o.user);

        return i;//==0?requestTimestamp.compareTo(o.requestTimestamp):i;
    }
}
