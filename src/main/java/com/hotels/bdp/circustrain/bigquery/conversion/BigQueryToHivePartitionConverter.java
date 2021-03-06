/**
 * Copyright (C) 2018-2019 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hotels.bdp.circustrain.bigquery.conversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;

import com.hotels.bdp.circustrain.bigquery.util.AvroConstants;

public class BigQueryToHivePartitionConverter {

  private final Partition partition = new Partition();

  public BigQueryToHivePartitionConverter() {
    partition.setDbName("default");
    partition.setTableName("default");
    partition.setValues(new ArrayList<String>());
    partition.setLastAccessTime(0);
    partition.setParameters(new HashMap<String, String>());
    mockStats();
    StorageDescriptor sd = new StorageDescriptor();
    sd.setLocation("");
    sd.setNumBuckets(-1);
    sd.setBucketCols(Collections.<String>emptyList());
    sd.setSortCols(Collections.<Order>emptyList());
    sd.setInputFormat(AvroConstants.INPUT_FORMAT);
    sd.setOutputFormat(AvroConstants.OUTPUT_FORMAT);
    sd.setCompressed(false);
    sd.setStoredAsSubDirectories(false);
    sd.setNumBuckets(-1);
    sd.setCols(new ArrayList<FieldSchema>());
    SerDeInfo serDeInfo = new SerDeInfo();
    serDeInfo.setSerializationLib(AvroConstants.SERIALIZATION_LIB);
    SkewedInfo si = new SkewedInfo();
    si.setSkewedColNames(Collections.<String>emptyList());
    si.setSkewedColValueLocationMaps(Collections.<List<String>, String>emptyMap());
    si.setSkewedColValues(Collections.<List<String>>emptyList());
    sd.setSkewedInfo(new SkewedInfo());
    sd.setSerdeInfo(serDeInfo);
    partition.setSd(sd);
  }

  public Partition convert() {
    return new Partition(partition);
  }

  // Work around for issue: https://issues.apache.org/jira/browse/HIVE-18767
  // Delete this code once the fix
  // github.com/apache/hive/commit/2fe5186a337141b6fd80b40abbc8bc4226bee962#diff-2a1b7c6ec7a77f1ca9ad84225d192e36
  // has been released in Hive 2.3.x
  private void mockStats() {
    for (String key : StatsSetupConst.fastStats) {
      partition.getParameters().put(key, "1");
    }
  }
}
