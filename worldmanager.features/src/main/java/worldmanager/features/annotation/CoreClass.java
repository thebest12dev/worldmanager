package worldmanager.features.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/// This annotation is to define if a class is a "core" class or not.
/// @author thebest12lines
@Retention(RetentionPolicy.RUNTIME)
@CoreClass

public @interface CoreClass {
}
