/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor;

/**
 * @author Clinton Begin
 */
public class ErrorContext {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator","\n");
  //多线程环境中，每个线程内部可以共用一份 ErrorContext，互不影响，保证异常日志正确输出
  private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<>();

  //充当中介，在调用 store() 方法时将当前 ErrorContext 保存下来，在调用 recall() 方法时将该 ErrorContext 实例传递给 LOCAL
  private ErrorContext stored;
  //异常是由谁在做什么的时候在哪个资源文件中发生的，执行的 SQL 是哪个，以及 Java 详细的异常信息
  private String resource;        //存储异常存在于哪个资源文件中
  private String activity;        //存储异常是做什么操作时发生的
  private String object;          //存储哪个对象操作时发生异常
  private String message;         //存储异常的概览信息
  private String sql;             //存储发生日常的 SQL 语句
  private Throwable cause;        //存储详细的 Java 异常日志

  private ErrorContext() {
  }

  public static ErrorContext instance() {
    ErrorContext context = LOCAL.get();
    if (context == null) {
      context = new ErrorContext();
      LOCAL.set(context);
    }
    return context;
  }

  //stored 变量充当一个中介，在调用 store() 方法时将当前 ErrorContext 保存下来，在调用 recall() 方法时将该 ErrorContext 实例传递给 LOCAL。
  public ErrorContext store() {
    ErrorContext newContext = new ErrorContext();
    newContext.stored = this;
    LOCAL.set(newContext);
    return LOCAL.get();
  }

  public ErrorContext recall() {
    if (stored != null) {
      LOCAL.set(stored);
      stored = null;
    }
    return LOCAL.get();
  }

  public ErrorContext resource(String resource) {
    this.resource = resource;
    return this;
  }

  public ErrorContext activity(String activity) {
    this.activity = activity;
    return this;
  }

  public ErrorContext object(String object) {
    this.object = object;
    return this;
  }

  public ErrorContext message(String message) {
    this.message = message;
    return this;
  }

  public ErrorContext sql(String sql) {
    this.sql = sql;
    return this;
  }

  public ErrorContext cause(Throwable cause) {
    this.cause = cause;
    return this;
  }

  public ErrorContext reset() {
    resource = null;
    activity = null;
    object = null;
    message = null;
    sql = null;
    cause = null;
    LOCAL.remove();
    return this;
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();

    // message
    if (this.message != null) {
      description.append(LINE_SEPARATOR);
      description.append("### ");
      description.append(this.message);
    }

    // resource
    if (resource != null) {
      description.append(LINE_SEPARATOR);
      description.append("### The error may exist in ");
      description.append(resource);
    }

    // object
    if (object != null) {
      description.append(LINE_SEPARATOR);
      description.append("### The error may involve ");
      description.append(object);
    }

    // activity
    if (activity != null) {
      description.append(LINE_SEPARATOR);
      description.append("### The error occurred while ");
      description.append(activity);
    }

    // sql
    if (sql != null) {
      description.append(LINE_SEPARATOR);
      description.append("### SQL: ");
      description.append(sql.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ').trim());
    }

    // cause
    if (cause != null) {
      description.append(LINE_SEPARATOR);
      description.append("### Cause: ");
      description.append(cause.toString());
    }

    return description.toString();
  }


  //获取系统参数
  public static void main(String[] args)
  {
    System.out.println("行分隔符（在 UNIX 系统中是“/n”）:/n" + System.getProperty("line.separator"));

    System.out.println("Java运行时环境版本:/n" + System.getProperty("java.version"));
    System.out.println("Java 运行时环境供应商:/n" + System.getProperty("java.vendor"));
    System.out.println("Java 供应商的URL:/n" + System.getProperty("java.vendor.url"));
    System.out.println("Java安装目录:/n" + System.getProperty("java.home"));
    System.out.println("Java 虚拟机规范版本:/n" + System.getProperty("java.vm.specification.version"));
    System.out.println("Java 类格式版本号:/n" + System.getProperty("java.class.version"));
    System.out.println("Java类路径：/n" + System.getProperty("java.class.path"));
    System.out.println("加载库时搜索的路径列表:/n" + System.getProperty("java.library.path"));
    System.out.println("默认的临时文件路径:/n" + System.getProperty("java.io.tmpdir"));
    System.out.println("要使用的 JIT 编译器的名称:/n" + System.getProperty("java.compiler"));
    System.out.println("一个或多个扩展目录的路径:/n" + System.getProperty("java.ext.dirs"));
    System.out.println("操作系统的名称:/n" + System.getProperty("os.name"));
    System.out.println("操作系统的架构:/n" + System.getProperty("os.arch"));
    System.out.println("操作系统的版本:/n" + System.getProperty("os.version"));
    System.out.println("文件分隔符（在 UNIX 系统中是“/”）:/n" + System.getProperty("file.separator"));
    System.out.println("路径分隔符（在 UNIX 系统中是“:”）:/n" + System.getProperty("path.separator"));
    System.out.println("用户的账户名称:/n" + System.getProperty("user.name"));
    System.out.println("用户的主目录:/n" + System.getProperty("user.home"));
    System.out.println("用户的当前工作目录:/n" + System.getProperty("user.dir"));
  }

}
