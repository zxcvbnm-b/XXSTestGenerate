package xxs.gen.gentest.service.entity;

import java.util.List;

public class VelocityGenOneTestDTO {
    private List<MockControllerMethodRequestEntity> mockControllerMethodRequestEntities;
    private String packageName;
    private String className;
    private boolean isOpenTransaction;


    public List<MockControllerMethodRequestEntity> getMockControllerMethodRequestEntities() {
        return mockControllerMethodRequestEntities;
    }

    public void setMockControllerMethodRequestEntities(List<MockControllerMethodRequestEntity> mockControllerMethodRequestEntities) {
        this.mockControllerMethodRequestEntities = mockControllerMethodRequestEntities;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isOpenTransaction() {
        return isOpenTransaction;
    }

    public void setOpenTransaction(boolean openTransaction) {
        isOpenTransaction = openTransaction;
    }
}
