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

package com.savoirtech.hecate.core.dao;

import java.util.Set;

import com.savoirtech.hecate.core.utils.ColumnIterator;

public interface GenericIteratingDao<KeyType, NameType, ValueType> {

    public void delete(KeyType key);

    public void deleteColumn(KeyType key, NameType name);

    public Set getKeys();

    public boolean containsKey(KeyType key);

    public void save(KeyType key, NameType name, ValueType value);

    public ColumnIterator<KeyType, NameType, ValueType> find(KeyType key);

    public ColumnIterator<KeyType, NameType, ValueType> find(KeyType key, boolean reverse);

    public ColumnIterator<KeyType, NameType, ValueType> find(KeyType key, NameType start, NameType end);

    public ColumnIterator<KeyType, NameType, ValueType> find(KeyType key, NameType start, NameType end, boolean reverse);
}
