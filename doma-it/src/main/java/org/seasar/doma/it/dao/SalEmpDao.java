package org.seasar.doma.it.dao;

import java.sql.Array;
import java.util.List;

import org.seasar.doma.AccessLevel;
import org.seasar.doma.ArrayFactory;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.it.ItConfig;
import org.seasar.doma.it.entity.SalEmp;

@Dao(config = ItConfig.class, accessLevel = AccessLevel.PACKAGE)
public interface SalEmpDao {

    static SalEmpDao get() {
        return new SalEmpDaoImpl();
    }

    @Select
    List<SalEmp> selectAll();

    @Insert
    int insert(SalEmp entity);

    @Update
    int update(SalEmp entity);

    @ArrayFactory(typeName = "integer")
    Array createIntegerArray(Integer[] elements);

    @ArrayFactory(typeName = "text")
    Array createString2DArray(String[][] elements);
}
