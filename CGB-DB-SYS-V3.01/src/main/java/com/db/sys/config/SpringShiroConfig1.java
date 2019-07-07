package com.db.sys.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import  org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;

import com.db.sys.service.ShiroUserRealm;

//@Configuration
public class SpringShiroConfig1 {
	
	
	 /**
	  * @Bean一般用户描述方法，然后将方法的返回值
	  * 交给Spring管理,其中@Bean注解中的内容为Bean
	  * 对象的key。
	  * @return
	  */

	/**
	 * shiro有三大组件构成,Subject(存储用户登陆信息),SecurityManager和 Realm
	 */
	
	@Bean("securityManager")  //配置SecurityManeger,注入UserReaml,缓存管理器和cookie管理器
	public  SecurityManager newSecurityManager( ShiroUserRealm realm,CacheManager cacheManager,
			 RememberMeManager rememberMeManager) {
		//创建安全管理器对象,他是SecurityManager的实现类
	   DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
		//管理器注入reaml   ,reaml域是与数据库连接的桥梁,相当于是service层
	   sm.setRealm(realm);
	   //注入缓存管理器,用于存储用户信息的缓存信息
	   sm.setCacheManager(cacheManager);
	   sm.setRememberMeManager(rememberMeManager);
	   sm.setSessionManager(newSessionManager());
		
		return sm;
		
		
		
		
	}
	
	 @Bean("shiroFilterFactory")
	 public ShiroFilterFactoryBean newShiroFilterFactoryBean(
		 SecurityManager securityManager) {
		//1.构建ShiroFilterFactoryBean对象(负责创建ShiroFilter工厂对象)
		ShiroFilterFactoryBean sf = new  ShiroFilterFactoryBean();
		 //2.设置安全管理器
		sf.setSecurityManager(securityManager);
		 //3.设置登录页面对应的url(非认证用户要跳转到此url对应的页面)
		sf.setLoginUrl("/doLoginUI.do");
		 //4.设置过滤规则(哪些允许匿名访问，哪些需要认证访问)
		Map<String, String> filterMap = new LinkedHashMap<String,String>();
		 filterMap.put("/bower_components/**","anon");
		 filterMap.put("/build/**","anon");
		 filterMap.put("/dist/**","anon");
		 filterMap.put("/plugins/**","anon");
		 filterMap.put("/user/doLogin.do", "anon");
		 filterMap.put("/doLogout.do","logout");
		 filterMap.put("/**","user");
		
		
		sf.setFilterChainDefinitionMap(filterMap);
		
		return sf;
	 }
	
	 //@Bean注解没有指定名字时，默认bean的名字为方法名
	 //DefaultAdvisorAutoProxyCreator是用来扫描上下文，寻找所有的Advistor(通知器），将这些Advisor应用到所有符合切入点的Bean中。
	 //所以必须在lifecycleBeanPostProcessor创建之后创建，所以用了depends-on=”lifecycleBeanPostProcessor”> 
	 @Bean("lifecycleBeanPostProcessor")
	 public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		 return new LifecycleBeanPostProcessor();
	 }
	 //配置代理,自动创建通知代理
//	 DefaultAdvisorAutoProxyCreator这个类功能更为强大，这个类的奇妙之处是他实现了BeanProcessor接口,当ApplicationContext读如所有的Bean配置信息后，
//	 这个类将扫描上下文，寻找所有的Advistor(一个Advisor是一个切入点和一个通知的组成)，将这些Advisor应用到所有符合切入点的Bean中

	 @DependsOn("lifecycleBeanPostProcessor")
	 @Bean
	 public DefaultAdvisorAutoProxyCreator DefaultAdvisorAutoProxyCreator() {
		 return new DefaultAdvisorAutoProxyCreator();
	 }
	 @Bean   //配置认证
	 public AuthorizationAttributeSourceAdvisor newAuthorizationAdvisor(
			 SecurityManager securityManager) {
		 AuthorizationAttributeSourceAdvisor advisor= 
		 new AuthorizationAttributeSourceAdvisor();
		 advisor.setSecurityManager(securityManager);
		 return advisor;
	 }
	 //配置缓存管理器(可以缓存用户的权限信息)
	 @Bean
	 public MemoryConstrainedCacheManager newCacheManager() {
		 return new MemoryConstrainedCacheManager();
	 }
	 //配置记住我
	 @Bean
	 public CookieRememberMeManager newCookieManager() {
		 CookieRememberMeManager cookieManager=
		 new CookieRememberMeManager();
		 SimpleCookie cookie=
		 new SimpleCookie("rememberMe");
		 cookie.setMaxAge(24*7*60*60);
		 cookieManager.setCookie(cookie);
		 return cookieManager;
	 }
	
		 //配置session管理器
public DefaultWebSessionManager newSessionManager() {
			 DefaultWebSessionManager sManager=new DefaultWebSessionManager();
			 sManager.setGlobalSessionTimeout(21600000);
			 sManager.setDeleteInvalidSessions(true);
			 sManager.setSessionValidationSchedulerEnabled(true);
			 return sManager;
		 }
	
}
