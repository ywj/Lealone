/**
 * Copyright (c) 2011 Yahoo! Inc. All rights reserved.
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
 * limitations under the License. See accompanying LICENSE file.
 */

package com.codefollower.lealone.omid.transaction;

import java.util.HashSet;
import java.util.Set;

import com.codefollower.lealone.omid.client.RowKeyFamily;

/**
 * 
 * This class contains the required information to represent an Omid's
 * transaction, including the set of rows modified.
 * 
 */
public class Transaction {
    private final Set<RowKeyFamily> rows;
    private final long startTimestamp;
    private long commitTimestamp;
    private boolean rollbackOnly;

    Transaction(long startTimestamp) {
        this.rows = new HashSet<RowKeyFamily>();
        this.startTimestamp = startTimestamp;
        this.commitTimestamp = 0;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(long commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }

    public RowKeyFamily[] getRows() {
        return rows.toArray(new RowKeyFamily[0]);
    }

    public void addRow(RowKeyFamily row) {
        rows.add(row);
    }

    public String toString() {
        return "Transaction-" + Long.toHexString(startTimestamp);
    }

    /**
     * Modify the transaction associated with the current thread such that the
     * only possible outcome of the transaction is to roll back the transaction.
     */
    public void setRollbackOnly() {
        rollbackOnly = true;
    }

    public boolean isRollbackOnly() {
        return rollbackOnly;
    }
}
