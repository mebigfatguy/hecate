/*
 * Copyright 2014 Savoir Technologies
 *
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

package com.savoirtech.hecate.core.indexing;

import com.savoirtech.hecate.core.abstractdao.AbstractIteratingCompositedDao;
import com.savoirtech.hecate.core.config.CassandraKeyspaceConfigurator;

public class CompositePojoDaoImpl extends AbstractIteratingCompositedDao implements CompositePojoDao {
    //----------------------------------------------------------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------------------------------------------------------

    public CompositePojoDaoImpl(String clusterName, CassandraKeyspaceConfigurator keyspaceConfigurator, String columnFamilyName,
                                String comparatorAlias) {
        super(clusterName, keyspaceConfigurator, String.class, CompositeColumnIdentifier.class, String.class, columnFamilyName, comparatorAlias);
    }
}
