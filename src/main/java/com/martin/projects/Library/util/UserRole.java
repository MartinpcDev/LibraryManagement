package com.martin.projects.Library.util;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
  ADMIN(Arrays.asList(Permissions.values())),
  EMPLOYEE(Arrays.asList(Permissions.values()));

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
