package com.db.sys.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.sys.dao.SysMenuDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.dao.SysRoleUserDao;
import com.db.sys.dao.SysUserDao;
import com.db.sys.pojo.SysUser;
@Service
public class ShiroUserRealm extends AuthorizingRealm  {
	
	@Autowired
	private SysUserDao sysUserDao;
    @Autowired
	private SysRoleUserDao sysUserRoleDao;
    @Autowired
	private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;
	/**
	 * 设置凭证匹配器(此方法用于告诉认证管理器
	 * 采用什么加密算法对用户输入密码进行加密)
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher hm = new HashedCredentialsMatcher();
		hm.setHashAlgorithmName("MD5");
		hm.setHashIterations(1);
		super.setCredentialsMatcher(hm);
	}
	
	
	
	
	
	/**
	 * 通过此方法完成授权信息的获取和封装
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("==doGetAuthorizationInfo==");
    	//1.获取登录用户id
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		//2.基于用户id获取用户对应的角色id并判定
		List<Integer> roleIds=
		sysUserRoleDao.findRoleIdsByUserId(user.getId());
		if(roleIds==null||roleIds.isEmpty())
		throw new AuthorizationException();
		
		//3.基于角色id获取对应的菜单id并判定
		Integer[] array= {};
		List<Integer> menuIds=
		sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.isEmpty())
		throw new AuthorizationException();
		
		//4.基于菜单id获取权限标识
		List<String> permissions=
		sysMenuDao.findPermissions(menuIds.toArray(array));
		if(permissions==null||permissions.isEmpty())
	    throw new AuthorizationException();
		//5.封装数据并返回。
		Set<String> permissionSet=new HashSet<String>();
		for(String per:permissions) {
			if(!StringUtils.isEmpty(per)) {
				permissionSet.add(per);
			}
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(permissionSet);
		return info;//此信息会返回给授权管理器
	}
	
	/**
	 * 通过此方法完成认证信息的获取和封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//从token中获得用户名密码token
		UsernamePasswordToken uptoken
		=(UsernamePasswordToken)token;
	
		String username = uptoken.getUsername();
		//2.基于用户名从数据库中查询用户信息
		SysUser userDB=sysUserDao.doFindObjectByName(username);
		//3.判定用户是否存在
		if(userDB==null) { throw new UnknownAccountException();
			
		}
		////4.判定用户是否已被禁用
		Long valid = userDB.getValid();
		if(valid==0) { throw new LockedAccountException();
			
		}
		//5.封装用户信息并返回
		ByteSource credentialsSalt = ByteSource.Util.bytes(userDB.getSalt());
		System.out.println(credentialsSalt);
		SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						userDB,//principal 表示身份信息
						userDB.getPassword(),//hashedCredentials 已加密的密码
						credentialsSalt,//salt 
						getName());//realmName
		
		
		return info;
		
	}

}
