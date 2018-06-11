package com.jm.pipe.FalconData.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

/**
 * @author Juan Marques 
 * @Date 2018-06-10
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    return new ConcurrentTaskExecutor(
      Executors.newFixedThreadPool(2));
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new CustomAsyncExceptionHandler();
  }

  static class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(
      Throwable throwable, Method method, Object... obj) {

      System.out.println("Exception message - " + throwable.getMessage());
      System.out.println("Method name - " + method.getName());
      for (Object param : obj) {
        System.out.println("Parameter value - " + param);
      }
    }
  }
}
