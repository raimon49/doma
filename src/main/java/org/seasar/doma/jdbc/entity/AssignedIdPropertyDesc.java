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
package org.seasar.doma.jdbc.entity;

import java.util.function.Supplier;

import org.seasar.doma.internal.jdbc.scalar.Scalar;

/**
 * アプリケーションにより割り当てられる識別子のプロパティ型です。
 * 
 * @author nakamura-to
 * 
 * @param <ENTITY>
 *            エンティティの型
 * @param <BASIC>
 *            プロパティの基本型
 * @param <CONTAINER>
 *            プロパティのドメイン型
 */
public class AssignedIdPropertyDesc<ENTITY, BASIC, CONTAINER>
        extends DefaultPropertyDesc<ENTITY, BASIC, CONTAINER> {

    /**
     * インスタンスを構築します。
     * 
     * @param entityClass
     *            エンティティのクラス
     * @param scalarSupplier
     *            ラッパーのサプライヤ
     * @param name
     *            プロパティの名前
     * @param columnName
     *            カラム名
     * @param namingType
     *            ネーミング規約
     * @param quoteRequired
     *            カラム名に引用符が必要とされるかどうか
     */
    public AssignedIdPropertyDesc(Class<ENTITY> entityClass,
            Supplier<Scalar<BASIC, CONTAINER>> scalarSupplier, String name, String columnName,
            NamingType namingType, boolean quoteRequired) {
        super(entityClass, scalarSupplier, name, columnName, namingType, true, true, quoteRequired);
    }

    @Override
    public boolean isId() {
        return true;
    }

}