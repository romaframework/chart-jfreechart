/*
 * Copyright 2008 Luigi Dell'Aquila (luigi.dellaquila@assetdata.it)
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
package org.romaframework.module.chart.jfreechart.charttypes;

import java.io.OutputStream;

import org.jfree.data.general.Dataset;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;

/**
 * this is the base interface to implement a chart representation type
 * @author Luigi Dell'Aquila
 *
 */
public interface ChartTypeRenderer {
	/**
	 * this method must read a dataset and the options and write the resulting image on the output stream
	 * @param dataset the data set to be represented
	 * @param options the options about how to represent the graph (width/height, orientation, labels etc.)
	 * @param outputStream the stream where this method has to write the chart image
	 */
	public void render(Dataset dataset, RenderOptions options, OutputStream outputStream);
}
