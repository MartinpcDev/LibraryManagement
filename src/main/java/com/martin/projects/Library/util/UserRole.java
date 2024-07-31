package com.martin.projects.Library.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRole {
  ADMIN(Arrays.asList(Permissions.values())),
  EMPLOYEE(Arrays.stream(Permissions.values())
      .filter(permissions -> !permissions.name().startsWith("READ_ALL_USERS") &&
          !permissions.name().startsWith("SAVE_ONE_USER") &&
          !permissions.name().startsWith("UPDATE_ONE_USER") &&
          !permissions.name().startsWith("DELETE_ONE_USER")
      ).collect(Collectors.toList()));

  private List<Permissions> permissions;

  UserRole(List<Permissions> permissions) {
    this.permissions = permissions;
  }

  public List<Permissions> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permissions> permissions) {
    this.permissions = permissions;
  }
}
