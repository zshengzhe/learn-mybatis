package com.ximalaya.eywa.common.spport;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * 类型帮助
 *
 * @author Zhang Shengzhe
 * @create 2022-05-23 17:10
 */
public final class TypeSupport {

  private TypeSupport() {
  }

  public static <T> T returnNull() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <T> T unsafeCast(Object obj) {
    return (T) obj;
  }

  public static <T> T cast(Object obj, Class<T> cls) {
    return TypeUtils.cast(obj, cls, ParserConfig.global);
  }

}
