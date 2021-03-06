/*
 * Copyright 2019 The FATE Authors. All Rights Reserved.
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

package com.webank.ai.fate.serving.common.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FileMetricReport implements MetricReport {

    private static final long DEFAULT_FILE_SIZE = 1048576L;

    MetricWriter metricWriter;

    Logger logger = LoggerFactory.getLogger(FileMetricReport.class);

    public FileMetricReport(String appName) {
        this.metricWriter = new MetricWriter(appName, DEFAULT_FILE_SIZE);
    }

    public void rmAllFile() throws Exception {
        metricWriter.removeAllFiles();
    }

    @Override
    public void report(List<MetricNode> data) {
        try {
            metricWriter.write(TimeUtil.currentTimeMillis(), data);
        } catch (Exception e) {
            logger.error("metric writer error",e);

        }
    }
}
