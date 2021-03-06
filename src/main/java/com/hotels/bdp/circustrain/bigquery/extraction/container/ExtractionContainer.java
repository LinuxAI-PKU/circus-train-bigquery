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
package com.hotels.bdp.circustrain.bigquery.extraction.container;

import com.google.cloud.bigquery.Table;

public class ExtractionContainer {

  private final Table table;
  private boolean temporaryTable;
  private final ExtractionUri extractionUri;
  private PostExtractionAction postExtractionAction;

  public ExtractionContainer(
      Table table,
      boolean temporaryTable,
      ExtractionUri extractionUri,
      PostExtractionAction postExtractionAction) {
    this.table = table;
    this.temporaryTable = temporaryTable;
    this.extractionUri = extractionUri;
    this.postExtractionAction = postExtractionAction;
  }

  public ExtractionContainer(Table table, boolean temporaryTable, ExtractionUri extractionUri) {
    this.table = table;
    this.temporaryTable = temporaryTable;
    this.extractionUri = extractionUri;
  }

  public Table getTable() {
    return table;
  }

  public boolean hasTemporaryTable() {
    return temporaryTable;
  }

  public ExtractionUri getExtractionUri() {
    return extractionUri;
  }

  public PostExtractionAction getPostExtractionAction() {
    return postExtractionAction;
  }

}
