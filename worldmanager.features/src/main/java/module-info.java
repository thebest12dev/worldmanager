/**
 * <code>worldmanager v0.3.0 Alpha</code>
 * <p style="color:#0077ff">
 * The worldmanager.features module is an extension to worldmanager to include plugin/feature support features present in the program. This module is required for worldmanager to run, unless removed by modifications. This module's sole purpose is to extend worldmanager's functionality so there isn't any files associated with it.
 * </p>
 * <p>
 * This project is licensed under the MIT License, which means you are free to modify, distribute or do anything with it as LONG you include the MIT license and include the original copyright holder (thebest12lines).
 * </p>
 * @since v0.3.0-alpha
 * @author thebest12lines
 */
module worldmanager.features {

    // requires java.desktop;

    requires common;

    exports worldmanager.features;
    exports worldmanager.features.annotation;
}