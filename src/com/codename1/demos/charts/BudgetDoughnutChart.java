/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codename1.demos.charts;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.views.DoughnutChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.mycompany.Entite.Espace;
import com.mycompany.Service.ServiceEspace;

/**
 * Budget demo pie chart.
 */
public class BudgetDoughnutChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Budget chart for several years";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The budget per project for several years (doughnut chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "Doughnut Chart Demo";
    }

    @Override
    public Component execute() {
        List<double[]> values = new ArrayList<double[]>();
        
        ServiceEspace serviceEspace=new ServiceEspace();
        for (Espace espace : serviceEspace.getList2())
        {
           
            
            values.add(new double[]{Double.valueOf(espace.getCapacity())});
        }
     
        List<String[]> titles = new ArrayList<String[]>();
        for (Espace espace : serviceEspace.getList2())
        {
           
            
            titles.add(new String[]{(espace.getNom())});
        }
     
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsColor(ColorUtil.GRAY);
        initRendererer(renderer);

        DoughnutChart chart = new DoughnutChart(buildMultipleCategoryDataset("Project budget", titles, values), renderer);
        ChartComponent c = newChart(chart);
        return c;

    }

}
