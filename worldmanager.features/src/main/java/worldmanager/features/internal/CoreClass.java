package worldmanager.features.internal;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/// This annotation is to define if a class is a "core" class or not. It is used to determine the permissions of a class, by default this
/// annotation gives permission `ROOT_ACCESS`. This annotation is an internal annotation, thus only `worldmanager.core` can use this to bypass permissions.
/// @author thebest12lines
@Retention(RetentionPolicy.RUNTIME)
@CoreClass

public @interface CoreClass {
}
