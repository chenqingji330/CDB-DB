package com.db.sys.config;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.db.sys.service.ShiroUserRealm;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;



//@Component
public class shiroConfig2 {
	//1配置安全管理器
	@Bean("securityManager")
	public SecurityManager newSecurityManager(ShiroUserRealm realm,CacheManager cacheManager,RememberMeManager rememberMeManager) {
		//创建安全管理器对象
		DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
		//注入其他管理器
		sm.setRealm(realm);
		sm.setCacheManager(cacheManager);
		sm.setRememberMeManager(rememberMeManager);
		return sm;
		
	}
	 
	//2配置过滤器工厂
	@Bean("shiroFilterFactoryBean")
	public  ShiroFilterFactoryBean newShiroFilterFactoryBean(SecurityManager securityManager) {
		//创建过滤器对象
		ShiroFilterFactoryBean fb = new ShiroFilterFactoryBean();
		//设置登录地址
		fb.setLoginUrl("/doLogin.do");
		//注入安全管理器
		fb.setSecurityManager(securityManager);
		//配置过滤规则,将过滤链表封装
		Map<String, String> filterMap = new LinkedHashMap<String,String>();
		 filterMap.put("/bower_components/**","anon");
		 filterMap.put("/build/**","anon");
		 filterMap.put("/dist/**","anon");
		 filterMap.put("/plugins/**","anon");
		 filterMap.put("/user/doLogin.do", "anon");
		 filterMap.put("/doLogout.do","logout");
		 filterMap.put("/**","user");
		fb.setFilterChainDefinitionMap(filterMap);
		return fb;
		
	}
	
	
	//3 创建LifecycleBeanPostProcessor  此对象管理bean的生命周期
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
		}
	
	
	//4 创建DefaultAdvisorAutoProxyCreator对象
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public  DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	//5 AuthorizationAttributeSourceAdvisor   认证中心
	@Bean
	public AuthorizationAttributeSourceAdvisor  newAuthorizationAttributeSourceAdvisor (SecurityManager securityManager) {
		
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
				
	}
	//6配置缓存管理器(可以缓存用户的权限信息) MemoryConstrainedCacheManager
	@Bean
	public  MemoryConstrainedCacheManager newMemoryConstrainedCacheManager() {
		return new  MemoryConstrainedCacheManager();
		
	}
	
	
	//7 配置记住我CookieRememberMeManager,将用户登录信息存入cookie中
	@Bean
	public  CookieRememberMeManager newCookieRememberMeManager() {
		
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setMaxAge(216000);
		simpleCookie.setName("rememberMe");
		cookieRememberMeManager.setCookie(simpleCookie);
		return cookieRememberMeManager;
		
	}
	
	public DefaultWebSessionManager newDefaultWebSessionManager() {
		DefaultWebSessionManager SM = new DefaultWebSessionManager();
		//设置会话最长时长
		SM.setGlobalSessionTimeout(216000);
		SM.setDeleteInvalidSessions(true);
		SM.setSessionValidationSchedulerEnabled(true);
		
		return SM;
		
	}
	
	
	
}
