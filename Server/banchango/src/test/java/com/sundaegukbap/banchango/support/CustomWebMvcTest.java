package com.sundaegukbap.banchango.support;

import com.sundaegukbap.banchango.recipe.presentation.RecipeController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@EnableAspectJAutoProxy
@WebMvcTest(controllers=RecipeController.class)
@Import({/*TestAuthConfig.class,*/ MockAllServiceBeanFactoryPostProcessor.class/*, ValidPageableAspect.class*/})
@Retention(RetentionPolicy.RUNTIME)
@TestExecutionListeners(value = {/*MockAuthTestExecutionListener.class,*/
        ResetMockTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface CustomWebMvcTest {

    Class<?>[] value() default {};
}