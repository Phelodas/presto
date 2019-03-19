/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.connector.thrift.api.expression;

import com.facebook.presto.connector.thrift.api.valuesets.PrestoThriftValueSet;
import com.facebook.presto.spi.predicate.SpiUnaryExpression;
import io.airlift.drift.annotations.ThriftConstructor;
import io.airlift.drift.annotations.ThriftField;
import io.airlift.drift.annotations.ThriftStruct;

import javax.annotation.Nullable;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static io.airlift.drift.annotations.ThriftField.Requiredness.OPTIONAL;

@ThriftStruct
public final class PrestoThriftUnaryExpression
{
    private final String column;
    private final PrestoThriftValueSet valueSet;

    @ThriftConstructor
    public PrestoThriftUnaryExpression(@Nullable String column, @Nullable PrestoThriftValueSet valueSet)
    {
        this.column = column;
        this.valueSet = valueSet;
    }

    @Nullable
    @ThriftField(value = 1, requiredness = OPTIONAL)
    public String getColumn()
    {
        return column;
    }

    @Nullable
    @ThriftField(value = 2, requiredness = OPTIONAL)
    public PrestoThriftValueSet getValueSet()
    {
        return valueSet;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrestoThriftUnaryExpression other = (PrestoThriftUnaryExpression) obj;
        return Objects.equals(this.column, other.column) &&
                Objects.equals(this.valueSet, other.valueSet);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(column, valueSet);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("column", column)
                .add("valueSet", valueSet)
                .toString();
    }

    public static PrestoThriftUnaryExpression fromSpiUnaryExpression(SpiUnaryExpression expression)
    {
        // TODO check class
        return new PrestoThriftUnaryExpression(expression.getColumn(), PrestoThriftValueSet.fromValueSet(expression.getValueSet()));
    }

    public static SpiUnaryExpression toSpiUnaryExpression(PrestoThriftUnaryExpression expression)
    {
        // TODO check class
        return new SpiUnaryExpression(expression.getColumn(), PrestoThriftValueSet.toValueSet(expression.getValueSet()));
    }
}
