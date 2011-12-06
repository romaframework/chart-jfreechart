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
package org.romaframework.module.chart.jfreechart.view.domain.renderoptions;

import org.jfree.chart.plot.PlotOrientation;
import org.romaframework.aspect.core.annotation.AnnotationConstants;
import org.romaframework.aspect.core.annotation.CoreField;
import org.romaframework.aspect.view.ViewConstants;
import org.romaframework.aspect.view.annotation.ViewAction;
import org.romaframework.aspect.view.annotation.ViewField;
import org.romaframework.core.Roma;
import org.romaframework.core.config.Refreshable;
import org.romaframework.frontend.domain.entity.ComposedEntityInstance;
import org.romaframework.module.chart.jfreechart.domain.EditableRenderOptions;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;

/**
 * this class is part of the ChartViewer GUI, it allows you to modify chart properties (type, dimension etc.)
 * 
 * @author Luigi Dell'Aquila (luigi.dellaquila@assetdata.it)
 * 
 */
public class RenderOptionsMain extends ComposedEntityInstance<EditableRenderOptions> implements Refreshable {

	@ViewField(label = "Type", render = ViewConstants.RENDER_SELECT, selectionField = "selected")
	private String[]					types;

	@ViewField(label = "Orientation", render = ViewConstants.RENDER_SELECT, selectionField = "orientation")
	private PlotOrientation[]	orientations	= new PlotOrientation[] { RenderOptions.ORIENTATION_HORIZONTAL, RenderOptions.ORIENTATION_VERTICAL };

	@ViewField(visible = AnnotationConstants.FALSE)
	public PlotOrientation getOrientation() {
		return getEntity().getOrientation();
	}

	public void setOrientation(PlotOrientation orientation) {
		this.getEntity().setOrientation(orientation);
	}

	public RenderOptionsMain(EditableRenderOptions entity) {
		super(entity);
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	@ViewAction(visible = AnnotationConstants.FALSE)
	public void refresh() {
		Roma.fieldChanged(this, "entity");

	}

	@ViewField(visible = AnnotationConstants.FALSE)
	public String getSelected() {
		return this.entity.getChartType();
	}

	public void setSelected(String selected) {
		this.entity.setChartType(selected);
	}

	@Override
	@CoreField(embedded = AnnotationConstants.TRUE)
	@ViewField(position = ViewConstants.LAYOUT_EXPAND)
	public EditableRenderOptions getEntity() {
		return super.getEntity();
	}

	public PlotOrientation[] getOrientations() {
		return orientations;
	}

	public void setOrientations(PlotOrientation[] orientations) {
		this.orientations = orientations;
	}

}
