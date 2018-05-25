package com.hpmont.dao.Impl;

/**
 * Created by Administrator on 2016/7/19.
 */
//@Repository("AdminMenuDao")
//public class AdminMenuDaoImpl extends BaseDAO implements AdminMenuMapper {
//
//	private static final String NAMESPACE_INFOUSER = "AdminMenuMapper.";
//
//	@Override
//	public int deleteByPrimaryKey(Long id) {
//		return delete(NAMESPACE_INFOUSER + "deleteByPrimaryKey", id);
//	}
//
//	@Override
//	public int insert(AdminMenu record) {
//		return insert(NAMESPACE_INFOUSER + "insert", record);
//	}
//
//	@Override
//	public AdminMenu selectByPrimaryKey(Long id) {
//		return selectOne(NAMESPACE_INFOUSER + "selectByPrimaryKey", id);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(AdminMenu record) {
//		return update(NAMESPACE_INFOUSER + "updateByPrimaryKeySelective", record);
//	}
//
//	@Override
//	public List<TreeAdminMenu> selectMenuByRoleId(Long roleId) {
//		return selectList(NAMESPACE_INFOUSER + "selectByRoleId", roleId);
//	}
//
//	@Override
//	public List<ShowAdminMenu> selectMenu() {
//		return selectList(NAMESPACE_INFOUSER + "selectMenu");
//	}
//
//	/**
//	 * 查询一级菜单列表
//	 *
//	 * @return
//	 */
//	@Override
//	public List<ShowAdminMenu> selectFirstMenus() {
//		return selectList(NAMESPACE_INFOUSER + "selectFirstMenus");
//	}
//
//	@Override
//	public Page<UserAuthority> selectAuthority(UserAuthority userAuthority, int currpage, int prePageRows) {
//		return selectPageList(NAMESPACE_INFOUSER + "selectAuthority", userAuthority, currpage, prePageRows);
//	}
//
//	/**
//	 * 查询树形列表
//	 *
//	 * @return
//	 */
//	@Override
//	public List<TreeAdminMenu> selectTreeMenu() {
//		return selectList(NAMESPACE_INFOUSER + "selectTreeMenu");
//	}
//}
