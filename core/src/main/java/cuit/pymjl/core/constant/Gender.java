package cuit.pymjl.core.constant;


/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/7 20:33
 **/
public enum Gender {

    /**
     * 男性
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(0);
    private Integer gender;

    Gender() {
    }

    Gender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
