package me.xiaoy.core.security;

import javax.annotation.Resource;

import me.xiaoy.core.constants.SecurityConstant;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.Role;
import me.xiaoy.mvc.manager.security.service.LoginAccountService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class DBRealm extends AuthorizingRealm {

	@Resource
	private LoginAccountService loginAccountService;
	
	/**
	 * 登陆回调
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		AuthenticationInfo info = null;
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String loginName = token.getUsername();
		if(StringUtil.isEmpty(loginName))
			return null;
		
		LoginAccount account = loginAccountService.getLoginAccountByLoginName(loginName);
		if(account != null) {
			if( account.getState() == SecurityConstant.LOGIN_ACCOUNT_DISABLED) {
				throw new LockedAccountException("用户账号已禁用！");
			} else if (account.getState() == SecurityConstant.LOGIN_ACCOUNT_LOCKED) {
				throw new LockedAccountException("用户账号已锁定！");
			} else if (account.getState() == SecurityConstant.LOGIN_ACCOUNT_LOCKED) {
				throw new LockedAccountException("用户账号已锁定！");
			} else if (account.getState() == SecurityConstant.LOGIN_ACCOUNT_EXPIRED) {
				throw new ExpiredCredentialsException("用户账号已过期！");
			} else if (account.getState() == SecurityConstant.LOGIN_ACCOUNT_DISCARD) {
				throw new LockedAccountException("用户账号已废弃！");
			}
			info = new SimpleAuthenticationInfo(account.getLoginName(), account.getPassword(), getName());
		}
		return info;
	}

	/**
	 * 获取当前用户所属角色列表和拥有的权限列表
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName=(String)principals.fromRealm(getName()).iterator().next();  
		if(StringUtil.isNotEmpty(loginName)){  
			LoginAccount account = loginAccountService.getLoginAccountByLoginName(loginName);
            if(account != null && account.getRoleList() != null){ 
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
                for(Role each: account.getRoleList()){  
                        info.addRole(each.getRoleName());  
//                        info.addStringPermissions(each.getPermissionsAsString());  
                }  
                return info;  
            }
        }  
        return null;  
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
