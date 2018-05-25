package com.hpmont.service.role.Impl;

import com.hpmont.dao.mapper.*;
import com.hpmont.domain.manual.admin.AdminRole;
import com.hpmont.domain.manual.admin.AdminRoleAuthority;
import com.hpmont.domain.manual.ShowAdminMenu;
import com.hpmont.domain.manual.role.RoleAuth;
import com.hpmont.service.role.IRoleService;
import com.hpmont.util.ArithUtil;
import com.hpmont.util.DateUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/2/4.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private AdminRoleManageMapper adminRoleDao;
    @Autowired
    private AdminRoleMapper roleDao;
    @Autowired
    private AdminMenuMapper menuDao;
    @Autowired
    private AdminRoleAuthorityMapper roleAuthDao;

    @Autowired
    private AdminUserRoleMapper userRoleDao;

    @Override
    public RoleAuth getRole(Integer roleId) {
        return adminRoleDao.selectRole(roleId);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<AdminRole> getAllRole() {
        return adminRoleDao.selectAllRole();
    }

    /**
     * 保存用户权限
     *
     * @param role
     */
    @Override
    public void saveRole(RoleAuth role,String[] auths) {
        Integer rolid=role.getId();
        if(rolid==null || rolid.intValue()==0) {
            AdminRole ar = new AdminRole();
            ar.setCreateTime(DateUtil.getCurrDateTime());
            ar.setDescription(role.getDescription());
            ar.setIsSystem(false);
            ar.setModifyTime(DateUtil.getCurrDateTime());
            ar.setName(role.getName());
            roleDao.insert(ar);
            rolid=ar.getId();
        }else{
            AdminRole ar = new AdminRole();
            ar.setId(rolid);
            ar.setDescription(role.getDescription());
            ar.setModifyTime(DateUtil.getCurrDateTime());
            ar.setName(role.getName());
            roleDao.updateByPrimaryKeySelective(ar);
        }
        if(auths!=null && auths.length>0) {
            List<ShowAdminMenu> lsmenu = menuDao.selectFirstMenus();
            Map<Long, String> mp = new HashMap<Long, String>();
            roleAuthDao.deleteByRoleId(rolid);
            for (String at : auths) {
                AdminRoleAuthority ara = new AdminRoleAuthority();
                if(this.setRoleAuth(mp, ara, at, lsmenu)){
                    ara.setRoleId(rolid);
                    roleAuthDao.insert(ara);
                }
            }
            if(mp!=null && mp.size()>0){
                Iterator<Map.Entry<Long, String>> entries = mp.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<Long, String> entry = entries.next();
                    AdminRoleAuthority ara = new AdminRoleAuthority();
                    ara.setRoleId(rolid);
                    ara.setAuthorities(entry.getValue());
                    ara.setMenuId(entry.getKey());
                    roleAuthDao.insert(ara);
                }
            }
        }
    }

    @Override
    public void delete(Integer id) {
        int count = userRoleDao.countByRoleId(id);
        if (count==0){
            roleAuthDao.deleteByRoleId(id);
            roleDao.deleteByPrimaryKey(id);
        }
    }

    private boolean setRoleAuth(Map<Long,String> firstMenuRole,AdminRoleAuthority ara,String pageAuth,List<ShowAdminMenu> lsfirstMenu){
        if(!Strings.isNullOrEmpty(pageAuth)){
            String[] spauth= pageAuth.split(";");
            if(spauth!=null && spauth.length>=2){
                ShowAdminMenu sam=lsfirstMenu.stream().filter(s -> s.getId().toString().equals(spauth[1])).limit(1).collect(Collectors.toList()).get(0);
                if(spauth.length==2){
                    ara.setAuthorities("");
                }else{
                    ara.setAuthorities(Strings.nullToEmpty(sam.getAuthorities())+":"+spauth[2]);
                }
                ara.setMenuId(ArithUtil.str2Long(spauth[0]));
                if(!firstMenuRole.containsKey(sam.getId())){
                    firstMenuRole.put(sam.getId(),Strings.nullToEmpty(sam.getAuthorities()));
                }
            }else {
                return false;
            }
        }
        return true;
    }

}
