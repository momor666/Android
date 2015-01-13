package com.sbhachu.flingchallenge.event;

import org.androidannotations.annotations.EBean;

import de.greenrobot.event.EventBus;
import static org.androidannotations.annotations.EBean.Scope;

/**
 * ApplicationEventBus
 * Extended EventBus so it can be injected as Android Annotations dependency.
 */
@EBean(scope = Scope.Singleton)
public class ApplicationEventBus extends EventBus {
}
