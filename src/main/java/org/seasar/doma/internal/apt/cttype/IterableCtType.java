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
package org.seasar.doma.internal.apt.cttype;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import org.seasar.doma.internal.apt.Context;

public class IterableCtType extends AbstractCtType {

    protected final TypeMirror elementTypeMirror;

    protected final CtType elementCtType;

    protected final boolean isList;

    public IterableCtType(Context ctx, TypeMirror type,
            boolean isList, TypeMirror elementTypeMirror,
            CtType elementCtType) {
        super(ctx, type);
        this.isList = isList;
        this.elementTypeMirror = elementTypeMirror;
        this.elementCtType = elementCtType;
    }

    public CtType getElementCtType() {
        return elementCtType;
    }

    public boolean isRawType() {
        return elementTypeMirror == null;
    }

    public boolean isWildcardType() {
        return elementTypeMirror != null
                && elementTypeMirror.getKind() == TypeKind.WILDCARD;
    }

    public boolean isList() {
        return isList;
    }

    @Override
    public <R, P, TH extends Throwable> R accept(
            CtTypeVisitor<R, P, TH> visitor, P p) throws TH {
        return visitor.visitIterableCtType(this, p);
    }
}
