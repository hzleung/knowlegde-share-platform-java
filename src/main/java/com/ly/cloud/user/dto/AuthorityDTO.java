package com.ly.cloud.user.dto;

import com.ly.cloud.user.entity.ProduceType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/21 9:02
 */


public class AuthorityDTO {
    /**
     * 角色Id#roleId#
     */
    private String roleId;
    /**
     * 角色名称#roleName#
     */
    private String roleName;
    /**
     * 菜单Id#menuId#
     */
    private String menuId;
    /**
     * 菜单名称#menuName#
     */
    private String menuName;
    /**
     * 产品类型#produceType#
     */
    private List<ProduceType> produceType;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<ProduceType> getProduceType() {
        return produceType;
    }

    public void setProduceType(List<ProduceType> produceType) {
        this.produceType = produceType;
    }
}