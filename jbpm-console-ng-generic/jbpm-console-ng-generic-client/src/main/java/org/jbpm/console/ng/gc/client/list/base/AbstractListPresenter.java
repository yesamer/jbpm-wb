/*
 * Copyright 2014 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.console.ng.gc.client.list.base;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.event.Observes;
import org.jbpm.console.ng.ga.model.QueryFilter;
import org.jbpm.console.ng.ga.model.events.SearchEvent;

/**
 *
 * @author salaboy
 */
public abstract class AbstractListPresenter<T> {

  protected AsyncDataProvider<T> dataProvider;

  protected QueryFilter currentFilter;

  public void addDataDisplay(final HasData<T> display) {
    dataProvider.addDataDisplay(display);
  }

  public void refreshGrid() {
    HasData<T> next = dataProvider.getDataDisplays().iterator().next();
    next.setVisibleRangeAndClearData(next.getVisibleRange(), true);
  }

  protected void onSearchEvent(@Observes SearchEvent searchEvent) {
    String filterString = searchEvent.getFilter();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("name", filterString);
    currentFilter.setParams(params);
    //TODO: I don't like this...
    HasData<T> next = dataProvider.getDataDisplays().iterator().next();
    next.setVisibleRangeAndClearData(next.getVisibleRange(), true);

  }
}
