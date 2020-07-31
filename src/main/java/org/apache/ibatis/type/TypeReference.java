/**
 *    Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * References a generic type.
 *
 * @param <T> the referenced type
 * @since 3.1.0
 * @author Simone Tripodi  新加的类型引用,为了引用一个泛型类型
 */

//这个类型引用的目的就是为了持有这个具体的类型处理器所处理的Java类型的原生类型。
//我们可以看到在该类中有两个public方法getRawType()和toString()方法，意味着这个原生类型是为了被外部调用而设
public abstract class TypeReference<T> {

  //此字段保存这个TypeHandler对相应的原生java类型
  private final Type rawType;

  protected TypeReference() {
    rawType = getSuperclassTypeParameter(getClass());
  }

  Type getSuperclassTypeParameter(Class<?> clazz) {
    //得到泛型T的实际类型，通过给定参数clazz的getGenericSuperclass()方法来获取该类类型的上一级类型（直接超类，父类，即参数类类型继承的类的类型）并带有参数类型，即带泛型。如果要获取不带泛型的父类可使用getSuperclass()方法
    Type genericSuperclass = clazz.getGenericSuperclass();
    //Class的实例表示的是在一个运行的应用中的所有类和接口，那么Java中有哪些不是Class类的实例呢？泛型类，如果一个类是泛型类，那么他就不再是Class类的实例，为什么呢？泛型类是Java中一种独特的存在，它一般用于传递类（更准确的说是传递类型），类似于一般方法中传递对象的概念，它不是简单的类，而是一种带有抽象概念性质的一种类，它会通过所传递的类（参数化类）来指定当前类所代表的是基于基本类型中的哪一类类型。（通过两种类型来确定具体的类型（最后这个类型表示的是泛型类型整体表达的类型））
    if (genericSuperclass instanceof Class) {
      // try to climb up the hierarchy until meet something useful
      if (TypeReference.class != genericSuperclass) {
        return getSuperclassTypeParameter(clazz.getSuperclass());
      }

      throw new TypeException("'" + getClass() + "' extends TypeReference but misses the type parameter. "
        + "Remove the extension or add a type parameter to it.");
    }

    //获取泛型<T>中的T类型，将该类型强转为参数化类型，使用其getActualTypeArguments()方法来获取其参数类型（泛型类型），因为该方法获取的泛型类型可能不是一个，所以返回的是一个数组，但是我们这里只会获取到一个，所以取第一个即可
    Type rawType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    // TODO remove this when Reflector is fixed to return Types
    //如果该类型还是参数化类型（仍然带有泛型，即泛型嵌套的模式），那么就需要再次执行getActualTypeArguments()方法来获取其泛型类型（参数类型），最后将该类型返回（赋值给字段）
    if (rawType instanceof ParameterizedType) {
      rawType = ((ParameterizedType) rawType).getRawType();
    }

    return rawType;
  }

  //getRawType()方法重点被调用的地方在TypeHandlerRegistry（类型处理器注册器）中，在没有指定JavaType而只有TypeHandler的情况下，调用该TypeHandler的getRawType()方法来获取其原生类型（即参数类型）来作为其JavaType来进行类型处理器的注册
  public final Type getRawType() {
    return rawType;
  }

  @Override
  public String toString() {
    return rawType.toString();
  }

}
