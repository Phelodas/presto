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
package com.facebook.presto.spi.predicate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpiUnaryExpression
        extends SpiExpression
{
    private final String column;
    private final ValueSet valueSet;
    // TODO nullAllowed from Domain?

    @JsonCreator
    public SpiUnaryExpression(@JsonProperty("column") String column, @JsonProperty("valueSet") ValueSet valueSet)
    {
        // TODO requirenull
        this.column = column;
        this.valueSet = valueSet;
    }

    public String getColumn()
    {
        return column;
    }
    public ValueSet getValueSet()
    {
        return valueSet;
    }
}
