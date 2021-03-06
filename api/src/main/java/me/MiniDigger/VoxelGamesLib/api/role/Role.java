package me.MiniDigger.VoxelGamesLib.api.role;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A role is part of the simplified permission system. all permissions are assigned to roles by default so there is no permission setup needed.
 */
public class Role {

    public static final String DEFAULT = "default";
    public static final String PREMIUM = "premium";
    public static final String MODERATOR = "moderator";
    public static final String ADMIN = "admin";

    private String name;
    private Role parent;

    public Role(@Nonnull String name, @Nullable Role parent) {
        this.name = name;
        this.parent = parent;
    }

    /**
     * @return the name of the role
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * @return the parent role, can be null if there is no parent (like for the default role)
     */
    @Nullable
    public Role getParent() {
        return parent;
    }

    /**
     * Checks if this role has the desired permission. Walks up the inheritance tree.
     *
     * @param perm the permission to check
     * @return if the role has that permission
     */
    public boolean hasPermission(Permission perm) {
        String roleName = perm.getRole().getName();
        Role currRole = this;
        while (currRole != null) {
            if (currRole.getName().equalsIgnoreCase(roleName)) {
                return true;
            }

            currRole = currRole.getParent();
        }

        return false;
    }
}
