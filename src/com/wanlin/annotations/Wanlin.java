package com.wanlin.annotations;

import java.lang.annotation.*;
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Wanlin {
    boolean isMark() default false;
}
