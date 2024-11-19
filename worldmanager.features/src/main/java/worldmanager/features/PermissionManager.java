package worldmanager.features;

import worldmanager.features.internal.CoreClass;

import java.util.*;

public class PermissionManager {
    private static final ArrayList<String> requestedPermissions = new ArrayList<>(List.of(new String[]{
            "GRANT_PERMISSIONS", "REVOKE_PERMISSIONS", "ACCESS_CORE_MODULE", "BYPASS_SECURITY_RESTRICTIONS",
            "MODIFY_WORLDS", "ACCESS_COMMON_MODULE", "LOAD_FEATURES", "ROOT_ACCESS", "BASIC_PERMISSIONS"
    }));
    private static final Map<Class<?>, Set<String>> permissions = new HashMap<>();

    public static void grantPermission(Class<?> clazz, String permission) {
        if (!clazz.isAnnotationPresent(CoreClass.class)) {
            if (!callerClassIs(clazz) && !hasPermission(clazz,"GRANT_PERMISSIONS")) {
                throw new SecurityException("Permission can only be granted with the GRANT_PERMISSIONS permission");
            }
            if (permission.equals("ROOT_ACCESS") && hasPermission(clazz,"ROOT_ACCESS")) {
                throw new SecurityException("Permission can only be granted with the ROOT_ACCESS permission");
            }
        }
        if (requestedPermissions.contains(permission)) {
            permissions.computeIfAbsent(clazz, k -> new HashSet<>()).add(permission);
        } else {
            throw new IllegalArgumentException("Unknown permission" +permission);
        }


    }

    public static void revokePermission(Class<?> clazz, String permission) {
        if (!clazz.isAnnotationPresent(CoreClass.class)) {
            if (!callerClassIs(clazz) && !hasPermission(clazz, "REVOKE_PERMISSIONS")) {
                throw new SecurityException("Permission can only be revoked with the REVOKE_PERMISSIONS permission");
            } else if (callerClassIs(clazz)) {
                Set<String> classPermissions = permissions.get(clazz);
                if (classPermissions != null) {
                    classPermissions.remove(permission);
                }
            }
        } else {
            Set<String> classPermissions = permissions.get(clazz);
            if (classPermissions != null) {
                classPermissions.remove(permission);
            }
        }
    }
    public static boolean hasPermission(Class<?> clazz, String permission) {
        if (clazz.isAnnotationPresent(CoreClass.class)) {
            return requestedPermissions.contains(permission); //root
        }
        Set<String> classPermissions = permissions.get(clazz);
        return classPermissions != null && classPermissions.contains(permission);
    }

    private static boolean callerClassIs(Class<?> clazz) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerClassName = stackTrace[3].getClassName(); // Get caller's class name
        try {
            return Class.forName(callerClassName) == clazz;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
