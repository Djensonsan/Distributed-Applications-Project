package testClasses;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "test", schema = "da-database", catalog = "")
public class TestEntity {
    private int idTest;
    private String name;

    @Id
    @Column(name = "idTest")
    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return idTest == that.idTest &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTest, name);
    }
}
