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
package org.romaframework.module.chart.jfreechart.domain;

import org.jfree.chart.plot.PlotOrientation;

public class RenderOptions {

	public static final String		CHART_TYPE_XYLINE						= "XYLineChart";
	public static String					CHART_TYPE_AREA							= "area";
	public static String					CHART_TYPE_BAR_CHART				= "barChart";
	public static String					CHART_TYPE_METER_CHART			= "meterChart";
	public static String					CHART_TYPE_BAR_CHART_3D			= "barChart3D";
	public static String					CHART_TYPE_LINE							= "line";
	public static String					CHART_TYPE_LINE_3D					= "line3D";
	public static String					CHART_TYPE_MULTIPLE_PIE			= "multiplePie";
	public static String					CHART_TYPE_MULTIPLE_PIE_3D	= "multiplePie3D";
	public static String					CHART_TYPE_STACKED_AREA			= "stackedArea";
	public static String					CHART_TYPE_STACKED_BAR			= "stackedBar";
	public static String					CHART_TYPE_STACKED_BAR_3D		= "stackedBar3D";
	public static String					CHART_TYPE_SPIDER						= "spider";

	public static PlotOrientation	ORIENTATION_HORIZONTAL			= PlotOrientation.HORIZONTAL;
	public static PlotOrientation	ORIENTATION_VERTICAL				= PlotOrientation.VERTICAL;

	protected int									width;
	protected int									height;
	protected String							chartType;
	protected String							chartTitle;
	protected boolean							showLegend;
	protected String							xLabel;
	protected String							yLabel;
	protected PlotOrientation			orientation;
	private boolean								isShowTooltip;

	public enum LabelOrietation {
		STANDART, DOWN_45, DOWN_90, UP_45, UP_90;
	}

	protected LabelOrietation	labelOrientation;

	public RenderOptions() {

	}

	public RenderOptions(int width, int height, String chartType, String chartTitle, boolean showLegend, String xLabel,
			String yLabel, PlotOrientation orientation) {
		this.width = width;
		this.height = height;
		this.chartType = chartType;
		this.chartTitle = chartTitle;
		this.showLegend = showLegend;
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.orientation = orientation;
	}

	public PlotOrientation getOrientation() {
		return orientation;
	}

	public String getXLabel() {
		return xLabel;
	}

	public String getYLabel() {
		return yLabel;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public boolean isShowLegend() {
		return showLegend;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getChartType() {
		return chartType;
	}

	public static RenderOptions newInstance() {
		RenderOptions options = new RenderOptions();
		options.chartTitle = "";
		options.chartType = RenderOptions.CHART_TYPE_BAR_CHART;
		options.height = 200;
		options.orientation = ORIENTATION_VERTICAL;
		options.showLegend = false;
		options.xLabel = "";
		options.yLabel = "";
		options.width = 200;
		return options;
	}

	public boolean isShowTooltip() {

		return isShowTooltip;
	}

	public void setShowTooltip(boolean isShowTooltip) {
		this.isShowTooltip = isShowTooltip;
	}

	public LabelOrietation getLabelOrientation() {
		return labelOrientation;
	}

}
