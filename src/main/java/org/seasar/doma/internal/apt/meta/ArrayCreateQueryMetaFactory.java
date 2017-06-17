/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.apt.meta;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.sql.Array;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import org.seasar.doma.internal.apt.AptException;
import org.seasar.doma.internal.apt.Context;
import org.seasar.doma.internal.apt.reflection.ArrayFactoryReflection;
import org.seasar.doma.message.Message;

/**
 * @author taedium
 * 
 */
public class ArrayCreateQueryMetaFactory extends
        AbstractCreateQueryMetaFactory<ArrayCreateQueryMeta> {

    public ArrayCreateQueryMetaFactory(Context ctx) {
        super(ctx, Array.class);
    }

    @Override
    public QueryMeta createQueryMeta(ExecutableElement method, DaoMeta daoMeta) {
        assertNotNull(method, daoMeta);
        ArrayFactoryReflection arrayFactoryReflection = ctx
                .getReflections()
                .newArrayFactoryReflection(method);
        if (arrayFactoryReflection == null) {
            return null;
        }
        ArrayCreateQueryMeta queryMeta = new ArrayCreateQueryMeta(method,
                daoMeta.getDaoElement());
        queryMeta.setArrayFactoryReflection(arrayFactoryReflection);
        queryMeta.setQueryKind(QueryKind.ARRAY_FACTORY);
        doTypeParameters(queryMeta, method, daoMeta);
        doReturnType(queryMeta, method, daoMeta);
        doParameters(queryMeta, method, daoMeta);
        doThrowTypes(queryMeta, method, daoMeta);
        return queryMeta;
    }

    @Override
    protected void doParameters(ArrayCreateQueryMeta queryMeta,
            ExecutableElement method, DaoMeta daoMeta) {
        List<? extends VariableElement> parameters = method.getParameters();
        int size = parameters.size();
        if (size != 1) {
            throw new AptException(Message.DOMA4002, method, new Object[] {
                    daoMeta.getDaoElement().getQualifiedName(),
                    method.getSimpleName() });
        }
        QueryParameterMeta parameterMeta = createParameterMeta(
                parameters.get(0), queryMeta);
        if (parameterMeta.getType().getKind() != TypeKind.ARRAY) {
            throw new AptException(Message.DOMA4076, parameterMeta.getElement(),
                    new Object[] {
                            daoMeta.getDaoElement().getQualifiedName(),
                            method.getSimpleName() });
        }
        queryMeta.setElementsParameterName(parameterMeta.getName());
        queryMeta.addParameterMeta(parameterMeta);
        if (parameterMeta.isBindable()) {
            queryMeta.addBindableParameterCtType(parameterMeta.getName(),
                    parameterMeta.getCtType());
        }
    }
}
