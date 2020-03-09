package cz.muni.fi.pa165;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed  {
}
//duration of each public method call for MainXml from https://blog.j-labs.pl/2019/10/Spring-AOP-in-practice-measure-method-execution-time