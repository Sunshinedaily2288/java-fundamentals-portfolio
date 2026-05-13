package role_access_control;

// WHAT: An Enum (Enumeration)
// WHY: To ensure only these three specific roles can ever be used.
public enum Role {
    ADMIN,    // Full access
    EDITOR,   // Mid-level access
    VIEWER    // Read-only access
}

