package com.sundaegukbap.banchango.support;

import com.sundaegukbap.banchango.config.ErrorLoggerConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@WebMvcTest
@Import({ErrorLoggerConfig.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomWebMvcTest { //컨트롤러 테스트를 위한 클래스

    //controller와 alias는 같은 매개변수에 대한 별칭
    @AliasFor("controllers")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] controllers() default {};
}