package com.db.common.aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
@Aspect
@Service
public class SysExceptionAspect {
	 @AfterThrowing("bean(*ServiceImpl)")
	 public void logExp() {
		System.out.println("出异常了"); 
	 }
}
