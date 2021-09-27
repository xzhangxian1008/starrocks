// This file is licensed under the Elastic License 2.0. Copyright 2021 StarRocks Limited.

package com.starrocks.sql.optimizer.operator.logical;

import com.google.common.base.Preconditions;
import com.starrocks.catalog.Column;
import com.starrocks.catalog.MysqlTable;
import com.starrocks.catalog.Table;
import com.starrocks.sql.optimizer.operator.OperatorType;
import com.starrocks.sql.optimizer.operator.OperatorVisitor;
import com.starrocks.sql.optimizer.operator.scalar.ColumnRefOperator;
import com.starrocks.sql.optimizer.operator.scalar.ScalarOperator;

import java.util.List;
import java.util.Map;

public class LogicalMysqlScanOperator extends LogicalScanOperator {
    public LogicalMysqlScanOperator(Table table,
                                    List<ColumnRefOperator> outputColumns,
                                    Map<ColumnRefOperator, Column> colRefToColumnMetaMap,
                                    Map<Column, ColumnRefOperator> columnMetaToColRefMap,
                                    long limit,
                                    ScalarOperator predicate) {
        super(OperatorType.LOGICAL_MYSQL_SCAN, table, outputColumns,
                colRefToColumnMetaMap, columnMetaToColRefMap, limit, predicate);
        Preconditions.checkState(table instanceof MysqlTable);
    }

    @Override
    public <R, C> R accept(OperatorVisitor<R, C> visitor, C context) {
        return visitor.visitLogicalMysqlScan(this, context);
    }
}
