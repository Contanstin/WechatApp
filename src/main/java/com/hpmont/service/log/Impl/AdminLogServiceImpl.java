package com.hpmont.service.log.Impl;

import com.hpmont.dao.mapper.AdminLogMapper;
import com.hpmont.domain.manual.admin.AdminLog;
import com.hpmont.service.log.IAdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/22.
 */
@Service("adminLogServiceImpl")
public class AdminLogServiceImpl implements IAdminLogService {
    @Autowired
    private AdminLogMapper logDao;
    @Override
    public int addLog(AdminLog record) {
        return logDao.insert(record);
    }
}
