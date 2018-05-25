package com.hpmont.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/8/27.
 * 需要写日志的继承此类
 */
public class BaseLogService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
