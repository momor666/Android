package com.sbhachu.gojimochallenge.event;

import org.androidannotations.annotations.EBean;

import de.greenrobot.event.EventBus;
import static org.androidannotations.annotations.EBean.Scope.Singleton;

/**
 * ApplicationEventBus
 * Extended EventBus so it can be injected as Android Annotations dependency.
 */
@EBean(scope = Singleton)
public class ApplicationEventBus extends EventBus {
}
