package com.daiwf.javalearndemos.spring.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * [这个类是用来测测看能否拿到yaml中的配制并且转换为实体]
 * [功能详细描述]
 * @version [版本号，2020-06-23]
 * @作者 daiwf
 * @创建时间 2020-06-23
 * @param
 * @return
 */
@Component
@ConfigurationProperties(prefix = "server")
public class ServerConfig
{
    private String port;


    public String getPort() {
        return port;
    }


    public void setPort(String port) {
        this.port = port;
    }

    public String getForwardheadersstrategy() {
        return forwardheadersstrategy;
    }

    public void setForwardheadersstrategy(String forwardheadersstrategy) {
        this.forwardheadersstrategy = forwardheadersstrategy;
    }

    private String forwardheadersstrategy;

    private    class compression
    {
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        private boolean enabled;
    }

    @Override public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Server:");
        sb.append("port:"+getPort());
        sb.append("forward-headers-strategy:"+getForwardheadersstrategy());
        sb.append("compression.isEnabled:"+new compression().isEnabled());
        return sb.toString();
    }
}
