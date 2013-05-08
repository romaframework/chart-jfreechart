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
package org.romaframework.module.chart.jfreechart;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.romaframework.aspect.chart.ChartAspect;
import org.romaframework.aspect.view.ViewAspect;
import org.romaframework.core.Roma;
import org.romaframework.core.Utility;
import org.romaframework.core.module.SelfRegistrantModule;
import org.romaframework.core.schema.SchemaClassResolver;
import org.romaframework.module.chart.jfreechart.domain.ChartRenderer;
import org.romaframework.module.chart.jfreechart.domain.ChartRepresentable;
import org.romaframework.module.chart.jfreechart.helper.ChartHelper;
import org.romaframework.module.chart.jfreechart.view.domain.chartviewer.ChartViewer;

public class ChartModule extends SelfRegistrantModule implements ChartAspect {
	private static Log	log	= LogFactory.getLog(ChartModule.class);

	public String moduleName() {
		return "chart";
	}

	public void shutdown() throws RuntimeException {
	}

	public void startup() throws RuntimeException {

		// REGISTER THE APPLICATION DOMAIN
		Roma.component(SchemaClassResolver.class).addDomainPackage(ChartModule.class.getPackage().getName());
		// REGISTER THE APPLICATION DOMAIN + VIEW
		Roma.component(SchemaClassResolver.class).addDomainPackage(ChartModule.class.getPackage().getName() + Utility.PACKAGE_SEPARATOR + ViewAspect.ASPECT_NAME);
	}

	public void toChart(Object obj, OutputStream outputStream) {
		try {
			if (obj instanceof ChartRepresentable) {
				((ChartRepresentable) obj).generateGraph().render(outputStream);
			} else if (obj instanceof ChartRenderer) {
				((ChartRenderer) obj).render(outputStream);
			} else if (obj instanceof ChartViewer) {
				((ChartViewer) obj).getChart().render(outputStream);
			} else if (obj instanceof Map<?, ?>) {
				ChartHelper.render((Map<?, ?>) obj, outputStream);
			} else if (obj instanceof Collection<?>) {
				ChartHelper.render((Collection<?>) obj, outputStream);
			} else {
				log.error("unsupported chart representation");
			}
		} catch (Throwable t) {
			log.error("could not create chart: " + t);
		}
	}

	public byte[] toChart(Object obj) {
		byte[] result = null;

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		toChart(obj, stream);
		if (stream.size() > 0) {
			result = stream.toByteArray();
		}
		return result;
	}

	public String aspectName() {
		return moduleName();
	}


	public Object getUnderlyingComponent() {
		return null;
	}

}
