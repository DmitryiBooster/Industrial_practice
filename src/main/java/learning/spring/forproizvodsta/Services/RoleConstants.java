package learning.spring.forproizvodsta.Services;

public final class RoleConstants {

    // Приватный конструктор — класс нельзя инстанцировать
    private RoleConstants() {
        throw new AssertionError("RoleConstants не должен инстанцироваться");
    }

    // ID ролей (как они в БД)
    public static final Long CLIENT_ROLE_ID = 2L;
    public static final Long TEACHER_ROLE_ID = 1L;
    public static final Long ADMIN_ROLE_ID = 3L;

    // Названия ролей (если нужны)
    public static final String CLIENT_ROLE_NAME = "CLIENT";
    public static final String TEACHER_ROLE_NAME = "TEACHER";
    public static final String ADMIN_ROLE_NAME = "ADMIN";
}
