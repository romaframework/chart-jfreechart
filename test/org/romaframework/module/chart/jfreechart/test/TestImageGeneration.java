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
package org.romaframework.module.chart.jfreechart.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.romaframework.module.chart.jfreechart.domain.ChartRenderer;
import org.romaframework.module.chart.jfreechart.domain.ChartRendererImpl;
import org.romaframework.module.chart.jfreechart.domain.EditableRenderOptions;
import org.romaframework.module.chart.jfreechart.helper.ChartHelper;

public class TestImageGeneration extends TestCase {
	private String	baseDir	= "~";

	public void test() throws Exception {
		ChartRendererImpl renderer = getDataRenderer();
		EditableRenderOptions options = getOptions();
		for (String type : renderer.getSupportedChartTypes()) {
			options.setChartType(type);
			File file = File.createTempFile("test" + type, ".png");
			FileOutputStream stream = new FileOutputStream(file);
			renderer.render(options, stream);
			stream.flush();
			stream.close();
		}
	}

	public void testCollectionObject() throws Exception {
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(3);
		l.add(8);
		l.add(4);
		l.add(2);
		ChartRenderer renderer = ChartHelper.generateChartRenderer(l);
		EditableRenderOptions options = getOptions();
		for (String type : renderer.getSupportedChartTypes()) {
			options.setChartType(type);
			File file = File.createTempFile("test-list" + type, ".png");
			FileOutputStream stream = new FileOutputStream(file);
			renderer.render(options, stream);
			stream.flush();
			stream.close();
		}
	}

	public void test3DCollectionObject() throws Exception {
		List<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> sublist1 = new ArrayList<Integer>();
		sublist1.add(1);
		sublist1.add(3);
		sublist1.add(8);
		sublist1.add(4);
		sublist1.add(2);
		l.add(sublist1);

		ArrayList<Integer> sublist2 = new ArrayList<Integer>();
		sublist2.add(3);
		sublist2.add(1);
		sublist2.add(6);
		sublist2.add(2);
		sublist2.add(7);
		l.add(sublist2);

		ChartRenderer renderer = ChartHelper.generateChartRenderer(l);
		EditableRenderOptions options = getOptions();
		for (String type : renderer.getSupportedChartTypes()) {
			options.setChartType(type);
			File file = File.createTempFile("test-list3d" + type, ".png");
			FileOutputStream stream = new FileOutputStream(file);
			renderer.render(options, stream);
			stream.flush();
			stream.close();
		}
	}

	public void testMapObject() throws Exception {
		Map<String, Number> map = new LinkedHashMap<String, Number>();
		map.put("uno", 3);
		map.put("due", 5);
		map.put("tre", 2);

		ChartRenderer renderer = ChartHelper.generateChartRenderer(map);
		EditableRenderOptions options = getOptions();
		for (String type : renderer.getSupportedChartTypes()) {
			options.setChartType(type);
			File file = File.createTempFile("test-map" + type, ".png");
			FileOutputStream stream = new FileOutputStream(file);
			renderer.render(options, stream);
			stream.flush();
			stream.close();
		}
	}

	public void testMap3DObject() throws Exception {
		Map<String, Number> submap1 = new LinkedHashMap<String, Number>();
		submap1.put("one", 3);
		submap1.put("two", 5);
		submap1.put("three", 2);

		Map<String, Number> submap2 = new LinkedHashMap<String, Number>();
		submap2.put("one", 6);
		submap2.put("two", 1);
		submap2.put("three", 3);

		Map<String, Map<String, Number>> map = new LinkedHashMap<String, Map<String, Number>>();
		map.put("first", submap1);
		map.put("second", submap2);

		ChartRenderer renderer = ChartHelper.generateChartRenderer(map);
		EditableRenderOptions options = getOptions();
		for (String type : renderer.getSupportedChartTypes()) {
			options.setChartType(type);
			File file = File.createTempFile("test-map3d" + type, ".png");
			FileOutputStream stream = new FileOutputStream(file);
			renderer.render(options, stream);
			stream.flush();
			stream.close();
		}
	}

	private ChartRendererImpl getDataRenderer() {
		ChartRendererImpl renderer = new ChartRendererImpl();
		renderer.setPoint("x", "a", 1);
		renderer.setPoint("x", "b", 2);
		renderer.setPoint("x", "c", 3);
		renderer.setPoint("y", "a", 4);
		renderer.setPoint("y", "b", 1);
		renderer.setPoint("y", "c", 2);
		return renderer;
	}

	private EditableRenderOptions getOptions() {
		EditableRenderOptions options = EditableRenderOptions.newInstance();
		return options;
	}
}
